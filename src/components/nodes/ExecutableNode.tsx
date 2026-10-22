import React from 'react';
import { Handle, Position } from '@xyflow/react';

interface ExecutableNodeProps {
  data: {
    title: string;
    className?: string;
    pathKey: string;
  };
  selected?: boolean;
}

export function ExecutableNode({ data, selected }: ExecutableNodeProps) {
  return (
    <div
      className={`px-4 py-2.5 rounded-md bg-[#EAF1FB] border-2 text-slate-900 shadow-md min-w-[150px] transition-all ${
        selected ? 'border-sky-500 ring-2 ring-sky-400/50 scale-[1.02]' : 'border-[#6C8EBF] hover:border-sky-400'
      }`}
    >
      <Handle
        type="target"
        position={Position.Left}
        className="!w-2.5 !h-2.5 !bg-[#6C8EBF] !border-white"
      />
      
      <div className="flex items-center gap-2">
        <span className="text-amber-600 font-bold text-xs select-none">⚙</span>
        <div className="flex flex-col">
          <span className="font-semibold text-xs text-[#1C3D5A] tracking-tight">{data.title}</span>
          {data.className && (
            <span className="text-[9px] font-mono text-slate-500 truncate max-w-[180px]">
              {data.className}
            </span>
          )}
        </div>
      </div>

      <Handle
        type="source"
        position={Position.Right}
        className="!w-2.5 !h-2.5 !bg-[#6C8EBF] !border-white"
      />
    </div>
  );
}
