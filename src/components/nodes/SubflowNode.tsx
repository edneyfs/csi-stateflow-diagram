import React from 'react';
import { Handle, Position } from '@xyflow/react';

interface SubflowNodeProps {
  data: {
    title: string;
    id: string;
    pathKey: string;
    filePath?: string;
  };
  selected?: boolean;
}

export function SubflowNode({ data, selected }: SubflowNodeProps) {
  return (
    <div
      className={`px-4 py-2.5 rounded-md bg-[#FFF2CC] border-2 text-slate-900 shadow-md min-w-[170px] transition-all ${
        selected ? 'border-amber-600 ring-2 ring-amber-400/50 scale-[1.02]' : 'border-[#D6B656] hover:border-amber-500'
      }`}
    >
      <Handle
        type="target"
        position={Position.Left}
        className="!w-2.5 !h-2.5 !bg-[#D6B656] !border-white"
      />

      <div className="flex items-center gap-2">
        <span className="text-amber-700 font-bold text-xs select-none">🗂</span>
        <div className="flex flex-col">
          <span className="font-semibold text-xs text-[#7A6014] tracking-tight">{data.title || data.id}</span>
          <span className="text-[9px] font-sans text-amber-800/70">Subfluxo de Execução</span>
        </div>
      </div>

      <Handle
        type="source"
        position={Position.Right}
        className="!w-2.5 !h-2.5 !bg-[#D6B656] !border-white"
      />
    </div>
  );
}
