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
   * e extraindo definições de classes (em especial classes com prefixo 'Mic*')
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
        } else if (entry.isFile() && entry.name.endsWith('.java')) {
          const simpleName = entry.name.replace(/\.java$/, '');
          const isMic = simpleName.startsWith('Mic');
          if (onlyMic && !isMic) continue;
          try {
            const simpleName = entry.name.replace(/\.java$/, '');
            const isMic = simpleName.startsWith('Mic');

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
                const buf = Buffer.alloc(1024);
                const { bytesRead } = await fd.read(buf, 0, 1024, 0);
                await fd.close();
                const header = buf.toString('utf8', 0, bytesRead);
                const pkgMatch = header.match(/package\s+([\w\.]+);/);
                if (pkgMatch) pkg = pkgMatch[1];
              } catch {
                // ignora erro de leitura
              }
            }

            const fullName = pkg ? `${pkg}.${simpleName}` : simpleName;
            results.push({
              className: fullName,
              simpleClassName: simpleName,
              packageName: pkg,
              filePath: fullPath,
              isMic,
            });
          } catch {
            // Ignora arquivos com erro
          }
        }
      }
    }

    if (fs.existsSync(directoryPath)) {
      await walk(directoryPath);
    }

    this.cachedClasses = results;
    return results;
  }

  /**
   * Filtra classes em memória por termo de busca e flag MIC
   */
  public static search(query: string, onlyMic: boolean = false): JavaClassInfo[] {
    const term = query.toLowerCase().trim();
    return this.cachedClasses.filter((item) => {
      if (onlyMic && !item.isMic) return false;
      if (!term) return true;
      return (
        item.simpleClassName.toLowerCase().includes(term) ||
        item.packageName.toLowerCase().includes(term)
      );
    });
  }

  public static getCached(): JavaClassInfo[] {
    return this.cachedClasses;
  }
}
