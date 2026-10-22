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
                        const content = await fs_1.default.promises.readFile(fullPath, 'utf-8');
                        const pkgMatch = content.match(/package\s+([\w\.]+);/);
                        const classMatch = content.match(/public\s+(?:abstract\s+)?class\s+(\w+)/);
                        if (classMatch) {
                            const simpleName = classMatch[1];
                            const pkg = pkgMatch ? pkgMatch[1] : '';
                            const fullName = pkg ? `${pkg}.${simpleName}` : simpleName;
                            results.push({
                                className: fullName,
                                simpleClassName: simpleName,
                                packageName: pkg,
                                filePath: fullPath,
                                isMic: simpleName.startsWith('Mic'),
                            });
                        }
                    }
                    catch {
                        // Ignora arquivos ilegíveis
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
