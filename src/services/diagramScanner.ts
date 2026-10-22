import fs from 'fs';
import path from 'path';

export interface DiagramFileInfo {
  name: string;
  relativePath: string;
  folder: string;
  fullPath: string;
  type: 'executionflow' | 'stateflow';
  isDiagramLayout: boolean;
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

    return diagrams.sort((a, b) => a.relativePath.localeCompare(b.relativePath));
  }
}
