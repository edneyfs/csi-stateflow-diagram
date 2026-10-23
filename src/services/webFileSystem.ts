import type { DiagramFileInfo, JavaClassInfo, DiagramReference } from '../types/workspace';
import { XmlEngine } from './xmlEngine';
import type { ExecutionFlowModel } from '../types/model';

const DB_NAME = 'CSI_STATEFLOW_DB';
const DB_VERSION = 1;
const STORE_NAME = 'workspace_handles';
const STORE_KEY = 'root_directory_handle';

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

/**
 * Utilitário de persistência nativa do FileSystemHandle no IndexedDB
 */
class HandleStorage {
  private static openDB(): Promise<IDBDatabase> {
    return new Promise((resolve, reject) => {
      if (typeof indexedDB === 'undefined') {
        return reject(new Error('IndexedDB não suportado neste ambiente.'));
      }
      const request = indexedDB.open(DB_NAME, DB_VERSION);
      request.onupgradeneeded = () => {
        const db = request.result;
        if (!db.objectStoreNames.contains(STORE_NAME)) {
          db.createObjectStore(STORE_NAME);
        }
      };
      request.onsuccess = () => resolve(request.result);
      request.onerror = () => reject(request.error);
    });
  }

  public static async saveHandle(handle: FileSystemDirectoryHandle): Promise<void> {
    const db = await this.openDB();
    return new Promise((resolve, reject) => {
      const tx = db.transaction(STORE_NAME, 'readwrite');
      const store = tx.objectStore(STORE_NAME);
      const req = store.put(handle, STORE_KEY);
      req.onsuccess = () => resolve();
      req.onerror = () => reject(req.error);
    });
  }

  public static async getStoredHandle(): Promise<FileSystemDirectoryHandle | null> {
    const db = await this.openDB();
    return new Promise((resolve, reject) => {
      const tx = db.transaction(STORE_NAME, 'readonly');
      const store = tx.objectStore(STORE_NAME);
      const req = store.get(STORE_KEY);
      req.onsuccess = () => resolve(req.result || null);
      req.onerror = () => reject(req.error);
    });
  }

  public static async clearStoredHandle(): Promise<void> {
    const db = await this.openDB();
    return new Promise((resolve, reject) => {
      const tx = db.transaction(STORE_NAME, 'readwrite');
      const store = tx.objectStore(STORE_NAME);
      const req = store.delete(STORE_KEY);
      req.onsuccess = () => resolve();
      req.onerror = () => reject(req.error);
    });
  }
}

/**
 * Serviço de acesso direto ao sistema de arquivos via W3C File System Access API
 * Funciona 100% no navegador (Chrome, Edge, Opera, Brave).
 */
export class WebFileSystemService {
  private static currentHandle: FileSystemDirectoryHandle | null = null;

  public static isSupported(): boolean {
    return typeof window !== 'undefined' && 'showDirectoryPicker' in window;
  }

  public static getCurrentHandle(): FileSystemDirectoryHandle | null {
    return this.currentHandle;
  }

  public static setCurrentHandle(handle: FileSystemDirectoryHandle | null) {
    this.currentHandle = handle;
  }

  /**
   * Abre o seletor nativo do sistema operacional e salva a referência no IndexedDB
   */
  public static async selectDirectory(): Promise<FileSystemDirectoryHandle | null> {
    if (!this.isSupported()) {
      throw new Error('File System Access API não é suportada neste navegador. Use Google Chrome ou Microsoft Edge.');
    }

    try {
      const handle = await (window as any).showDirectoryPicker({
        mode: 'readwrite',
      });

      this.currentHandle = handle;
      await HandleStorage.saveHandle(handle);
      return handle;
    } catch (err: any) {
      if (err.name === 'AbortError') {
        // Usuário cancelou a seleção
        return null;
      }
      throw err;
    }
  }

  /**
   * Recupera a pasta aberta anteriormente a partir do IndexedDB
   */
  public static async getStoredDirectory(): Promise<FileSystemDirectoryHandle | null> {
    try {
      return await HandleStorage.getStoredHandle();
    } catch {
      return null;
    }
  }

  /**
   * Reconecta com 1 clique a pasta salva anteriormente no IndexedDB
   */
  public static async reconnectStoredDirectory(): Promise<FileSystemDirectoryHandle | null> {
    const handle = await this.getStoredDirectory();
    if (!handle) return null;

    const granted = await this.verifyPermission(handle, true);
    if (granted) {
      this.currentHandle = handle;
      return handle;
    }
    return null;
  }

  /**
   * Limpa a pasta memorizada
   */
  public static async clearStoredDirectory(): Promise<void> {
    this.currentHandle = null;
    await HandleStorage.clearStoredHandle();
  }

  /**
   * Verifica ou solicita permissão ao usuário
   */
  public static async verifyPermission(
    handle: FileSystemDirectoryHandle | FileSystemFileHandle,
    readWrite = true
  ): Promise<boolean> {
    const options: any = {};
    if (readWrite) {
      options.mode = 'readwrite';
    }

    // 1. Checa se permissão já está concedida
    if ((await (handle as any).queryPermission(options)) === 'granted') {
      return true;
    }

    // 2. Se não estiver, solicita com 1 clique ao usuário
    if ((await (handle as any).requestPermission(options)) === 'granted') {
      return true;
    }

    return false;
  }

