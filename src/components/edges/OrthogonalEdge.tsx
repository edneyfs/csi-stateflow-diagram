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
  const isStartLink = Boolean(data?.isStartLink);
  const displayLabel = label || (isStartLink ? '' : 'DEFAULT');
  const isEditingProp = Boolean(data?.isEditingInline);
  const [internalEditing, setInternalEditing] = useState(false);
  const isEditing = isEditingProp || internalEditing;

  const [editVal, setEditVal] = useState(displayLabel);
  const inputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    setEditVal(displayLabel);
  }, [displayLabel]);

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

  const handleContextMenu = (e: React.MouseEvent) => {
    e.preventDefault();
    e.stopPropagation();
    if (typeof (data as any)?.onContextMenu === 'function') {
      (data as any).onContextMenu(e);
    } else if (typeof (data as any)?.onSelect === 'function') {
      (data as any).onSelect();
    }
  };

  const handleStartEditing = () => {
    setInternalEditing(true);
    if (typeof (data as any)?.onStartInlineEdit === 'function') {
      (data as any).onStartInlineEdit();
    }
    if (typeof (data as any)?.onSelect === 'function') {
      (data as any).onSelect();
    }
  };

  const handleStopEditing = () => {
    setInternalEditing(false);
    if (typeof (data as any)?.onStopInlineEdit === 'function') {
      (data as any).onStopInlineEdit();
    }
  };

  const handleDoubleClick = (e: React.MouseEvent) => {
    e.preventDefault();
    e.stopPropagation();
    if (!isStartLink) {
      handleStartEditing();
    }
  };

  const handleCommit = (newVal?: string) => {
    const finalVal = (newVal !== undefined ? newVal : editVal).trim();
    handleStopEditing();
    if (finalVal && finalVal !== displayLabel && typeof (data as any)?.onUpdateLabel === 'function') {
      (data as any).onUpdateLabel(finalVal);
    }
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      e.stopPropagation();
      handleCommit();
    } else if (e.key === 'Escape') {
      e.stopPropagation();
      setEditVal(displayLabel);
      handleStopEditing();
    }
  };

  return (
    <>
      <BaseEdge
        id={id}
        path={edgePath}
        markerEnd={markerEnd}
        interactionWidth={30}
        style={{
          ...style,
          stroke: selected ? '#38bdf8' : isStartLink ? '#64748b' : '#94a3b8',
          strokeWidth: selected ? 3.5 : 2,
          strokeDasharray: isStartLink ? '4 4' : undefined,
          transition: 'stroke 0.2s, stroke-width 0.2s',
          cursor: 'pointer',
        }}
      />
      {displayLabel && (
        <EdgeLabelRenderer>
          <div
            style={{
              position: 'absolute',
              transform: `translate(-50%, -50%) translate(${labelX}px,${labelY}px)`,
              pointerEvents: 'all',
            }}
            onClick={handleSelect}
            onDoubleClick={handleDoubleClick}
            onContextMenu={handleContextMenu}
            onMouseDown={(e) => e.stopPropagation()}
            className={`nodrag nopan px-2.5 py-1 rounded text-[11px] font-mono font-semibold shadow-lg cursor-pointer transition select-none flex items-center gap-1.5 ${
              selected
                ? 'bg-sky-600 text-white border-2 border-sky-300 ring-4 ring-sky-400/40 shadow-sky-500/40 scale-110 z-30'
                : 'bg-slate-900/90 border border-slate-600 text-slate-200 hover:border-sky-400 hover:text-sky-300 hover:bg-slate-800'
            }`}
            title="Duplo clique: editar na tela | Clique direito: opções de edição"
          >
            {isEditing ? (
              <div
                className="nodrag nopan flex items-center gap-1.5 bg-slate-900 p-2 rounded-lg border-2 border-sky-400 shadow-2xl z-50 backdrop-blur-md"
                onClick={(e) => e.stopPropagation()}
                onMouseDown={(e) => e.stopPropagation()}
                onDoubleClick={(e) => e.stopPropagation()}
              >
                <input
                  ref={inputRef}
                  type="text"
                  value={editVal}
                  onChange={(e) => setEditVal(e.target.value)}
                  onBlur={() => handleCommit()}
                  onKeyDown={handleKeyDown}
                  className="bg-slate-950 text-sky-200 border border-sky-500 rounded px-2 py-0.5 text-xs font-mono font-bold outline-none w-36 shadow-inner focus:ring-1 focus:ring-sky-300"
                  placeholder="Nome saída..."
                  autoFocus
                />
                <button
                  type="button"
                  onMouseDown={(e) => {
                    e.preventDefault();
                    e.stopPropagation();
                    handleCommit();
                  }}
                  className="px-2 py-0.5 text-[10px] bg-sky-600 hover:bg-sky-500 text-white rounded font-mono font-bold transition shadow"
                  title="Confirmar"
                >
                  OK
                </button>
                <div className="flex gap-1 border-l border-slate-700 pl-1.5">
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
                      className="px-1.5 py-0.5 text-[9px] bg-slate-800 hover:bg-sky-600 hover:text-white text-slate-300 rounded font-mono font-semibold transition border border-slate-700"
                    >
                      {preset}
                    </button>
                  ))}
                </div>
              </div>
            ) : (
              <span>{displayLabel}</span>
            )}
          </div>
        </EdgeLabelRenderer>
      )}
    </>
  );
}
