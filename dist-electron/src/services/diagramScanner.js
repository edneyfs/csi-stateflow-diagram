"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.DiagramScanner = void 0;
const fs_1 = __importDefault(require("fs"));
const path_1 = __importDefault(require("path"));
class DiagramScanner {
    static async scanWorkspaceDiagrams(workspacePath) {
        const diagrams = [];
        async function walk(dir) {
            const entries = await fs_1.default.promises.readdir(dir, { withFileTypes: true });
            for (const entry of entries) {
                const fullPath = path_1.default.join(dir, entry.name);
                if (entry.isDirectory()) {
                    if (entry.name !== '.git' &&
                        entry.name !== 'node_modules' &&
                        entry.name !== 'dist' &&
                        entry.name !== 'bin' &&
                        entry.name !== 'target') {
                        await walk(fullPath);
                    }
                }
                else if (entry.isFile()) {
                    const isDiagram = entry.name.endsWith('.executionflow') ||
                        entry.name.endsWith('.stateflow') ||
                        entry.name.endsWith('.executionflow_diagram') ||
                        entry.name.endsWith('.stateflow_diagram');
                    if (isDiagram) {
                        const relPath = path_1.default.relative(workspacePath, fullPath).replace(/\\/g, '/');
                        const folder = path_1.default.dirname(relPath) === '.' ? '' : path_1.default.dirname(relPath);
                        const isExecution = entry.name.includes('executionflow');
                        diagrams.push({
                            name: entry.name,
                            relativePath: relPath,
                            folder: folder,
                            fullPath: fullPath,
                            type: isExecution ? 'executionflow' : 'stateflow',
                            isDiagramLayout: entry.name.endsWith('_diagram'),
                        });
                    }
                }
            }
        }
        if (fs_1.default.existsSync(workspacePath)) {
            await walk(workspacePath);
        }
        return diagrams.sort((a, b) => a.relativePath.localeCompare(b.relativePath));
    }
}
exports.DiagramScanner = DiagramScanner;
