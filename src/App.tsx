import React, { useState } from 'react';
import {
  FolderOpen,
  Save,
  Search,
  HelpCircle,
  Play,
  Layers,
  Settings,
  Plus,
  FileCode,
  FileText
} from 'lucide-react';

export function App() {
  const [activeTab, setActiveTab] = useState<string>('7026_CONFIRMACAO_AJUSTES.executionflow');
  const [selectedNode, setSelectedNode] = useState<{
    id: string;
    type: string;
    title: string;
    className?: string;
    pathKey?: string;
  } | null>({
    id: '1386704906523',
    type: 'ExecutableFlowObject',
    title: 'MicSalvaDisplay',
    className: 'com.csi.oper.pos.MicSalvaDisplay',
    pathKey: '1386704906523',
  });

  const openHelpPortal = () => {
    window.open('site_suporte/index.html', '_blank');
  };

  return (
    <div className="flex flex-col h-screen w-screen bg-slate-950 text-slate-100 select-none font-sans">
      {/* 1. Header / Top Navigation */}
      <header className="h-12 bg-slate-900 border-b border-slate-800 flex items-center justify-between px-4 z-20">
        <div className="flex items-center gap-3">
          <div className="flex items-center gap-2 font-semibold text-sky-400">
            <Layers className="w-5 h-5" />
            <span className="tracking-wide">CSI StateFlow Studio</span>
          </div>
          <span className="text-xs px-2 py-0.5 rounded bg-sky-950 text-sky-300 border border-sky-800">
            v1.0.0
          </span>
          <div className="h-4 w-[1px] bg-slate-800 mx-2" />
          <nav className="flex items-center gap-1 text-sm text-slate-400">
            <button className="px-2.5 py-1 hover:bg-slate-800 rounded transition">Arquivo</button>
            <button className="px-2.5 py-1 hover:bg-slate-800 rounded transition">Editar</button>
            <button className="px-2.5 py-1 hover:bg-slate-800 rounded transition">Exibir</button>
            <button className="px-2.5 py-1 hover:bg-slate-800 rounded transition">Projeto</button>
          </nav>
        </div>

        <div className="flex items-center gap-2">
          <button
            onClick={() => alert('Seletor de pasta do projeto via IPC')}
            className="flex items-center gap-1.5 px-3 py-1.5 text-xs font-medium rounded-md bg-slate-800 hover:bg-slate-700 text-slate-200 transition border border-slate-700"
          >
            <FolderOpen className="w-3.5 h-3.5 text-sky-400" />
            <span>Abrir Projeto</span>
          </button>
          <button
            onClick={() => alert('Diagrama salvo com sucesso!')}
            className="flex items-center gap-1.5 px-3 py-1.5 text-xs font-medium rounded-md bg-sky-600 hover:bg-sky-500 text-white transition shadow-sm"
          >
            <Save className="w-3.5 h-3.5" />
            <span>Salvar</span>
          </button>
          <button
            onClick={openHelpPortal}
            className="p-1.5 text-slate-400 hover:text-sky-300 hover:bg-slate-800 rounded-md transition"
            title="Abrir Central de Ajuda & Suporte"
          >
            <HelpCircle className="w-4 h-4" />
          </button>
        </div>
      </header>

      {/* 2. Main Workbench (3 Columns Layout) */}
      <div className="flex-1 flex overflow-hidden">
        {/* Left Sidebar: Explorer & Java Classes */}
        <aside className="w-72 bg-slate-900/95 border-r border-slate-800 flex flex-col z-10">
          <div className="p-3 border-b border-slate-800 flex items-center justify-between">
            <span className="text-xs font-bold uppercase tracking-wider text-slate-400">
              Explorador de Arquivos
            </span>
            <button className="p-1 hover:bg-slate-800 rounded text-slate-400 hover:text-slate-200">
              <Plus className="w-4 h-4" />
            </button>
          </div>

          <div className="flex-1 overflow-y-auto p-2 space-y-4">
            <div>
              <div className="text-xs font-semibold text-slate-500 px-2 mb-1 flex items-center gap-1.5">
                <FileCode className="w-3.5 h-3.5 text-sky-400" />
                <span>DIAGRAMAS (.executionflow)</span>
              </div>
              <ul className="text-sm space-y-0.5">
                <li
                  onClick={() => setActiveTab('7026_CONFIRMACAO_AJUSTES.executionflow')}
                  className={`px-2.5 py-1.5 rounded cursor-pointer flex items-center gap-2 text-xs transition ${
                    activeTab === '7026_CONFIRMACAO_AJUSTES.executionflow'
                      ? 'bg-sky-950 text-sky-300 font-medium border border-sky-800/60'
                      : 'text-slate-400 hover:bg-slate-800 hover:text-slate-200'
                  }`}
                >
                  <span className="w-1.5 h-1.5 rounded-full bg-sky-400" />
                  <span className="truncate">7026_CONFIRMACAO_AJUSTES</span>
                </li>
                <li
                  onClick={() => setActiveTab('7025_AJUSTES_DURANTE_VENDA.executionflow')}
                  className={`px-2.5 py-1.5 rounded cursor-pointer flex items-center gap-2 text-xs transition ${
                    activeTab === '7025_AJUSTES_DURANTE_VENDA.executionflow'
                      ? 'bg-sky-950 text-sky-300 font-medium border border-sky-800/60'
                      : 'text-slate-400 hover:bg-slate-800 hover:text-slate-200'
                  }`}
                >
                  <span className="w-1.5 h-1.5 rounded-full bg-slate-600" />
                  <span className="truncate">7025_AJUSTES_DURANTE_VENDA</span>
                </li>
              </ul>
            </div>

            <div>
              <div className="text-xs font-semibold text-slate-500 px-2 mb-1 flex items-center justify-between">
                <div className="flex items-center gap-1.5">
                  <span className="text-amber-400 font-mono text-[11px]">☕</span>
                  <span>CLASSES JAVA (MIC*)</span>
                </div>
                <span className="text-[10px] bg-slate-800 text-slate-400 px-1.5 py-0.2 rounded">5 MICs</span>
              </div>
              <div className="px-2 mb-2">
                <div className="relative">
                  <Search className="w-3.5 h-3.5 text-slate-500 absolute left-2 top-2" />
                  <input
                    type="text"
                    placeholder="Filtrar Mic..."
                    className="w-full bg-slate-950 border border-slate-800 rounded pl-7 pr-2 py-1 text-xs text-slate-200 focus:outline-none focus:border-sky-600"
                  />
                </div>
              </div>
              <ul className="text-xs space-y-0.5">
                {[
                  'MicSalvaDisplay',
                  'MicConfirmaAjustes',
                  'MicSolicitaDadosClienteAjuste',
                  'MicAbandonaAjustes',
                  'MicRecuperaDisplay',
                ].map((mic) => (
                  <li
                    key={mic}
                    className="px-2.5 py-1 rounded text-slate-300 hover:bg-slate-800 hover:text-sky-300 cursor-pointer flex items-center justify-between group"
                    title={`Clique com o botão direito para buscar referências`}
                  >
                    <div className="flex items-center gap-2 truncate">
                      <span className="text-sky-500 text-[10px]">⚙</span>
                      <span className="truncate">{mic}</span>
                    </div>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </aside>

        {/* Center: Canvas Area & Tab Headers */}
        <main className="flex-1 flex flex-col bg-slate-950 relative overflow-hidden">
          {/* Tabs Header */}
          <div className="h-9 bg-slate-900/60 border-b border-slate-800 flex items-center px-2 gap-1 overflow-x-auto">
            <div className="px-3 py-1.5 bg-slate-950 border-t-2 border-sky-500 border-x border-slate-800 rounded-t text-xs font-medium text-slate-200 flex items-center gap-2 shadow-sm">
              <FileText className="w-3.5 h-3.5 text-sky-400" />
              <span>{activeTab}</span>
              <span className="w-1.5 h-1.5 rounded-full bg-amber-400" title="Alterações não salvas" />
            </div>
          </div>

          {/* Interactive Canvas Canvas Mockup (Ready for React Flow) */}
          <div className="flex-1 relative bg-radial-grid overflow-hidden flex items-center justify-center p-8">
            <div className="w-full h-full border border-slate-800/80 rounded-lg bg-slate-950/40 relative p-8 flex flex-col justify-center items-center">
              {/* Visual preview of nodes according to Eclipse screenshot */}
              <div className="flex items-center gap-8 relative flex-wrap justify-center">
                {/* Start Point */}
                <div className="flex flex-col items-center">
                  <div className="w-8 h-8 rounded-full bg-black border-2 border-slate-400 shadow-md flex items-center justify-center cursor-pointer hover:scale-105 transition">
                    <div className="w-2 h-2 rounded-full bg-slate-300" />
                  </div>
                  <span className="text-[10px] text-slate-400 mt-1">Start Point</span>
                </div>

                <div className="w-10 border-t-2 border-dashed border-slate-500" />

                {/* Executable Object 1 */}
                <div
                  onClick={() =>
                    setSelectedNode({
                      id: '1386704906523',
                      type: 'ExecutableFlowObject',
                      title: 'MicSalvaDisplay',
                      className: 'com.csi.oper.pos.MicSalvaDisplay',
                      pathKey: '1386704906523',
                    })
                  }
                  className="px-4 py-2.5 rounded-md bg-[#EAF1FB] border-2 border-[#6C8EBF] text-slate-900 shadow-md cursor-pointer hover:ring-2 hover:ring-sky-400 transition min-w-[140px]"
                >
                  <div className="flex items-center gap-1.5">
                    <span className="text-amber-600 font-bold text-xs">⚙</span>
                    <span className="font-semibold text-xs text-[#1C3D5A]">MicSalvaDisplay</span>
                  </div>
                </div>

                <div className="flex items-center">
                  <div className="w-12 h-0.5 bg-slate-600" />
                  <span className="text-[9px] bg-slate-800 border border-slate-700 text-slate-300 px-1 py-0.2 rounded -ml-7 -mt-5">
                    DEFAULT
                  </span>
                  <div className="w-0 h-0 border-y-4 border-y-transparent border-l-6 border-l-slate-600" />
                </div>

                {/* Executable Object 2 */}
                <div
                  onClick={() =>
                    setSelectedNode({
                      id: '1386704906524',
                      type: 'ExecutableFlowObject',
                      title: 'MicConfirmaAjustes',
                      className: 'com.csi.oper.pos.MicConfirmaAjustes',
                      pathKey: '1386704906524',
                    })
                  }
                  className="px-4 py-2.5 rounded-md bg-[#EAF1FB] border-2 border-[#6C8EBF] text-slate-900 shadow-md cursor-pointer hover:ring-2 hover:ring-sky-400 transition min-w-[140px]"
                >
                  <div className="flex items-center gap-1.5">
                    <span className="text-amber-600 font-bold text-xs">⚙</span>
                    <span className="font-semibold text-xs text-[#1C3D5A]">MicConfirmaAjustes</span>
                  </div>
                </div>

                <div className="flex items-center">
                  <div className="w-12 h-0.5 bg-slate-600" />
                  <span className="text-[9px] bg-slate-800 border border-slate-700 text-slate-300 px-1 py-0.2 rounded -ml-7 -mt-5">
                    DEFAULT
                  </span>
                  <div className="w-0 h-0 border-y-4 border-y-transparent border-l-6 border-l-slate-600" />
                </div>

                {/* Result Node */}
                <div
                  onClick={() =>
                    setSelectedNode({
                      id: '1386704906530',
                      type: 'ExecutionFlowResult',
                      title: 'FINALIZA_AJUSTES',
                      pathKey: '1386704906530',
                    })
                  }
                  className="px-4 py-1.5 rounded-full bg-[#E2F0D9] border-2 border-[#70AD47] text-[#276A3C] shadow-md cursor-pointer hover:ring-2 hover:ring-emerald-400 transition flex items-center gap-2"
                >
                  <span className="text-xs">🚩</span>
                  <span className="font-bold text-xs">FINALIZA_AJUSTES</span>
                </div>
              </div>

              {/* Sticky Note Sample */}
              <div className="absolute bottom-6 left-6 w-60 p-3 bg-[#FFF9C4] border border-[#FBC02D] text-[#5D4037] rounded shadow-lg text-xs font-sans leading-relaxed">
                <div className="font-bold text-[10px] text-[#8D6E63] uppercase tracking-wider mb-1 flex items-center justify-between">
                  <span>Nota Técnica</span>
                  <span>📌</span>
                </div>
                Fluxo de confirmação de ajustes de venda com retorno padrão para finalização.
              </div>
            </div>
          </div>

          {/* Bottom References Panel */}
          <div className="h-32 bg-slate-900/90 border-t border-slate-800 p-3 flex flex-col">
            <div className="flex items-center justify-between text-xs text-slate-400 mb-2">
              <div className="flex items-center gap-2 font-semibold text-slate-300">
                <Search className="w-3.5 h-3.5 text-sky-400" />
                <span>REFERÊNCIAS CRUZADAS (Search ExecutionFlow Diagram References)</span>
              </div>
              <span className="text-[11px] text-slate-500">2 diagramas contêm a classe selecionada</span>
            </div>
            <div className="flex-1 overflow-y-auto space-y-1">
              <div className="text-xs p-1.5 rounded bg-slate-950/60 hover:bg-slate-800 text-slate-300 cursor-pointer flex items-center justify-between border border-slate-800/40">
                <span className="font-mono text-sky-300">pdv/7025_AJUSTES_DURANTE_VENDA.executionflow</span>
                <span className="text-[10px] bg-slate-800 text-slate-400 px-2 py-0.5 rounded">Nó #1386704906523</span>
              </div>
              <div className="text-xs p-1.5 rounded bg-slate-950/60 hover:bg-slate-800 text-slate-300 cursor-pointer flex items-center justify-between border border-slate-800/40">
                <span className="font-mono text-sky-300">pdv/7026_CONFIRMACAO_AJUSTES.executionflow</span>
                <span className="text-[10px] bg-slate-800 text-slate-400 px-2 py-0.5 rounded">Nó #1386704906523</span>
              </div>
            </div>
          </div>
        </main>

        {/* Right Sidebar: Palette & Properties */}
        <aside className="w-80 bg-slate-900/95 border-l border-slate-800 flex flex-col z-10">
          {/* Palette Section */}
          <div className="p-3 border-b border-slate-800">
            <span className="text-xs font-bold uppercase tracking-wider text-slate-400">
              Paleta de Ferramentas
            </span>
            <div className="grid grid-cols-2 gap-2 mt-2">
              <div className="p-2 rounded bg-slate-800/80 border border-slate-700/60 hover:border-sky-500 cursor-pointer flex items-center gap-2 text-xs text-slate-200">
                <div className="w-3.5 h-3.5 rounded-full bg-black border border-slate-400" />
                <span>Start Point</span>
              </div>
              <div className="p-2 rounded bg-slate-800/80 border border-slate-700/60 hover:border-sky-500 cursor-pointer flex items-center gap-2 text-xs text-slate-200">
                <span className="text-amber-500 text-xs">⚙</span>
                <span>Mic Action</span>
              </div>
              <div className="p-2 rounded bg-slate-800/80 border border-slate-700/60 hover:border-sky-500 cursor-pointer flex items-center gap-2 text-xs text-slate-200">
                <span className="text-amber-400 text-xs">🗂</span>
                <span>Subfluxo</span>
              </div>
              <div className="p-2 rounded bg-slate-800/80 border border-slate-700/60 hover:border-sky-500 cursor-pointer flex items-center gap-2 text-xs text-slate-200">
                <span className="text-emerald-400 text-xs">🚩</span>
                <span>Resultado</span>
              </div>
            </div>
          </div>

          {/* Properties Section */}
          <div className="flex-1 flex flex-col p-3 overflow-y-auto">
            <span className="text-xs font-bold uppercase tracking-wider text-slate-400 mb-3 flex items-center gap-1.5">
              <Settings className="w-3.5 h-3.5 text-sky-400" />
              <span>Propriedades do Elemento</span>
            </span>

            {selectedNode ? (
              <div className="space-y-3 text-xs">
                <div>
                  <label className="text-slate-500 text-[10px] uppercase font-semibold">Tipo do Nó</label>
                  <div className="mt-0.5 font-mono text-slate-300 bg-slate-950 p-1.5 rounded border border-slate-800">
                    {selectedNode.type}
                  </div>
                </div>

                <div>
                  <label className="text-slate-500 text-[10px] uppercase font-semibold">Nome da Classe / Rótulo</label>
                  <input
                    type="text"
                    value={selectedNode.title}
                    readOnly
                    className="mt-0.5 w-full font-semibold text-slate-200 bg-slate-950 p-1.5 rounded border border-slate-800 focus:outline-none"
                  />
                </div>

                {selectedNode.className && (
                  <div>
                    <label className="text-slate-500 text-[10px] uppercase font-semibold">Pacote Java Completo</label>
                    <div className="mt-0.5 font-mono text-[11px] text-sky-300 bg-slate-950 p-1.5 rounded border border-slate-800 break-all">
                      {selectedNode.className}
                    </div>
                  </div>
                )}

                <div>
                  <label className="text-slate-500 text-[10px] uppercase font-semibold">Path Key (Epoch ID)</label>
                  <div className="mt-0.5 font-mono text-[11px] text-slate-400 bg-slate-950 p-1.5 rounded border border-slate-800">
                    {selectedNode.pathKey || '1386704906523'}
                  </div>
                </div>

                <div className="pt-2">
                  <button
                    onClick={() => alert(`Buscando referências da classe ${selectedNode.title}...`)}
                    className="w-full py-1.5 rounded bg-sky-950 hover:bg-sky-900 text-sky-300 border border-sky-800 text-xs font-medium transition flex items-center justify-center gap-1.5"
                  >
                    <Search className="w-3 h-3" />
                    <span>Buscar em Outros Diagramas</span>
                  </button>
                </div>
              </div>
            ) : (
              <div className="text-slate-500 text-xs italic mt-4 text-center">
                Selecione um nó no canvas para visualizar e editar suas propriedades.
              </div>
            )}
          </div>
        </aside>
      </div>
    </div>
  );
}
