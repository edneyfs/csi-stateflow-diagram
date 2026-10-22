const fs = require('fs');
const path = require('path');

class DiagramScanner {
  /**
   * Varre recursivamente a pasta do workspace procurando TODOS os arquivos de diagramas
   * (.executionflow, .stateflow, .executionflow_diagram, .stateflow_diagram)
   */
  static async scanWorkspaceDiagrams(workspacePath) {
    const diagrams = [];

    async function walk(dir) {
      const entries = await fs.promises.readdir(dir, { withFileTypes: true });
      for (const entry of entries) {
        const fullPath = path.join(dir, entry.name);
        if (entry.isDirectory()) {
          if (
            entry.name !== '.git' &&
            entry.name !== 'node_modules' &&
            entry.name !== 'dist' &&
            entry.name !== 'bin' &&
            entry.name !== 'target'
          ) {
            await walk(fullPath);
          }
        } else if (entry.isFile()) {
          const ext = path.extname(entry.name).toLowerCase();
          const isDiagram =
            entry.name.endsWith('.executionflow') ||
            entry.name.endsWith('.stateflow') ||
            entry.name.endsWith('.executionflow_diagram') ||
            entry.name.endsWith('.stateflow_diagram');

          if (isDiagram) {
            const relPath = path.relative(workspacePath, fullPath).replace(/\\/g, '/');
            const folder = path.dirname(relPath) === '.' ? '' : path.dirname(relPath);
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

    if (fs.existsSync(workspacePath)) {
      await walk(workspacePath);
    }

    // Ordena alfabeticamente por pasta e nome
    return diagrams.sort((a, b) => a.relativePath.localeCompare(b.relativePath));
  }
}

module.exports = { DiagramScanner };
