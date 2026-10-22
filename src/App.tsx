import React, { useState, useMemo, useCallback } from 'react';
import { Node, Edge, MarkerType } from '@xyflow/react';
import {
  FolderOpen,
  Save,
  Search,
  HelpCircle,
  Layers,
  Settings,
  Plus,
  FileCode,
  FileText,
  X,
  ExternalLink,
} from 'lucide-react';
import { DiagramCanvas } from './components/canvas/DiagramCanvas';

// Dados iniciais fiéis à captura de tela 7026_CONFIRMACAO_AJUSTES.executionflow_diagram
const initialNodesData: Node[] = [
  {
    id: 'start-point',
    type: 'startPoint',
    position: { x: 50, y: 150 },
    data: {},
  },
  {
    id: '1386704906523',
    type: 'executable',
    position: { x: 160, y: 135 },
    data: {
      title: 'MicSalvaDisplay',
      className: 'com.csi.oper.pos.MicSalvaDisplay',
      pathKey: '1386704906523',
    },
  },
  {
    id: '1386704906524',
    type: 'executable',
    position: { x: 380, y: 135 },
    data: {
      title: 'MicConfirmaAjustes',
      className: 'com.csi.oper.pos.MicConfirmaAjustes',
      pathKey: '1386704906524',
    },
  },
  {
    id: '1386704906525',
    type: 'executable',
    position: { x: 640, y: 135 },
    data: {
      title: 'MicSolicitaDadosClienteAjuste',
      className: 'com.csi.oper.pos.MicSolicitaDadosClienteAjuste',
      pathKey: '1386704906525',
    },
  },
  {
    id: '1386704906526',
    type: 'executable',
    position: { x: 920, y: 40 },
    data: {
      title: 'MicAbandonaAjustes',
      className: 'com.csi.oper.pos.MicAbandonaAjustes',
      pathKey: '1386704906526',
    },
  },
  {
    id: '1386704906527',
    type: 'executable',
    position: { x: 920, y: 135 },
    data: {
      title: 'MicRecuperaDisplay',
      className: 'com.csi.oper.pos.MicRecuperaDisplay',
      pathKey: '1386704906527',
    },
  },
  {
    id: '1386704906530',
    type: 'result',
    position: { x: 1160, y: 145 },
    data: {
      title: 'FINALIZA_AJUSTES',
      pathKey: '1386704906530',
    },
  },
  {
    id: 'note-1',
    type: 'stickyNote',
    position: { x: 160, y: 260 },
    data: {
      text: 'Fluxo principal de ajuste de venda. Todas as ações gravam no display e tratam exceção de abandono.',
    },
  },
];

const initialEdgesData: Edge[] = [
  {
    id: 'e-start-1',
    source: 'start-point',
    target: '1386704906523',
    type: 'orthogonal',
    markerEnd: { type: MarkerType.ArrowClosed, color: '#64748b' },
    data: { isStartLink: true },
  },
  {
    id: 'e-1-2',
    source: '1386704906523',
    target: '1386704906524',
    type: 'orthogonal',
    markerEnd: { type: MarkerType.ArrowClosed, color: '#94a3b8' },
    data: { label: 'DEFAULT' },
  },
  {
    id: 'e-2-3',
    source: '1386704906524',
    target: '1386704906525',
    type: 'orthogonal',
    markerEnd: { type: MarkerType.ArrowClosed, color: '#94a3b8' },
    data: { label: 'DEFAULT' },
  },
  {
    id: 'e-2-4',
    source: '1386704906524',
    target: '1386704906526',
    type: 'orthogonal',
    markerEnd: { type: MarkerType.ArrowClosed, color: '#94a3b8' },
    data: { label: 'ABANDONAR_AJUSTES' },
  },
  {
    id: 'e-3-4',
    source: '1386704906525',
    target: '1386704906526',
    type: 'orthogonal',
    markerEnd: { type: MarkerType.ArrowClosed, color: '#94a3b8' },
    data: { label: 'ABANDONAR_AJUSTES' },
  },
  {
    id: 'e-2-5',
    source: '1386704906524',
    target: '1386704906527',
    type: 'orthogonal',
    markerEnd: { type: MarkerType.ArrowClosed, color: '#94a3b8' },
    data: { label: 'NAO_HA_AJUSTES' },
  },
  {
    id: 'e-3-5',
    source: '1386704906525',
    target: '1386704906527',
    type: 'orthogonal',
    markerEnd: { type: MarkerType.ArrowClosed, color: '#94a3b8' },
    data: { label: 'DEFAULT' },
  },
  {
    id: 'e-4-5',
    source: '1386704906526',
    target: '1386704906527',
    type: 'orthogonal',
    markerEnd: { type: MarkerType.ArrowClosed, color: '#94a3b8' },
    data: { label: 'DEFAULT' },
  },
  {
    id: 'e-5-res',
    source: '1386704906527',
    target: '1386704906530',
    type: 'orthogonal',
    markerEnd: { type: MarkerType.ArrowClosed, color: '#94a3b8' },
    data: { label: 'DEFAULT' },
  },
];

