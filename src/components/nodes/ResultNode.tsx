import React from 'react';
import { Handle, Position } from '@xyflow/react';

interface ResultNodeProps {
  data: {
    title: string;
    pathKey: string;
  };
  selected?: boolean;
}

export function ResultNode({ data, selected }: ResultNodeProps) {
  return (
    <div
      className={`px-4 py-1.5 rounded-full bg-[#E2F0D9] border-2 text-[#276A3C] shadow-md transition-all flex items-center gap-1.5 ${
        selected ? 'border-emerald-600 ring-2 ring-emerald-400/50 scale-[1.02]' : 'border-[#70AD47] hover:border-emerald-500'
      }`}
    >
      <Handle
        type="target"
        position={Position.Left}
        className="!w-2.5 !h-2.5 !bg-[#70AD47] !border-white"
      />
      <span className="text-xs select-none">🚩</span>
      <span className="font-bold text-xs tracking-tight">{data.title}</span>
    </div>
  );
}
