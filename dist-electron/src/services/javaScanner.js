"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.JavaScanner = void 0;
const fs_1 = __importDefault(require("fs"));
const path_1 = __importDefault(require("path"));
class JavaScanner {
    static cachedClasses = [];
    /**
     * Varre recursivamente um diretório procurando arquivos .java
     * e extraindo definições de classes (em especial classes com prefixo 'Mic*')
     */
    static async scanDirectory(directoryPath) {
        const results = [];
        async function walk(dir) {
            const entries = await fs_1.default.promises.readdir(dir, { withFileTypes: true });
            for (const entry of entries) {
                const fullPath = path_1.default.join(dir, entry.name);
                if (entry.isDirectory()) {
                    // Ignora pastas comuns que não contêm fontes
                    if (entry.name !== '.git' && entry.name !== 'node_modules' && entry.name !== 'bin' && entry.name !== 'target') {
                        await walk(fullPath);
                    }
                }
                else if (entry.isFile() && entry.name.endsWith('.java')) {
                    try {
                        const simpleName = entry.name.replace(/\.java$/, '');
                        const isMic = simpleName.startsWith('Mic');
                        // Extrai pacote primeiro via caminho de pastas (0ms overhead)
                        let pkg = '';
                        const normPath = fullPath.replace(/\\/g, '/');
                        const pathParts = normPath.split('/');
                        const pkgIndex = pathParts.findIndex((p) => ['com', 'org', 'br', 'net', 'gov', 'io'].includes(p.toLowerCase()));
                        if (pkgIndex !== -1) {
                            pkg = pathParts.slice(pkgIndex, -1).join('.');
                        }
                        // Fallback: se não estiver na convenção de pastas, lê apenas os primeiros 1024 bytes do cabeçalho
                        if (!pkg) {
                            try {
                                const fd = await fs_1.default.promises.open(fullPath, 'r');
                                const buf = Buffer.alloc(1024);
                                const { bytesRead } = await fd.read(buf, 0, 1024, 0);
                                await fd.close();
                                const header = buf.toString('utf8', 0, bytesRead);
                                const pkgMatch = header.match(/package\s+([\w\.]+);/);
                                if (pkgMatch)
                                    pkg = pkgMatch[1];
                            }
                            catch {
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
                    }
                    catch {
                        // Ignora arquivos com erro
                    }
                }
            }
        }
        if (fs_1.default.existsSync(directoryPath)) {
            await walk(directoryPath);
        }
        this.cachedClasses = results;
        return results;
    }
    /**
     * Filtra classes em memória por termo de busca e flag MIC
     */
    static search(query, onlyMic = false) {
        const term = query.toLowerCase().trim();
        return this.cachedClasses.filter((item) => {
            if (onlyMic && !item.isMic)
                return false;
            if (!term)
                return true;
            return (item.simpleClassName.toLowerCase().includes(term) ||
                item.packageName.toLowerCase().includes(term));
        });
    }
    static getCached() {
        return this.cachedClasses;
    }
}
exports.JavaScanner = JavaScanner;