export function App() {
  const [activeTab, setActiveTab] = useState<string>('7026_CONFIRMACAO_AJUSTES.executionflow');
  const [selectedNode, setSelectedNode] = useState<Node | null>(initialNodesData[1]);
  const [searchFilter, setSearchFilter] = useState('');
  const [micOnly, setMicOnly] = useState(true);

  // Painel de Busca Cruzada ("Search ExecutionFlow Diagram References...")
  const [referenceDrawer, setReferenceDrawer] = useState<{
    visible: boolean;
    className: string;
    results: Array<{ diagramName: string; path: string; nodePathKey: string }>;
  }>({
    visible: false,
    className: '',
    results: [],
  });

  // Menu de Contexto do Nó
  const [contextMenu, setContextMenu] = useState<{
    x: number;
    y: number;
    node: Node | null;
  } | null>(null);

  const javaClasses = useMemo(
    () => [
      { name: 'MicSalvaDisplay', pkg: 'com.csi.oper.pos.MicSalvaDisplay' },
      { name: 'MicConfirmaAjustes', pkg: 'com.csi.oper.pos.MicConfirmaAjustes' },
      { name: 'MicSolicitaDadosClienteAjuste', pkg: 'com.csi.oper.pos.MicSolicitaDadosClienteAjuste' },
      { name: 'MicAbandonaAjustes', pkg: 'com.csi.oper.pos.MicAbandonaAjustes' },
      { name: 'MicRecuperaDisplay', pkg: 'com.csi.oper.pos.MicRecuperaDisplay' },
      { name: 'MicAbreGaveta', pkg: 'com.csi.oper.pos.MicAbreGaveta' },
      { name: 'MicAberturaCupom', pkg: 'com.csi.oper.pos.MicAberturaCupom' },
      { name: 'MicExibeMensagem', pkg: 'com.csi.oper.pos.MicExibeMensagem' },
    ],
    []
  );

  const filteredClasses = useMemo(() => {
    return javaClasses.filter((c) => {
      if (micOnly && !c.name.startsWith('Mic')) return false;
      if (!searchFilter) return true;
      return (
        c.name.toLowerCase().includes(searchFilter.toLowerCase()) ||
        c.pkg.toLowerCase().includes(searchFilter.toLowerCase())
      );
    });
  }, [javaClasses, searchFilter, micOnly]);

  const handleSelectNode = useCallback((node: Node | null) => {
    setSelectedNode(node);
    setContextMenu(null);
  }, []);

  const handleContextMenuNode = useCallback((event: React.MouseEvent, node: Node) => {
    event.preventDefault();
    setSelectedNode(node);
    setContextMenu({
      x: event.clientX,
      y: event.clientY,
      node,
    });
  }, []);

  const handleSearchReferences = (className: string) => {
    setContextMenu(null);
    setReferenceDrawer({
      visible: true,
      className,
      results: [
        {
          diagramName: '7025_AJUSTES_DURANTE_VENDA.executionflow',
          path: 'pdv/7025_AJUSTES_DURANTE_VENDA.executionflow',
          nodePathKey: '1386704906523',
        },
        {
          diagramName: '7026_CONFIRMACAO_AJUSTES.executionflow',
          path: 'pdv/7026_CONFIRMACAO_AJUSTES.executionflow',
          nodePathKey: '1386704906523',
        },
      ],
    });
  };

  const openHelpPortal = () => {
    window.open('site_suporte/index.html', '_blank');
  };

  return (
    <div
      onClick={() => setContextMenu(null)}
      className="flex flex-col h-screen w-screen bg-slate-950 text-slate-100 select-none font-sans overflow-hidden"
    >
      {/* 1. Header / Top Navigation */}
      <header className="h-12 bg-slate-900 border-b border-slate-800 flex items-center justify-between px-4 z-30 shadow-md">
        <div className="flex items-center gap-3">
          <div className="flex items-center gap-2 font-semibold text-sky-400">
            <Layers className="w-5 h-5 text-sky-400" />
            <span className="tracking-wide">CSI StateFlow Studio</span>
          </div>
          <span className="text-xs px-2 py-0.5 rounded-full bg-sky-950 text-sky-300 border border-sky-800 font-mono">
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
            onClick={() => alert('Seletor de pasta do projeto via IPC (Electron)')}
            className="flex items-center gap-1.5 px-3 py-1.5 text-xs font-medium rounded-md bg-slate-800 hover:bg-slate-700 text-slate-200 transition border border-slate-700 shadow-sm"
          >
            <FolderOpen className="w-3.5 h-3.5 text-sky-400" />
            <span>Abrir Projeto</span>
          </button>
          <button
            onClick={() => alert('Diagrama salvo em formato XML legado (.executionflow)!')}
            className="flex items-center gap-1.5 px-3 py-1.5 text-xs font-medium rounded-md bg-sky-600 hover:bg-sky-500 text-white transition shadow-sm font-semibold"
          >
            <Save className="w-3.5 h-3.5" />
            <span>Salvar XML</span>
          </button>
          <button
            onClick={openHelpPortal}
            className="flex items-center gap-1.5 px-2.5 py-1.5 text-xs text-slate-300 hover:text-white bg-slate-800/80 hover:bg-slate-700 rounded-md transition border border-slate-700"
            title="Abrir Central de Ajuda & Documentação Viva"
          >
            <HelpCircle className="w-4 h-4 text-emerald-400" />
            <span>Ajuda</span>
          </button>
        </div>
      </header>

      {/* 2. Main Workbench (3 Columns Layout) */}
      <div className="flex-1 flex overflow-hidden relative">
        {/* Left Sidebar: Explorer & Java Classes */}
        <aside className="w-72 bg-slate-900/95 border-r border-slate-800 flex flex-col z-20 shadow-lg">
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
                  <span>CLASSES JAVA (SCANNER)</span>
                </div>
                <button
                  onClick={() => setMicOnly(!micOnly)}
                  className={`text-[10px] px-1.5 py-0.2 rounded border transition ${
                    micOnly
                      ? 'bg-amber-950 text-amber-300 border-amber-800'
                      : 'bg-slate-800 text-slate-400 border-slate-700'
                  }`}
                >
                  {micOnly ? 'Filtro MIC Ativo' : 'Todas'}
                </button>
              </div>

              <div className="px-2 mb-2">
                <div className="relative">
                  <Search className="w-3.5 h-3.5 text-slate-500 absolute left-2 top-2" />
                  <input
                    type="text"
                    value={searchFilter}
                    onChange={(e) => setSearchFilter(e.target.value)}
                    placeholder="Filtrar classe Java..."
                    className="w-full bg-slate-950 border border-slate-800 rounded pl-7 pr-2 py-1 text-xs text-slate-200 focus:outline-none focus:border-sky-600 placeholder:text-slate-600"
                  />
                </div>
              </div>

              <ul className="text-xs space-y-0.5">
                {filteredClasses.map((item) => (
                  <li
                    key={item.name}
                    onContextMenu={(e) => {
                      e.preventDefault();
                      handleSearchReferences(item.name);
                    }}
                    className="px-2.5 py-1 rounded text-slate-300 hover:bg-slate-800 hover:text-sky-300 cursor-pointer flex items-center justify-between group"
                    title={`Botão direito para: Search ExecutionFlow Diagram References`}
                  >
                    <div className="flex items-center gap-2 truncate">
                      <span className="text-sky-500 text-[10px]">⚙</span>
                      <span className="truncate font-mono">{item.name}</span>
                    </div>
                    <button
                      onClick={(e) => {
                        e.stopPropagation();
                        handleSearchReferences(item.name);
                      }}
                      className="opacity-0 group-hover:opacity-100 p-0.5 hover:text-sky-300 text-slate-500 transition"
                      title="Buscar referências em diagramas"
                    >
                      <Search className="w-3 h-3" />
                    </button>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </aside>

        {/* Center: Canvas Area & Tab Headers */}
        <main className="flex-1 flex flex-col bg-slate-950 relative overflow-hidden">
          {/* Tabs Header */}
          <div className="h-9 bg-slate-900/60 border-b border-slate-800 flex items-center px-2 gap-1 overflow-x-auto z-10">
            <div className="px-3 py-1.5 bg-slate-950 border-t-2 border-sky-500 border-x border-slate-800 rounded-t text-xs font-medium text-slate-200 flex items-center gap-2 shadow-sm">
              <FileText className="w-3.5 h-3.5 text-sky-400" />
              <span>{activeTab}</span>
              <span className="w-1.5 h-1.5 rounded-full bg-amber-400" title="Alterações não salvas" />
            </div>
          </div>

          {/* Interactive React Flow Canvas */}
          <div className="flex-1 relative overflow-hidden">
            <DiagramCanvas
              initialNodes={initialNodesData}
              initialEdges={initialEdgesData}
              onSelectNode={handleSelectNode}
              onContextMenuNode={handleContextMenuNode}
            />
          </div>

          {/* Bottom References Panel (Replica da tela "procura a classe em outros diagramas.png") */}
          {referenceDrawer.visible && (
            <div className="h-40 bg-slate-900 border-t border-slate-800 p-3 flex flex-col z-20 shadow-2xl transition-all">
              <div className="flex items-center justify-between text-xs text-slate-400 mb-2 border-b border-slate-800 pb-1.5">
                <div className="flex items-center gap-2 font-semibold text-slate-200">
                  <Search className="w-4 h-4 text-sky-400" />
                  <span>
                    SEARCH EXECUTIONFLOW REFERENCES:{' '}
                    <span className="text-sky-300 font-mono font-bold">
                      {referenceDrawer.className}
                    </span>
                  </span>
                </div>
                <div className="flex items-center gap-3">
                  <span className="text-[11px] text-slate-500">
                    {referenceDrawer.results.length} arquivo(s) encontrado(s)
                  </span>
                  <button
                    onClick={() => setReferenceDrawer((prev) => ({ ...prev, visible: false }))}
                    className="p-1 hover:bg-slate-800 rounded text-slate-400 hover:text-slate-200 transition"
                  >
                    <X className="w-3.5 h-3.5" />
                  </button>
                </div>
              </div>
              <div className="flex-1 overflow-y-auto space-y-1">
                {referenceDrawer.results.map((ref, idx) => (
                  <div
                    key={idx}
                    onClick={() => {
                      setActiveTab(ref.diagramName);
                      alert(`Abrindo ${ref.diagramName} e focando nó #${ref.nodePathKey}`);
                    }}
                    className="text-xs p-2 rounded bg-slate-950/70 hover:bg-slate-800 text-slate-300 cursor-pointer flex items-center justify-between border border-slate-800/50 group transition"
                  >
                    <div className="flex items-center gap-2">
                      <FileCode className="w-3.5 h-3.5 text-sky-400" />
                      <span className="font-mono text-sky-300 group-hover:underline">
                        {ref.path}
                      </span>
                    </div>
                    <div className="flex items-center gap-3">
                      <span className="text-[10px] bg-slate-800 text-slate-400 px-2 py-0.5 rounded font-mono">
                        Nó #{ref.nodePathKey}
                      </span>
                      <ExternalLink className="w-3 h-3 text-slate-500 group-hover:text-sky-400" />
                    </div>
                  </div>
                ))}
              </div>
            </div>
          )}
        </main>

        {/* Right Sidebar: Palette & Properties */}
        <aside className="w-80 bg-slate-900/95 border-l border-slate-800 flex flex-col z-20 shadow-lg">
          {/* Palette Section */}
          <div className="p-3 border-b border-slate-800">
            <span className="text-xs font-bold uppercase tracking-wider text-slate-400">
              Paleta de Ferramentas
            </span>
            <div className="grid grid-cols-2 gap-2 mt-2">
              <div className="p-2 rounded bg-slate-800/80 border border-slate-700/60 hover:border-sky-500 cursor-pointer flex items-center gap-2 text-xs text-slate-200 transition shadow-sm">
                <div className="w-3 h-3 rounded-full bg-black border border-slate-400" />
                <span>Start Point</span>
              </div>
              <div className="p-2 rounded bg-slate-800/80 border border-slate-700/60 hover:border-sky-500 cursor-pointer flex items-center gap-2 text-xs text-slate-200 transition shadow-sm">
                <span className="text-amber-500 text-xs">⚙</span>
                <span>Mic Action</span>
              </div>
              <div className="p-2 rounded bg-slate-800/80 border border-slate-700/60 hover:border-sky-500 cursor-pointer flex items-center gap-2 text-xs text-slate-200 transition shadow-sm">
                <span className="text-amber-400 text-xs">🗂</span>
                <span>Subfluxo</span>
              </div>
              <div className="p-2 rounded bg-slate-800/80 border border-slate-700/60 hover:border-sky-500 cursor-pointer flex items-center gap-2 text-xs text-slate-200 transition shadow-sm">
                <span className="text-emerald-400 text-xs">🚩</span>
                <span>Resultado</span>
              </div>
            </div>
          </div>

          {/* Properties Section (Replica fiel de "propriedades.png") */}
          <div className="flex-1 flex flex-col p-3 overflow-y-auto">
            <span className="text-xs font-bold uppercase tracking-wider text-slate-400 mb-3 flex items-center gap-1.5">
              <Settings className="w-3.5 h-3.5 text-sky-400" />
              <span>Propriedades do Elemento</span>
            </span>

            {selectedNode ? (
              <div className="space-y-3 text-xs">
                <div>
                  <label className="text-slate-500 text-[10px] uppercase font-semibold">Tipo do Nó</label>
                  <div className="mt-0.5 font-mono text-slate-300 bg-slate-950 p-2 rounded border border-slate-800">
                    {selectedNode.type === 'executable'
                      ? 'Executable Flow Object'
                      : selectedNode.type === 'result'
                      ? 'Execution Flow Result'
                      : selectedNode.type === 'startPoint'
                      ? 'Start Point'
                      : selectedNode.type}
                  </div>
                </div>

                <div>
                  <label className="text-slate-500 text-[10px] uppercase font-semibold">
                    Simple Class Name / Rótulo
                  </label>
                  <input
                    type="text"
                    value={(selectedNode.data?.title as string) || ''}
                    readOnly
                    className="mt-0.5 w-full font-semibold text-slate-200 bg-slate-950 p-2 rounded border border-slate-800 focus:outline-none"
                  />
                </div>

                {selectedNode.data?.className && (
                  <div>
                    <label className="text-slate-500 text-[10px] uppercase font-semibold">
                      Class Name (Pacote Completo)
                    </label>
                    <div className="mt-0.5 font-mono text-[11px] text-sky-300 bg-slate-950 p-2 rounded border border-slate-800 break-all">
                      {selectedNode.data.className as string}
                    </div>
                  </div>
                )}

                <div>
                  <label className="text-slate-500 text-[10px] uppercase font-semibold">
                    Path Key (Epoch ID Único)
                  </label>
                  <div className="mt-0.5 font-mono text-[11px] text-slate-400 bg-slate-950 p-2 rounded border border-slate-800">
                    {(selectedNode.data?.pathKey as string) || selectedNode.id}
                  </div>
                </div>

                <div className="pt-2">
                  <button
                    onClick={() =>
                      handleSearchReferences(
                        (selectedNode.data?.title as string) || selectedNode.id
                      )
                    }
                    className="w-full py-2 rounded bg-sky-950 hover:bg-sky-900 text-sky-300 border border-sky-800 text-xs font-medium transition flex items-center justify-center gap-1.5 shadow-sm"
                  >
                    <Search className="w-3.5 h-3.5" />
                    <span>Search ExecutionFlow References...</span>
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

      {/* 3. Node Context Menu (Replica fiel de "opcoes do no.png") */}
      {contextMenu && (
        <div
          style={{ top: contextMenu.y, left: contextMenu.x }}
          className="fixed bg-slate-900 border border-slate-700 shadow-2xl rounded-md py-1.5 z-50 text-xs text-slate-200 min-w-[210px] backdrop-blur"
          onClick={(e) => e.stopPropagation()}
        >
          <button
            onClick={() => {
              alert('Nota adicionada ao diagrama!');
              setContextMenu(null);
            }}
            className="w-full px-3 py-1.5 hover:bg-slate-800 flex items-center gap-2 text-left"
          >
            <span>📝</span>
            <span>Add Note</span>
          </button>
          <button
            onClick={() => {
              handleSearchReferences(
                (contextMenu.node?.data?.title as string) || contextMenu.node?.id || ''
              );
            }}
            className="w-full px-3 py-1.5 hover:bg-slate-800 flex items-center gap-2 text-left text-sky-300 font-medium"
          >
            <span>🔍</span>
            <span>Search execution flow diagram references...</span>
          </button>
          <button
            onClick={() => {
              setSelectedNode(contextMenu.node);
              setContextMenu(null);
            }}
            className="w-full px-3 py-1.5 hover:bg-slate-800 flex items-center gap-2 text-left"
          >
            <span>📋</span>
            <span>Show Properties View</span>
          </button>
          <div className="h-[1px] bg-slate-800 my-1" />
          <button
            onClick={() => {
              alert('Elemento removido do modelo');
              setContextMenu(null);
            }}
            className="w-full px-3 py-1.5 hover:bg-red-950 text-red-400 flex items-center gap-2 text-left"
          >
            <span>❌</span>
            <span>Delete from Model</span>
          </button>
        </div>
      )}
    </div>
  );
}
