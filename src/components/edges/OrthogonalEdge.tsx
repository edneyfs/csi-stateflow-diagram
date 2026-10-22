import React from 'react';
import {
  BaseEdge,
  EdgeLabelRenderer,
  getSmoothStepPath,
  type EdgeProps,
} from '@xyflow/react';

export function OrthogonalEdge({
  id,
  sourceX,
  sourceY,
  targetX,
  targetY,
  sourcePosition,
  targetPosition,
  style = {},
  data,
  markerEnd,
}: EdgeProps) {
  const [edgePath, labelX, labelY] = getSmoothStepPath({
    sourceX,
    sourceY,
    sourcePosition,
    targetX,
    targetY,
    borderRadius: 8,
  });

  const label = (data?.label as string) || '';
  const isStartLink = data?.isStartLink as boolean;

  return (
    <>
      <BaseEdge
        path={edgePath}
        markerEnd={markerEnd}
        style={{
          ...style,
          stroke: isStartLink ? '#64748b' : '#94a3b8',
          strokeWidth: 2,
          strokeDasharray: isStartLink ? '4 4' : undefined,
        }}
      />
      {label && (
        <EdgeLabelRenderer>
          <div
            style={{
              position: 'absolute',
              transform: `translate(-50%, -50%) translate(${labelX}px,${labelY}px)`,
              pointerEvents: 'all',
            }}
            className="px-1.5 py-0.5 rounded bg-slate-800 border border-slate-600 text-[10px] font-mono font-medium text-slate-200 shadow-sm cursor-pointer hover:border-sky-400 hover:text-sky-300 transition"
          >
            {label}
          </div>
        </EdgeLabelRenderer>
      )}
    </>
  );
}
