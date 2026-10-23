import React, { useCallback, useMemo } from 'react';
import {
  ReactFlow,
  Background,
  Controls,
  MiniMap,
  ReactFlowProvider,
  useReactFlow,
  type Connection,
  type Edge,
  type Node,
  type OnNodesChange,
  type OnEdgesChange,
} from '@xyflow/react';
import '@xyflow/react/dist/style.css';

import { StartPointNode } from '../nodes/StartPointNode';
import { ExecutableNode } from '../nodes/ExecutableNode';
import { SubflowNode } from '../nodes/SubflowNode';
import { ResultNode } from '../nodes/ResultNode';
import { StickyNoteNode } from '../nodes/StickyNoteNode';
import { OrthogonalEdge } from '../edges/OrthogonalEdge';

export interface DiagramCanvasProps {
  nodes: Node[];
  edges: Edge[];
  onNodesChange: OnNodesChange;
  onEdgesChange: OnEdgesChange;
  onConnect: (connection: Connection) => void;
  onSelectNode: (node: Node | null) => void;
  onSelectEdge: (edge: Edge | null) => void;
  selectedEdgeId?: string | null;
  onContextMenuNode: (event: React.MouseEvent, node: Node) => void;
  onDropNewNode?: (type: 'startPoint' | 'executable' | 'subflow' | 'result', position: { x: number; y: number }) => void;
}

function DiagramCanvasInternal({
  nodes,
  edges,
  onNodesChange,
  onEdgesChange,
  onConnect,
  onSelectNode,
  onSelectEdge,
  selectedEdgeId,
  onContextMenuNode,
  onDropNewNode,
}: DiagramCanvasProps) {
  const { screenToFlowPosition } = useReactFlow();

  const nodeTypes = useMemo(
    () => ({
      startPoint: StartPointNode,
      executable: ExecutableNode,
      subflow: SubflowNode,
      result: ResultNode,
      stickyNote: StickyNoteNode,
    }),
    []
  );

  const edgeTypes = useMemo(
    () => ({
      orthogonal: OrthogonalEdge,
    }),
    []
  );

  const onNodeClick = useCallback(
    (_: React.MouseEvent, node: Node) => {
      onSelectNode(node);
      onSelectEdge(null);
    },
    [onSelectNode, onSelectEdge]
  );

  const onEdgeClick = useCallback(
    (_: React.MouseEvent, edge: Edge) => {
      onSelectEdge(edge);
      onSelectNode(null);
    },
    [onSelectEdge, onSelectNode]
  );

  const onPaneClick = useCallback(() => {
    onSelectNode(null);
    onSelectEdge(null);
  }, [onSelectNode, onSelectEdge]);

  const onDragOver = useCallback((event: React.DragEvent) => {
    event.preventDefault();
    event.dataTransfer.dropEffect = 'move';
  }, []);

  const onDrop = useCallback(
    (event: React.DragEvent) => {
      event.preventDefault();
      const type = event.dataTransfer.getData('application/reactflow') as
        | 'startPoint'
        | 'executable'
        | 'subflow'
        | 'result';

      if (!type || !onDropNewNode) return;

      const position = screenToFlowPosition({
        x: event.clientX,
        y: event.clientY,
      });

      onDropNewNode(type, position);
    },
    [screenToFlowPosition, onDropNewNode]
  );

  // Injeta flag selected para a aresta clicada
  const mappedEdges = useMemo(() => {
    if (!selectedEdgeId) return edges;
    return edges.map((e) => ({
      ...e,
      selected: e.id === selectedEdgeId,
    }));
  }, [edges, selectedEdgeId]);

  return (
    <div
      className="w-full h-full relative bg-slate-950"
      onDragOver={onDragOver}
      onDrop={onDrop}
    >
      <ReactFlow
        nodes={nodes}
        edges={mappedEdges}
        onNodesChange={onNodesChange}
        onEdgesChange={onEdgesChange}
        onConnect={onConnect}
        onNodeClick={onNodeClick}
        onEdgeClick={onEdgeClick}
        onNodeContextMenu={onContextMenuNode}
        onPaneClick={onPaneClick}
        nodeTypes={nodeTypes}
        edgeTypes={edgeTypes}
        fitView
        className="bg-radial-grid"
      >
        <Background color="#334155" gap={20} size={1} />
        <Controls className="!bg-slate-900 !border-slate-800 !shadow-lg [&>button]:!bg-slate-800 [&>button]:!border-slate-700 [&>button]:!text-slate-200" />
        <MiniMap
          nodeColor={(node) => {
            switch (node.type) {
              case 'startPoint':
                return '#000000';
              case 'executable':
                return '#6C8EBF';
              case 'subflow':
                return '#D6B656';
              case 'result':
                return '#70AD47';
              case 'stickyNote':
                return '#FBC02D';
              default:
                return '#64748b';
            }
          }}
          className="!bg-slate-900/90 !border-slate-800 rounded-lg overflow-hidden shadow-xl"
        />
      </ReactFlow>
    </div>
  );
}

export function DiagramCanvas(props: DiagramCanvasProps) {
  return (
    <ReactFlowProvider>
      <DiagramCanvasInternal {...props} />
    </ReactFlowProvider>
  );
}
