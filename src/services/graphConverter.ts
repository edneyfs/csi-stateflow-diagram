import { MarkerType } from '@xyflow/react';
import type { Node, Edge } from '@xyflow/react';
import type { DiagramModel, ExecutionFlowModel, StateFlowModel } from '../types/model';

export class GraphConverter {
  /**
   * Converte um DiagramModel em nós e arestas do React Flow com posicionamento automático
   * ou coordenadas visuais originais extraídas de arquivo .diagram
   */
  public static modelToGraph(
    model: DiagramModel,
    notationPositions?: Map<string, { x: number; y: number }>
  ): { nodes: Node[]; edges: Edge[] } {
    if (model.type === 'executionflow') {
      return this.executionFlowToGraph(model, notationPositions);
    } else {
      return this.stateFlowToGraph(model, notationPositions);
    }
  }

  private static executionFlowToGraph(
    model: ExecutionFlowModel,
    notationPositions?: Map<string, { x: number; y: number }>
  ): { nodes: Node[]; edges: Edge[] } {
    const nodes: Node[] = [];
    const edges: Edge[] = [];
    const hasNotation = !!(notationPositions && notationPositions.size > 0);

    // 1. Nó de Início
    if (model.startPoint) {
      const pos = notationPositions?.get('startPoint') || { x: 50, y: 150 };
      nodes.push({
        id: 'start-point',
        type: 'startPoint',
        position: pos,
        data: {},
      });
    }

    // 2. Ações Mic (Executables)
    model.executableFlowObjects.forEach((exec, idx) => {
      const key = `executableFlowObjects.${idx}`;
      const pos = notationPositions?.get(key) || { x: 0, y: 0 };
      nodes.push({
        id: exec.pathKey,
        type: 'executable',
        position: pos,
        data: {
          title: exec.simpleClassName || exec.className.split('.').pop() || '',
          className: exec.className,
          pathKey: exec.pathKey,
        },
      });
    });

    // 3. Subfluxos
    model.executionFlowObjects.forEach((sub, idx) => {
      const key = `executionFlowObjects.${idx}`;
      const pos = notationPositions?.get(key) || { x: 0, y: 0 };
      nodes.push({
        id: sub.pathKey,
        type: 'subflow',
        position: pos,
        data: {
          title: sub.title || sub.id,
          id: sub.id,
          pathKey: sub.pathKey,
          fileName: sub.fileName,
          filePath: sub.filePath,
        },
      });
    });

    // 4. Resultados
    model.results.forEach((res, idx) => {
      const key = `results.${idx}`;
      const pos = notationPositions?.get(key) || { x: 0, y: 0 };
      nodes.push({
        id: res.pathKey,
        type: 'result',
        position: pos,
        data: {
          title: res.executionFlowResultValue,
          pathKey: res.pathKey,
        },
      });
    });

    // 5. Conexão do StartPoint
    if (model.startPointConnection?.destinationObjectPathKey) {
      edges.push({
        id: `e-start-${model.startPointConnection.destinationObjectPathKey}`,
        source: 'start-point',
        target: model.startPointConnection.destinationObjectPathKey,
        type: 'orthogonal',
        markerEnd: { type: MarkerType.ArrowClosed, color: '#64748b' },
        data: { isStartLink: true },
      });
    }

    // 6. Conexões de fluxo
    model.flowConnections.forEach((conn, index) => {
      edges.push({
        id: `e-${conn.sourcePathKey}-${conn.destinationPathKey}-${index}`,
        source: conn.sourcePathKey,
        target: conn.destinationPathKey,
        type: 'orthogonal',
        markerEnd: { type: MarkerType.ArrowClosed, color: '#94a3b8' },
        data: { label: conn.executionFlowResultValue || 'DEFAULT' },
      });
    });

    if (!hasNotation) {
      this.applyAutoLayout(nodes, edges);
    }

    return { nodes, edges };
  }

