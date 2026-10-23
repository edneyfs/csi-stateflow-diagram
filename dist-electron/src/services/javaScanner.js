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
     * e extraindo definições de classes (em especial classes com prefixo 'Mic*' / 'MIC*')
     */
    static async scanDirectory(directoryPath, onlyMic = true) {
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
                else if (entry.isFile() && entry.name.toLowerCase().endsWith('.java')) {
                    const simpleName = entry.name.replace(/\.java$/i, '');
                    const isMic = /^mic/i.test(simpleName);
                    if (onlyMic && !isMic)
                        continue;
                    try {
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
                                const buffer = Buffer.alloc(1024);
                                const { bytesRead } = await fd.read(buffer, 0, 1024, 0);
                                await fd.close();
                                const header = buffer.toString('utf8', 0, bytesRead);
                                const pkgMatch = header.match(/package\s+([\w\.]+);/);
                                if (pkgMatch) {
                                    pkg = pkgMatch[1];
                                }
                            }
                            catch {
                                // ignora se falhar leitura parcial
                            }
                        }
                        const fullName = pkg ? `${pkg}.${simpleName}` : simpleName;
                        const relPath = path_1.default.relative(directoryPath, fullPath).replace(/\\/g, '/');
                        results.push({
                            className: fullName,
                            simpleClassName: simpleName,
                            packageName: pkg,
                            filePath: relPath,
                            isMic,
                        });
                    }
                    catch {
                        // Ignora arquivos que não possam ser lidos
                    }
                }
            }
        }
        if (fs_1.default.existsSync(directoryPath)) {
            await walk(directoryPath);
        }
        this.cachedClasses = results.sort((a, b) => a.simpleClassName.localeCompare(b.simpleClassName));
        return this.cachedClasses;
    }
    static getCachedClasses() {
        return this.cachedClasses;
    }
}
exports.JavaScanner = JavaScanner;
