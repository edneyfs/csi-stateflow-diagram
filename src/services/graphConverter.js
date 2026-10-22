class GraphConverter {
  /**
   * Converte um DiagramModel em nós e arestas do React Flow com posicionamento automático
   */
  static modelToGraph(model) {
    if (model.type === 'executionflow') {
      return this.executionFlowToGraph(model);
    } else {
      return this.stateFlowToGraph(model);
    }
  }

  static executionFlowToGraph(model) {
    const nodes = [];
    const edges = [];

    // 1. Nó de Início
    if (model.startPoint) {
      nodes.push({
        id: 'start-point',
        type: 'startPoint',
        position: { x: 50, y: 150 },
        data: {},
      });
    }

    // 2. Ações Mic (Executables)
    for (const exec of model.executableFlowObjects) {
      nodes.push({
        id: exec.pathKey,
        type: 'executable',
        position: { x: 0, y: 0 },
        data: {
          title: exec.simpleClassName || exec.className.split('.').pop() || '',
          className: exec.className,
          pathKey: exec.pathKey,
        },
      });
    }

    // 3. Subfluxos
    for (const sub of model.executionFlowObjects || []) {
      nodes.push({
        id: sub.pathKey,
        type: 'subflow',
        position: { x: 0, y: 0 },
        data: {
          title: sub.title || sub.id,
          id: sub.id,
          pathKey: sub.pathKey,
          fileName: sub.fileName,
          filePath: sub.filePath,
        },
      });
    }

    // 4. Resultados
    for (const res of model.results || []) {
      nodes.push({
        id: res.pathKey,
        type: 'result',
        position: { x: 0, y: 0 },
        data: {
          title: res.executionFlowResultValue,
          pathKey: res.pathKey,
        },
      });
    }

    // 5. Conexão do StartPoint
    if (model.startPointConnection && model.startPointConnection.destinationObjectPathKey) {
      edges.push({
        id: `e-start-${model.startPointConnection.destinationObjectPathKey}`,
        source: 'start-point',
        target: model.startPointConnection.destinationObjectPathKey,
        type: 'orthogonal',
        markerEnd: { type: 'arrowclosed', color: '#64748b' },
        data: { isStartLink: true },
      });
    }

    // 6. Conexões de fluxo
    (model.flowConnections || []).forEach((conn, index) => {
      edges.push({
        id: `e-${conn.sourcePathKey}-${conn.destinationPathKey}-${index}`,
        source: conn.sourcePathKey,
        target: conn.destinationPathKey,
        type: 'orthogonal',
        markerEnd: { type: 'arrowclosed', color: '#94a3b8' },
        data: { label: conn.executionFlowResultValue || 'DEFAULT' },
      });
    });

    this.applyAutoLayout(nodes, edges);
    return { nodes, edges };
  }

  static stateFlowToGraph(model) {
    const nodes = [];
    const edges = [];

    // 1. Início
    if (model.startPoint) {
      nodes.push({
        id: 'start-point',
        type: 'startPoint',
        position: { x: 50, y: 150 },
        data: {},
      });
    }

    // 2. Estados
    for (const st of model.states || []) {
      nodes.push({
        id: st.pathKey,
        type: 'executable',
        position: { x: 0, y: 0 },
        data: {
          title: st.id,
          description: st.description,
          pathKey: st.pathKey,
        },
      });
    }

    // 3. Execution Flows dentro do StateFlow
    for (const ef of model.executionFlows || []) {
      nodes.push({
        id: ef.pathKey,
        type: 'subflow',
        position: { x: 0, y: 0 },
        data: {
          title: ef.title || ef.id,
          id: ef.id,
          pathKey: ef.pathKey,
          filePath: ef.filePath,
        },
      });
    }

    // 4. Start Point Connection
    if (model.startPointConnection && model.startPointConnection.destinationStateId) {
      const destState = (model.states || []).find((s) => s.id === model.startPointConnection.destinationStateId);
      if (destState) {
        edges.push({
          id: `e-start-${destState.pathKey}`,
          source: 'start-point',
          target: destState.pathKey,
          type: 'orthogonal',
          markerEnd: { type: 'arrowclosed', color: '#64748b' },
          data: { isStartLink: true },
        });
      }
    }

    // 5. State Connections
    (model.stateConnections || []).forEach((conn, index) => {
      edges.push({
        id: `e-sc-${conn.sourcePathKey}-${conn.destinationPathKey}-${index}`,
        source: conn.sourcePathKey,
        target: conn.destinationPathKey,
        type: 'orthogonal',
        markerEnd: { type: 'arrowclosed', color: '#94a3b8' },
        data: { label: conn.eventID },
      });
    });

    // 6. Flow Connections
    (model.flowConnections || []).forEach((conn, index) => {
      edges.push({
        id: `e-fc-${conn.sourcePathKey}-${conn.destinationPathKey}-${index}`,
        source: conn.sourcePathKey,
        target: conn.destinationPathKey,
        type: 'orthogonal',
        markerEnd: { type: 'arrowclosed', color: '#94a3b8' },
        data: { label: conn.executionFlowResultValue || 'DEFAULT' },
      });
    });

    this.applyAutoLayout(nodes, edges);
    return { nodes, edges };
  }

  /**
   * Layout automático horizontal em camadas (DAG Layout)
   */
  static applyAutoLayout(nodes, edges) {
    const depths = new Map();
    depths.set('start-point', 0);

    const adj = new Map();
    for (const edge of edges) {
      if (!adj.has(edge.source)) adj.set(edge.source, []);
      adj.get(edge.source).push(edge.target);
    }

    // BFS para calcular a profundidade de cada nó a partir de start-point
    const queue = [{ id: 'start-point', depth: 0 }];
    const visited = new Set(['start-point']);

    while (queue.length > 0) {
      const { id, depth } = queue.shift();
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
    const levels = new Map();
    for (const node of nodes) {
      const d = depths.get(node.id) || 0;
      if (!levels.has(d)) levels.set(d, []);
      levels.get(d).push(node);
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

module.exports = { GraphConverter };