  private static stateFlowToGraph(
    model: StateFlowModel,
    notationPositions?: Map<string, { x: number; y: number }>
  ): { nodes: Node[]; edges: Edge[] } {
    const nodes: Node[] = [];
    const edges: Edge[] = [];
    const hasNotation = !!(notationPositions && notationPositions.size > 0);

    // 1. Início
    if (model.startPoint) {
      const pos = notationPositions?.get('startPoint') || { x: 50, y: 150 };
      nodes.push({
        id: 'start-point',
        type: 'startPoint',
        position: pos,
        data: {},
      });
    }

    // 2. Estados
    model.states.forEach((st, idx) => {
      const key = `states.${idx}`;
      const pos = notationPositions?.get(key) || { x: 0, y: 0 };
      nodes.push({
        id: st.pathKey,
        type: 'executable',
        position: pos,
        data: {
          title: st.id,
          description: st.description,
          pathKey: st.pathKey,
        },
      });
    });

    // 3. Execution Flows dentro do StateFlow
    model.executionFlows.forEach((ef, idx) => {
      const key = `executionFlows.${idx}`;
      const pos = notationPositions?.get(key) || { x: 0, y: 0 };
      nodes.push({
        id: ef.pathKey,
        type: 'subflow',
        position: pos,
        data: {
          title: ef.title || ef.id,
          id: ef.id,
          pathKey: ef.pathKey,
          filePath: ef.filePath,
        },
      });
    });

    // 4. Start Point Connection
    if (model.startPointConnection?.destinationStateId) {
      const destState = model.states.find((s) => s.id === model.startPointConnection?.destinationStateId);
      if (destState) {
        edges.push({
          id: `e-start-${destState.pathKey}`,
          source: 'start-point',
          target: destState.pathKey,
          type: 'orthogonal',
          markerEnd: { type: MarkerType.ArrowClosed, color: '#64748b' },
          data: { isStartLink: true },
        });
      }
    }

    // 5. State Connections
    model.stateConnections.forEach((conn, index) => {
      edges.push({
        id: `e-sc-${conn.sourcePathKey}-${conn.destinationPathKey}-${index}`,
        source: conn.sourcePathKey,
        target: conn.destinationPathKey,
        type: 'orthogonal',
        markerEnd: { type: MarkerType.ArrowClosed, color: '#94a3b8' },
        data: { label: conn.eventID },
      });
    });

    // 6. Flow Connections
    model.flowConnections.forEach((conn, index) => {
      edges.push({
        id: `e-fc-${conn.sourcePathKey}-${conn.destinationPathKey}-${index}`,
        source: conn.sourcePathKey,
        target: conn.destinationPathKey,
        type: 'orthogonal',
        markerEnd: { type: MarkerType.ArrowClosed, color: '#94a3b8' },
        data: { label: conn.executionFlowResultValue || 'DEFAULT' },
      });
    });

    if (!hasNotation) {
      this.applyAutoLayout(nodes, edges);
    }

    return { nodes, edges };
  }

  /**
   * Layout automático horizontal em camadas (DAG Layout)
   */
  private static applyAutoLayout(nodes: Node[], edges: Edge[]): void {
    const depths = new Map<string, number>();
    depths.set('start-point', 0);

    const adj = new Map<string, string[]>();
    for (const edge of edges) {
      if (!adj.has(edge.source)) adj.set(edge.source, []);
      adj.get(edge.source)!.push(edge.target);
    }

    // BFS para calcular a profundidade de cada nó a partir de start-point
    const queue: Array<{ id: string; depth: number }> = [{ id: 'start-point', depth: 0 }];
    const visited = new Set<string>(['start-point']);

    while (queue.length > 0) {
      const { id, depth } = queue.shift()!;
      const neighbors = adj.get(id) || [];
      for (const next of neighbors) {
        const nextDepth = Math.max(depth + 1, depths.get(next) || 0);
        depths.set(next, nextDepth);
        if (!visited.has(next)) {
          visited.add(next);
          queue.push({ id: next, depth: nextDepth });
        }
      }
    }

    // Atribuir profundidade padrão aos nós não conectados
    let maxDepth = 0;
    for (const [, d] of depths) {
      if (d > maxDepth) maxDepth = d;
    }

    for (const node of nodes) {
      if (!depths.has(node.id)) {
        maxDepth += 1;
        depths.set(node.id, maxDepth);
      }
    }

    // Agrupar nós por profundidade para espaçamento vertical
    const levels = new Map<number, Node[]>();
    for (const node of nodes) {
      const d = depths.get(node.id) || 0;
      if (!levels.has(d)) levels.set(d, []);
      levels.get(d)!.push(node);
    }

    // Atribuir coordenadas (x, y)
    for (const [depth, levelNodes] of levels.entries()) {
      const x = 50 + depth * 230;
      levelNodes.forEach((node, idx) => {
        const total = levelNodes.length;
        const startY = 140 - ((total - 1) * 90) / 2;
        const y = startY + idx * 90;
        node.position = { x, y };
      });
    }
  }
}
