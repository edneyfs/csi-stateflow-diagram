"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.ReferenceEngine = void 0;
const fs_1 = __importDefault(require("fs"));
const path_1 = __importDefault(require("path"));
const xmlEngine_1 = require("./xmlEngine");
class ReferenceEngine {
    // Mapa em memória: className (ou simpleClassName) -> lista de referências
    static classToDiagrams = new Map();
    /**
     * Indexa todos os arquivos de fluxo (.executionflow e .stateflow) em um diretório
     */
    static async indexWorkspace(workspaceDir) {
        this.classToDiagrams.clear();
        let totalDiagrams = 0;
        async function walk(dir) {
            const entries = await fs_1.default.promises.readdir(dir, { withFileTypes: true });
            for (const entry of entries) {
                const fullPath = path_1.default.join(dir, entry.name);
                if (entry.isDirectory()) {
                    if (entry.name !== '.git' && entry.name !== 'node_modules' && entry.name !== 'dist') {
                        await walk(fullPath);
                    }
                }
                else if (entry.isFile() &&
                    (entry.name.endsWith('.executionflow') || entry.name.endsWith('.stateflow'))) {
                    try {
                        totalDiagrams++;
                        const content = await fs_1.default.promises.readFile(fullPath, 'utf-8');
                        const model = xmlEngine_1.XmlEngine.parse(content);
                        if (model.type === 'executionflow') {
                            const execModel = model;
                            for (const obj of execModel.executableFlowObjects) {
                                const ref = {
                                    diagramPath: fullPath,
                                    diagramName: entry.name,
                                    nodePathKey: obj.pathKey,
                                    simpleClassName: obj.simpleClassName,
                                    className: obj.className,
                                };
                                // Indexa tanto por nome completo quanto por nome simples
                                ReferenceEngine.addRef(obj.simpleClassName, ref);
                                if (obj.className) {
                                    ReferenceEngine.addRef(obj.className, ref);
                                }
                            }
                        }
                    }
                    catch {
                        // Ignora diagramas com erro de sintaxe
                    }
                }
            }
        }
        if (fs_1.default.existsSync(workspaceDir)) {
            await walk(workspaceDir);
        }
        return totalDiagrams;
    }
    static addRef(key, ref) {
        const list = this.classToDiagrams.get(key) || [];
        // Evita duplicidade
        if (!list.some(r => r.diagramPath === ref.diagramPath && r.nodePathKey === ref.nodePathKey)) {
            list.push(ref);
        }
        this.classToDiagrams.set(key, list);
    }
    /**
     * Retorna os diagramas que contêm a classe especificada
     */
    static findReferences(classOrSimpleName) {
        return this.classToDiagrams.get(classOrSimpleName) || [];
    }
}
exports.ReferenceEngine = ReferenceEngine;
