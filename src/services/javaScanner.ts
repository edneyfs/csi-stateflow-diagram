import fs from 'fs';
import path from 'path';

export interface JavaClassInfo {
  className: string;
  simpleClassName: string;
  packageName: string;
  filePath: string;
  isMic: boolean;
}

export class JavaScanner {
  private static cachedClasses: JavaClassInfo[] = [];

  /**
   * Varre recursivamente um diretório procurando arquivos .java
   * e extraindo definições de classes (em especial classes com prefixo 'Mic*' / 'MIC*')
   */
  public static async scanDirectory(directoryPath: string, onlyMic: boolean = true): Promise<JavaClassInfo[]> {
    const results: JavaClassInfo[] = [];

    async function walk(dir: string) {
      const entries = await fs.promises.readdir(dir, { withFileTypes: true });
      for (const entry of entries) {
        const fullPath = path.join(dir, entry.name);
        if (entry.isDirectory()) {
          // Ignora pastas comuns que não contêm fontes
          if (entry.name !== '.git' && entry.name !== 'node_modules' && entry.name !== 'bin' && entry.name !== 'target') {
            await walk(fullPath);
          }
        } else if (entry.isFile() && entry.name.toLowerCase().endsWith('.java')) {
          const simpleName = entry.name.replace(/\.java$/i, '');
          const isMic = /^mic/i.test(simpleName);
          if (onlyMic && !isMic) continue;
          try {
            // Extrai pacote primeiro via caminho de pastas (0ms overhead)
            let pkg = '';
            const normPath = fullPath.replace(/\\/g, '/');
            const pathParts = normPath.split('/');
            const pkgIndex = pathParts.findIndex((p) =>
              ['com', 'org', 'br', 'net', 'gov', 'io'].includes(p.toLowerCase())
            );
            if (pkgIndex !== -1) {
              pkg = pathParts.slice(pkgIndex, -1).join('.');
            }

            // Fallback: se não estiver na convenção de pastas, lê apenas os primeiros 1024 bytes do cabeçalho
            if (!pkg) {
              try {
                const fd = await fs.promises.open(fullPath, 'r');
                const buffer = Buffer.alloc(1024);
                const { bytesRead } = await fd.read(buffer, 0, 1024, 0);
                await fd.close();
                const header = buffer.toString('utf8', 0, bytesRead);
                const pkgMatch = header.match(/package\s+([\w\.]+);/);
                if (pkgMatch) {
                  pkg = pkgMatch[1];
                }
              } catch {
                // ignora se falhar leitura parcial
              }
            }

            const fullName = pkg ? `${pkg}.${simpleName}` : simpleName;
            const relPath = path.relative(directoryPath, fullPath).replace(/\\/g, '/');

            results.push({
              className: fullName,
              simpleClassName: simpleName,
              packageName: pkg,
              filePath: relPath,
              isMic,
            });
          } catch {
            // Ignora arquivos que não possam ser lidos
          }
        }
      }
    }

    if (fs.existsSync(directoryPath)) {
      await walk(directoryPath);
    }

    this.cachedClasses = results.sort((a, b) => a.simpleClassName.localeCompare(b.simpleClassName));
    return this.cachedClasses;
  }

  public static getCachedClasses(): JavaClassInfo[] {
    return this.cachedClasses;
  }
}
