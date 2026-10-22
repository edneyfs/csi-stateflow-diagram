import React from 'react';
import { Handle, Position } from '@xyflow/react';

export function StartPointNode() {
  return (
    <div className="flex flex-col items-center group">
      <div className="w-8 h-8 rounded-full bg-black border-2 border-slate-300 shadow-md flex items-center justify-center transition-transform group-hover:scale-105">
        <div className="w-2.5 h-2.5 rounded-full bg-slate-200" />
      </div>
      <span className="text-[10px] font-semibold text-slate-400 mt-1">Start Point</span>
      <Handle
        type="source"
        position={Position.Right}
        className="!w-2.5 !h-2.5 !bg-slate-400 !border-black"
      />
    </div>
  );
}
