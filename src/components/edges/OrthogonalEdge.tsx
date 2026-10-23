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
  selected,
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
        id={id}
        path={edgePath}
        markerEnd={markerEnd}
        style={{
          ...style,
          stroke: selected ? '#38bdf8' : isStartLink ? '#64748b' : '#94a3b8',
          strokeWidth: selected ? 3 : 2,
          strokeDasharray: isStartLink ? '4 4' : undefined,
          transition: 'stroke 0.2s, stroke-width 0.2s',
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
            className={`px-2 py-0.5 rounded text-[11px] font-mono font-semibold shadow-md cursor-pointer transition select-none ${
              selected
                ? 'bg-sky-600 text-white border-2 border-sky-300 ring-2 ring-sky-400/50 shadow-sky-500/30'
                : 'bg-slate-800 border border-slate-600 text-slate-200 hover:border-sky-400 hover:text-sky-300'
            }`}
            title="Clique para selecionar e editar o resultado desta transição"
          >
            {label}
          </div>
        </EdgeLabelRenderer>
      )}
    </>
  );
}
