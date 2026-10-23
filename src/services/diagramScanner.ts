import fs from 'fs';
import path from 'path';

export interface DiagramFileInfo {
  name: string;
  relativePath: string;
  folder: string;
  fullPath: string;
  type: 'executionflow' | 'stateflow';
  isDiagramLayout: boolean;
  hasCompanionLayout?: boolean;
}

export class DiagramScanner {
  public static async scanWorkspaceDiagrams(workspacePath: string): Promise<DiagramFileInfo[]> {
    const diagrams: DiagramFileInfo[] = [];

    async function walk(dir: string) {
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
          const lowerName = entry.name.toLowerCase();
          const isDiagram =
            lowerName.endsWith('.executionflow') ||
            lowerName.endsWith('.stateflow') ||
            lowerName.endsWith('.executionflow_diagram') ||
            lowerName.endsWith('.stateflow_diagram');

          if (isDiagram) {
            const relPath = path.relative(workspacePath, fullPath).replace(/\\/g, '/');
            const folder = path.dirname(relPath) === '.' ? '' : path.dirname(relPath);
            const isExecution = lowerName.includes('executionflow');

            diagrams.push({
              name: entry.name,
              relativePath: relPath,
              folder: folder,
              fullPath: fullPath,
              type: isExecution ? 'executionflow' : 'stateflow',
              isDiagramLayout: lowerName.endsWith('_diagram'),
            });
          }
        }
      }
    }

    if (fs.existsSync(workspacePath)) {
      await walk(workspacePath);
    }

    const allPaths = new Set(diagrams.map((d) => d.relativePath));
    for (const d of diagrams) {
      if (!d.isDiagramLayout) {
        d.hasCompanionLayout = allPaths.has(`${d.relativePath}_diagram`);
      }
    }

    return diagrams.sort((a, b) => a.relativePath.localeCompare(b.relativePath));
  }
}
