import fs from 'fs';
import path from 'path';
import { XmlEngine } from './xmlEngine';
import { ExecutionFlowModel } from '../types/model';

export interface DiagramReference {
  diagramPath: string;
  diagramName: string;
  nodePathKey: string;
  simpleClassName: string;
  className: string;
}

export class ReferenceEngine {
  // Mapa em memória: className (ou simpleClassName) -> lista de referências
  private static classToDiagrams = new Map<string, DiagramReference[]>();

  /**
   * Indexa todos os arquivos de fluxo (.executionflow e .stateflow) em um diretório
   */
  public static async indexWorkspace(workspaceDir: string): Promise<number> {
    this.classToDiagrams.clear();
    let totalDiagrams = 0;

    async function walk(dir: string) {
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
              const execModel = model as ExecutionFlowModel;
              for (const obj of execModel.executableFlowObjects) {
                const ref: DiagramReference = {
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
          } catch {
            // Ignora diagramas com erro de sintaxe
          }
        }
      }
    }

    if (fs.existsSync(workspaceDir)) {
      await walk(workspaceDir);
    }

    return totalDiagrams;
  }

  private static addRef(key: string, ref: DiagramReference) {
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
  public static findReferences(classOrSimpleName: string): DiagramReference[] {
    return this.classToDiagrams.get(classOrSimpleName) || [];
  }
}
