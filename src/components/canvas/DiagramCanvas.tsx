import React, { useCallback, useMemo } from 'react';
import {
  ReactFlow,
  Background,
  Controls,
  MiniMap,
  useNodesState,
  useEdgesState,
  addEdge,
  Connection,
  Edge,
  Node,
  MarkerType,
} from '@xyflow/react';
import '@xyflow/react/dist/style.css';

import { StartPointNode } from '../nodes/StartPointNode';
import { ExecutableNode } from '../nodes/ExecutableNode';
import { SubflowNode } from '../nodes/SubflowNode';
import { ResultNode } from '../nodes/ResultNode';
import { StickyNoteNode } from '../nodes/StickyNoteNode';
import { OrthogonalEdge } from '../edges/OrthogonalEdge';

interface DiagramCanvasProps {
  initialNodes: Node[];
  initialEdges: Edge[];
  onSelectNode: (node: Node | null) => void;
  onContextMenuNode: (event: React.MouseEvent, node: Node) => void;
}

export function DiagramCanvas({
  initialNodes,
  initialEdges,
  onSelectNode,
  onContextMenuNode,
}: DiagramCanvasProps) {
  const [nodes, setNodes, onNodesChange] = useNodesState(initialNodes);
  const [edges, setEdges, onEdgesChange] = useEdgesState(initialEdges);

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

  const onConnect = useCallback(
    (params: Connection) => {
      const newEdge: Edge = {
        ...params,
        id: `e-${params.source}-${params.target}-${Date.now()}`,
        type: 'orthogonal',
        markerEnd: { type: MarkerType.ArrowClosed, color: '#94a3b8' },
        data: { label: 'DEFAULT' },
      };
      setEdges((eds) => addEdge(newEdge, eds));
    },
    [setEdges]
  );

  const onNodeClick = useCallback(
    (_: React.MouseEvent, node: Node) => {
      onSelectNode(node);
    },
    [onSelectNode]
  );

  const onPaneClick = useCallback(() => {
    onSelectNode(null);
  }, [onSelectNode]);

  return (
    <div className="w-full h-full relative bg-slate-950">
      <ReactFlow
        nodes={nodes}
        edges={edges}
        onNodesChange={onNodesChange}
        onEdgesChange={onEdgesChange}
        onConnect={onConnect}
        onNodeClick={onNodeClick}
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
