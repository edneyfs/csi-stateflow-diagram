const { XmlEngine } = require('./xmlEngine');

const IGNORED_DIRS = new Set([
  '.git',
  '.agent',
  '.vscode',
  '.idea',
  'node_modules',
  'dist',
  'dist-electron',
  'bin',
  'target',
  'extracted_plugins',
  'original_jars',
  'decompiled_src',
  'telas-plugin-eclipse',
]);

class WebFileSystemService {
  static currentHandle = null;

  static isSupported() {
    return typeof window !== 'undefined' && 'showDirectoryPicker' in window;
  }

  static getCurrentHandle() {
    return this.currentHandle;
  }

  static setCurrentHandle(handle) {
    this.currentHandle = handle;
  }

  static async selectDirectory() {
    if (!this.isSupported()) {
      throw new Error('File System Access API não é suportada neste navegador.');
    }
    const handle = await window.showDirectoryPicker({ mode: 'readwrite' });
    this.currentHandle = handle;
    return handle;
  }

  static async getFileHandle(root, relativePath, create = false) {
    const normalized = relativePath.replace(/\\/g, '/').replace(/^\/+/, '');
    const parts = normalized.split('/');
    const fileName = parts.pop();

    if (!fileName) {
      throw new Error(`Caminho inválido: ${relativePath}`);
    }

    let currentDir = root;
    for (const part of parts) {
      currentDir = await currentDir.getDirectoryHandle(part, { create });
    }

    const fileHandle = await currentDir.getFileHandle(fileName, { create });
    return { fileHandle, parentDir: currentDir, fileName };
  }

  static async scanDiagrams(root = this.currentHandle) {
    if (!root) return [];
    const diagrams = [];

    async function walk(dir, currentRelPath = '') {
      for await (const entry of dir.values()) {
        if (entry.kind === 'directory') {
          if (!IGNORED_DIRS.has(entry.name)) {
            const nextRelPath = currentRelPath ? `${currentRelPath}/${entry.name}` : entry.name;
            await walk(entry, nextRelPath);
          }
        } else if (entry.kind === 'file') {
          const name = entry.name;
          const isDiagram =
            name.endsWith('.executionflow') ||
            name.endsWith('.stateflow') ||
            name.endsWith('.executionflow_diagram') ||
            name.endsWith('.stateflow_diagram');

          if (isDiagram) {
            const relPath = currentRelPath ? `${currentRelPath}/${name}` : name;
            const folder = currentRelPath;
            const isExecution = name.includes('executionflow');

            diagrams.push({
              name,
              relativePath: relPath,
              folder,
              fullPath: relPath,
              type: isExecution ? 'executionflow' : 'stateflow',
              isDiagramLayout: name.endsWith('_diagram'),
            });
          }
        }
      }
    }

    await walk(root);

    // Identifica quais diagramas semânticos possuem companion layout (.diagram)
    const allPaths = new Set(diagrams.map((d) => d.relativePath));
    for (const d of diagrams) {
      if (!d.isDiagramLayout) {
        d.hasCompanionLayout = allPaths.has(`${d.relativePath}_diagram`);
      }
    }

    return diagrams.sort((a, b) => a.relativePath.localeCompare(b.relativePath));
  }

  static async scanJavaClasses(root = this.currentHandle) {
    if (!root) return [];
    const results = [];

    async function walk(dir, currentRelPath = '') {
      for await (const entry of dir.values()) {
        if (entry.kind === 'directory') {
          if (!IGNORED_DIRS.has(entry.name)) {
            const nextRelPath = currentRelPath ? `${currentRelPath}/${entry.name}` : entry.name;
            await walk(entry, nextRelPath);
          }
        } else if (entry.kind === 'file' && entry.name.endsWith('.java')) {
          try {
            const file = await entry.getFile();
            const content = await file.text();

            const pkgMatch = content.match(/package\s+([\w\.]+);/);
            const classMatch = content.match(/public\s+(?:abstract\s+)?class\s+(\w+)/);

            if (classMatch) {
              const simpleName = classMatch[1];
              const pkg = pkgMatch ? pkgMatch[1] : '';
              const fullName = pkg ? `${pkg}.${simpleName}` : simpleName;
              const relPath = currentRelPath ? `${currentRelPath}/${entry.name}` : entry.name;

              results.push({
                className: fullName,
                simpleClassName: simpleName,
                packageName: pkg,
                filePath: relPath,
                isMic: simpleName.startsWith('Mic'),
              });
            }
          } catch {
            // Ignora arquivos que não possam ser lidos
          }
        }
      }
    }

    await walk(root);
    return results.sort((a, b) => a.simpleClassName.localeCompare(b.simpleClassName));
  }

  static async findReferences(className, root = this.currentHandle) {
    if (!root || !className) return [];

    const diagrams = await this.scanDiagrams(root);
    const references = [];

    for (const d of diagrams) {
      if (d.isDiagramLayout) continue;
      try {
        const content = await this.readFile(d.relativePath, root);
        const model = XmlEngine.parse(content);

        if (model.type === 'executionflow') {
          for (const obj of model.executableFlowObjects) {
            const matchSimple = obj.simpleClassName === className;
            const matchFull = obj.className === className;
            if (matchSimple || matchFull) {
              references.push({
                diagramPath: d.relativePath,
                diagramName: d.name,
                nodePathKey: obj.pathKey,
                simpleClassName: obj.simpleClassName,
                className: obj.className,
              });
            }
          }
        }
      } catch {
        // Ignora erros
      }
    }

    return references;
  }

  static async readFile(relativePath, root = this.currentHandle) {
    if (!root) throw new Error('Nenhuma pasta selecionada no navegador.');
    const { fileHandle } = await this.getFileHandle(root, relativePath, false);
    const file = await fileHandle.getFile();
    return await file.text();
  }

  static async writeFile(relativePath, content, root = this.currentHandle, createBackup = true) {
    if (!root) throw new Error('Nenhuma pasta selecionada no navegador.');

    const { fileHandle, parentDir, fileName } = await this.getFileHandle(root, relativePath, true);
    let backupPath;

    if (createBackup) {
      try {
        const existingFile = await fileHandle.getFile();
        if (existingFile.size > 0) {
          const existingContent = await existingFile.text();
          const backupFileName = `${fileName}.bak`;
          const backupHandle = await parentDir.getFileHandle(backupFileName, { create: true });
          const backupWritable = await backupHandle.createWritable();
          await backupWritable.write(existingContent);
          await backupWritable.close();
          backupPath = `${relativePath}.bak`;
        }
      } catch (backupErr) {
        console.warn('Aviso: Não foi possível criar backup .bak:', backupErr);
      }
    }

    const normalizedContent = content.replace(/\r?\n/g, '\r\n');
    const writable = await fileHandle.createWritable();
    await writable.write(normalizedContent);
    await writable.close();

    return {
      success: true,
      backupPath,
    };
  }
}

module.exports = { WebFileSystemService };
