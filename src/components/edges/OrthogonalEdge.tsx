import React, { useState, useEffect, useRef } from 'react';
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
  const [isEditing, setIsEditing] = useState(false);
  const [editVal, setEditVal] = useState(label);
  const inputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    setEditVal(label);
  }, [label]);

  useEffect(() => {
    if (isEditing && inputRef.current) {
      inputRef.current.focus();
      inputRef.current.select();
    }
  }, [isEditing]);

  const handleSelect = (e: React.MouseEvent) => {
    e.stopPropagation();
    if (typeof (data as any)?.onSelect === 'function') {
      (data as any).onSelect();
    }
  };

  const handleDoubleClick = (e: React.MouseEvent) => {
    e.stopPropagation();
    if (!isStartLink) {
      setIsEditing(true);
      if (typeof (data as any)?.onSelect === 'function') {
        (data as any).onSelect();
      }
    }
  };

  const handleCommit = (newVal?: string) => {
    const finalVal = (newVal !== undefined ? newVal : editVal).trim();
    setIsEditing(false);
    if (finalVal && finalVal !== label && typeof (data as any)?.onUpdateLabel === 'function') {
      (data as any).onUpdateLabel(finalVal);
    }
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      handleCommit();
    } else if (e.key === 'Escape') {
      setEditVal(label);
      setIsEditing(false);
    }
  };

  return (
    <>
      <BaseEdge
        id={id}
        path={edgePath}
        markerEnd={markerEnd}
        interactionWidth={25}
        style={{
          ...style,
          stroke: selected ? '#38bdf8' : isStartLink ? '#64748b' : '#94a3b8',
          strokeWidth: selected ? 3 : 2,
          strokeDasharray: isStartLink ? '4 4' : undefined,
          transition: 'stroke 0.2s, stroke-width 0.2s',
          cursor: 'pointer',
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
            onClick={handleSelect}
            onDoubleClick={handleDoubleClick}
            className={`px-2 py-0.5 rounded text-[11px] font-mono font-semibold shadow-md cursor-pointer transition select-none flex items-center gap-1 ${
              selected
                ? 'bg-sky-600 text-white border-2 border-sky-300 ring-2 ring-sky-400/50 shadow-sky-500/30 scale-105'
                : 'bg-slate-800 border border-slate-600 text-slate-200 hover:border-sky-400 hover:text-sky-300'
            }`}
            title="Clique para selecionar e editar nas propriedades, ou duplo-clique para editar inline"
          >
            {isEditing ? (
              <div
                className="flex items-center gap-1 bg-slate-900 p-1.5 rounded-lg border border-sky-400 shadow-2xl z-50 backdrop-blur-md"
                onClick={(e) => e.stopPropagation()}
              >
                <input
                  ref={inputRef}
                  type="text"
                  value={editVal}
                  onChange={(e) => setEditVal(e.target.value)}
                  onBlur={() => handleCommit()}
                  onKeyDown={handleKeyDown}
                  className="bg-slate-950 text-sky-200 border border-sky-500 rounded px-1.5 py-0.5 text-xs font-mono font-bold outline-none w-32 shadow-inner"
                  placeholder="Nome saída..."
                />
                <div className="flex gap-0.5">
                  {['DEFAULT', 'CONFIRMAR', 'ABANDONAR', 'SUCESSO'].map((preset) => (
                    <button
                      key={preset}
                      type="button"
                      onMouseDown={(e) => {
                        e.preventDefault();
                        e.stopPropagation();
                        setEditVal(preset);
                        handleCommit(preset);
                      }}
                      className="px-1.5 py-0.5 text-[9px] bg-slate-800 hover:bg-sky-600 hover:text-white text-slate-300 rounded font-mono font-semibold transition"
                    >
                      {preset}
                    </button>
                  ))}
                </div>
              </div>
            ) : (
              <span>{label}</span>
            )}
          </div>
        </EdgeLabelRenderer>
      )}
    </>
  );
}
