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
  selectedNodeId?: string | null;
  editingEdgeId?: string | null;
  onStartEditingEdge?: (edgeId: string) => void;
  onStopEditingEdge?: () => void;
  onContextMenuNode: (event: React.MouseEvent, node: Node) => void;
  onContextMenuEdge?: (event: React.MouseEvent, edge: Edge) => void;
  onDropNewNode?: (type: 'startPoint' | 'executable' | 'subflow' | 'result', position: { x: number; y: number }) => void;
  onDeleteNode?: (nodeId: string) => void;
  onDeleteEdge?: (edgeId: string) => void;
  onUpdateEdgeLabel?: (edgeId: string, newLabel: string) => void;
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
  selectedNodeId,
  editingEdgeId,
  onStartEditingEdge,
  onStopEditingEdge,
  onContextMenuNode,
  onContextMenuEdge,
  onDropNewNode,
  onDeleteNode,
  onDeleteEdge,
  onUpdateEdgeLabel,
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
    (event: React.MouseEvent, node: Node) => {
      event.stopPropagation();
      onSelectNode(node);
      onSelectEdge(null);
    },
    [onSelectNode, onSelectEdge]
  );

  const onEdgeClick = useCallback(
    (event: React.MouseEvent, edge: Edge) => {
      event.stopPropagation();
      onSelectEdge(edge);
      onSelectNode(null);
    },
    [onSelectEdge, onSelectNode]
  );

  const onEdgeContextMenu = useCallback(
    (event: React.MouseEvent, edge: Edge) => {
      event.preventDefault();
      onSelectEdge(edge);
      onSelectNode(null);
      if (onContextMenuEdge) {
        onContextMenuEdge(event, edge);
      }
    },
    [onSelectEdge, onSelectNode, onContextMenuEdge]
  );

  const onSelectionChange = useCallback(
    ({ nodes: selNodes, edges: selEdges }: { nodes: Node[]; edges: Edge[] }) => {
      if (selNodes && selNodes.length > 0) {
        onSelectNode(selNodes[0]);
        onSelectEdge(null);
      } else if (selEdges && selEdges.length > 0) {
        onSelectEdge(selEdges[0]);
        onSelectNode(null);
      }
    },
    [onSelectNode, onSelectEdge]
  );

  const onPaneClick = useCallback(() => {
    onSelectNode(null);
    onSelectEdge(null);
    if (onStopEditingEdge) {
      onStopEditingEdge();
    }
  }, [onSelectNode, onSelectEdge, onStopEditingEdge]);

  const onNodesDelete = useCallback(
    (deletedNodes: Node[]) => {
      if (onDeleteNode) {
        deletedNodes.forEach((n) => onDeleteNode(n.id));
      }
    },
    [onDeleteNode]
  );

  const onEdgesDelete = useCallback(
    (deletedEdges: Edge[]) => {
      if (onDeleteEdge) {
        deletedEdges.forEach((e) => onDeleteEdge(e.id));
      }
    },
    [onDeleteEdge]
  );

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

  // Injeta flag selected para nós e arestas para sincronia total
  const mappedNodes = useMemo(() => {
    if (!selectedNodeId) return nodes;
    return nodes.map((n) => ({
      ...n,
      selected: n.id === selectedNodeId,
    }));
  }, [nodes, selectedNodeId]);

  const mappedEdges = useMemo(() => {
    return edges.map((e) => ({
      ...e,
      selected: e.id === selectedEdgeId,
      data: {
        ...e.data,
        isEditingInline: editingEdgeId === e.id,
        onStartInlineEdit: () => {
          if (onStartEditingEdge) onStartEditingEdge(e.id);
        },
        onStopInlineEdit: () => {
          if (onStopEditingEdge) onStopEditingEdge();
        },
        onSelect: () => {
          onSelectEdge(e);
          onSelectNode(null);
        },
        onContextMenu: (evt: React.MouseEvent) => {
          evt.preventDefault();
          evt.stopPropagation();
          onSelectEdge(e);
          onSelectNode(null);
          if (onContextMenuEdge) {
            onContextMenuEdge(evt, e);
          }
        },
        onUpdateLabel: (newLabel: string) => {
          if (onUpdateEdgeLabel) {
            onUpdateEdgeLabel(e.id, newLabel);
          }
        },
      },
    }));
  }, [
    edges,
    selectedEdgeId,
    editingEdgeId,
    onStartEditingEdge,
    onStopEditingEdge,
    onSelectEdge,
    onSelectNode,
    onContextMenuEdge,
    onUpdateEdgeLabel,
  ]);

  return (
    <div
      className="w-full h-full relative bg-slate-950"
      onDragOver={onDragOver}
      onDrop={onDrop}
    >
      <ReactFlow
        nodes={mappedNodes}
        edges={mappedEdges}
        onNodesChange={onNodesChange}
        onEdgesChange={onEdgesChange}
        onConnect={onConnect}
        onNodeClick={onNodeClick}
        onEdgeClick={onEdgeClick}
        onNodeContextMenu={onContextMenuNode}
        onEdgeContextMenu={onEdgeContextMenu}
        onPaneClick={onPaneClick}
        onSelectionChange={onSelectionChange}
        onNodesDelete={onNodesDelete}
        onEdgesDelete={onEdgesDelete}
        deleteKeyCode={['Backspace', 'Delete']}
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