  /**
   * Navega até o FileHandle a partir de um caminho relativo
   */
  private static async getFileHandle(
    root: FileSystemDirectoryHandle,
    relativePath: string,
    create = false
  ): Promise<{ fileHandle: FileSystemFileHandle; parentDir: FileSystemDirectoryHandle; fileName: string }> {
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

  /**
   * Varre recursivamente todos os arquivos de diagramas (.executionflow e .stateflow)
   */
  public static async scanDiagrams(
    root: FileSystemDirectoryHandle = this.currentHandle!
  ): Promise<DiagramFileInfo[]> {
    if (!root) return [];
    const diagrams: DiagramFileInfo[] = [];

    async function walk(dir: FileSystemDirectoryHandle, currentRelPath = '') {
      for await (const entry of (dir as any).values()) {
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

  /**
   * Varre recursivamente os arquivos .java do workspace e extrai classes MICs
   */
  public static async scanJavaClasses(
    root: FileSystemDirectoryHandle = this.currentHandle!
  ): Promise<JavaClassInfo[]> {
    if (!root) return [];
    const results: JavaClassInfo[] = [];

    async function walk(dir: FileSystemDirectoryHandle, currentRelPath = '') {
      for await (const entry of (dir as any).values()) {
        if (entry.kind === 'directory') {
          if (!IGNORED_DIRS.has(entry.name)) {
            const nextRelPath = currentRelPath ? `${currentRelPath}/${entry.name}` : entry.name;
            await walk(entry, nextRelPath);
          }
        } else if (entry.kind === 'file' && entry.name.endsWith('.java')) {
          try {
            const simpleName = entry.name.replace(/\.java$/, '');
            const isMic = simpleName.startsWith('Mic');

            // 1. Extração ultra-rápida do pacote a partir da estrutura de diretórios (0ms overhead)
            let pkg = '';
            const pathParts = currentRelPath.split('/');
            const pkgIndex = pathParts.findIndex((p) =>
              ['com', 'org', 'br', 'net', 'gov', 'io'].includes(p.toLowerCase())
            );
            if (pkgIndex !== -1) {
              pkg = pathParts.slice(pkgIndex).join('.');
            }

            // 2. Fallback: se não detectado no caminho, lê apenas os primeiros 1024 bytes (não o arquivo todo)
            if (!pkg) {
              try {
                const file = await entry.getFile();
                const slice = typeof file.slice === 'function' ? file.slice(0, 1024) : file;
                const header = await slice.text();
                const pkgMatch = header.match(/package\s+([\w\.]+);/);
                if (pkgMatch) pkg = pkgMatch[1];
              } catch {
                // ignora falha de leitura pontual
              }
            }

            const fullName = pkg ? `${pkg}.${simpleName}` : simpleName;
            const relPath = currentRelPath ? `${currentRelPath}/${entry.name}` : entry.name;

            results.push({
              className: fullName,
              simpleClassName: simpleName,
              packageName: pkg,
              filePath: relPath,
              isMic,
            });
          } catch {
            // Ignora arquivos que não possam ser processados
          }
        }
      }
    }

    await walk(root);
    return results.sort((a, b) => a.simpleClassName.localeCompare(b.simpleClassName));
  }

  /**
   * Busca referências de classes em todos os diagramas do workspace
   */
  public static async findReferences(
    className: string,
    root: FileSystemDirectoryHandle = this.currentHandle!
  ): Promise<DiagramReference[]> {
    if (!root || !className) return [];

    const diagrams = await this.scanDiagrams(root);
    const references: DiagramReference[] = [];

    for (const d of diagrams) {
      if (d.isDiagramLayout) continue; // Pula arquivos de layout puramente visuais
      try {
        const content = await this.readFile(d.relativePath, root);
        const model = XmlEngine.parse(content);

        if (model.type === 'executionflow') {
          const execModel = model as ExecutionFlowModel;
          for (const obj of execModel.executableFlowObjects) {
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
        // Ignora diagramas com erro de leitura
      }
    }

    return references;
  }

  /**
   * Lê o conteúdo texto de um arquivo a partir do caminho relativo
   */
  public static async readFile(
    relativePath: string,
    root: FileSystemDirectoryHandle = this.currentHandle!
  ): Promise<string> {
    if (!root) throw new Error('Nenhuma pasta selecionada no navegador.');
    const { fileHandle } = await this.getFileHandle(root, relativePath, false);
    const file = await fileHandle.getFile();
    return await file.text();
  }

  /**
   * Gravação atômica com criação automática de backup de segurança (.bak)
   * Padroniza as quebras de linha para CRLF (\r\n) preservando compatibilidade CSI / Eclipse / Windows.
   */
  public static async writeFile(
    relativePath: string,
    content: string,
    root: FileSystemDirectoryHandle = this.currentHandle!,
    createBackup = true
  ): Promise<{ success: boolean; backupPath?: string }> {
    if (!root) throw new Error('Nenhuma pasta selecionada no navegador.');

    const { fileHandle, parentDir, fileName } = await this.getFileHandle(root, relativePath, true);

    let backupPath: string | undefined;

    // 1. Cria backup .bak se o arquivo já existir e backup estiver habilitado
    if (createBackup) {
      try {
        const existingFile = await fileHandle.getFile();
        if (existingFile.size > 0) {
          const existingContent = await existingFile.text();
          const backupFileName = `${fileName}.bak`;
          const backupHandle = await parentDir.getFileHandle(backupFileName, { create: true });
          const backupWritable = await (backupHandle as any).createWritable();
          await backupWritable.write(existingContent);
          await backupWritable.close();
          backupPath = `${relativePath}.bak`;
        }
      } catch (backupErr) {
        console.warn('Aviso: Não foi possível criar backup .bak antes de gravar:', backupErr);
      }
    }

    // 2. Padronização CRLF para total compatibilidade com a máquina de estados legada CSI
    const normalizedContent = content.replace(/\r?\n/g, '\r\n');

    // 3. Gravação atômica no arquivo destino
    const writable = await (fileHandle as any).createWritable();
    await writable.write(normalizedContent);
    await writable.close();

    return {
      success: true,
      backupPath,
    };
  }
}
