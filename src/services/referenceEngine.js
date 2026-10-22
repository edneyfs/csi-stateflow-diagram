const fs = require('fs');
const path = require('path');
const { XmlEngine } = require('./xmlEngine');

class ReferenceEngine {
  static classToDiagrams = new Map();

  static async indexWorkspace(workspaceDir) {
    this.classToDiagrams.clear();
    let totalDiagrams = 0;

    async function walk(dir) {
      const entries = await fs.promises.readdir(dir, { withFileTypes: true });
      for (const entry of entries) {
        const fullPath = path.join(dir, entry.name);
        if (entry.isDirectory()) {
          if (entry.name !== '.git' && entry.name !== 'node_modules' && entry.name !== 'dist') {
            await walk(fullPath);
          }
        } else if (
          entry.isFile() &&
          (entry.name.endsWith('.executionflow') || entry.name.endsWith('.stateflow'))
        ) {
          try {
            totalDiagrams++;
            const content = await fs.promises.readFile(fullPath, 'utf-8');
            const model = XmlEngine.parse(content);

            if (model.type === 'executionflow') {
              for (const obj of model.executableFlowObjects) {
                const ref = {
                  diagramPath: fullPath,
                  diagramName: entry.name,
                  nodePathKey: obj.pathKey,
                  simpleClassName: obj.simpleClassName,
                  className: obj.className,
                };

                ReferenceEngine.addRef(obj.simpleClassName, ref);
                if (obj.className) {
                  ReferenceEngine.addRef(obj.className, ref);
                }
              }
            }
          } catch {
            // Ignora arquivos com erro
          }
        }
      }
    }

    if (fs.existsSync(workspaceDir)) {
      await walk(workspaceDir);
    }

    return totalDiagrams;
  }

  static addRef(key, ref) {
    const list = this.classToDiagrams.get(key) || [];
    if (!list.some(r => r.diagramPath === ref.diagramPath && r.nodePathKey === ref.nodePathKey)) {
      list.push(ref);
    }
    this.classToDiagrams.set(key, list);
  }

  static findReferences(classOrSimpleName) {
    return this.classToDiagrams.get(classOrSimpleName) || [];
  }
}

module.exports = { ReferenceEngine };
