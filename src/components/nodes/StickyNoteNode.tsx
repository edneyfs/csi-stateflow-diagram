import React, { useState } from 'react';

interface StickyNoteProps {
  data: {
    text: string;
  };
}

export function StickyNoteNode({ data }: StickyNoteProps) {
  const [text, setText] = useState(data.text || '');

  return (
    <div className="w-64 p-3 bg-[#FFF9C4] border border-[#FBC02D] text-[#5D4037] rounded-md shadow-lg font-sans">
      <div className="font-bold text-[10px] text-[#8D6E63] uppercase tracking-wider mb-1 flex items-center justify-between border-b border-amber-300 pb-1">
        <span>Nota Técnica</span>
        <span>📌</span>
      </div>
      <textarea
        value={text}
        onChange={(e) => setText(e.target.value)}
        className="w-full h-24 bg-transparent resize-none border-none focus:outline-none text-xs text-[#5D4037] leading-relaxed"
        placeholder="Escreva sua observação aqui..."
      />
    </div>
  );
}
