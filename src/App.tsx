import React, { useState, useMemo, useCallback, useEffect } from 'react';
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
  ChevronRight,
  RefreshCw,
  FolderTree,
  Sliders,
} from 'lucide-react';
import { DiagramCanvas } from './components/canvas/DiagramCanvas';
import type { DiagramFileInfo } from './services/diagramScanner';

// Diagramas padrão pré-carregados para navegação imediata
const defaultMockDiagrams: DiagramFileInfo[] = [
  {
    name: '7026_CONFIRMACAO_AJUSTES.executionflow',
    relativePath: 'configuracao/padrao/diagramas/pdv/7026_CONFIRMACAO_AJUSTES.executionflow',
    folder: 'pdv',
    fullPath: 'pdv/7026_CONFIRMACAO_AJUSTES.executionflow',
    type: 'executionflow',
    isDiagramLayout: false,
  },
  {
    name: '7025_AJUSTES_DURANTE_VENDA.executionflow',
    relativePath: 'configuracao/padrao/diagramas/pdv/7025_AJUSTES_DURANTE_VENDA.executionflow',
    folder: 'pdv',
    fullPath: 'pdv/7025_AJUSTES_DURANTE_VENDA.executionflow',
    type: 'executionflow',
    isDiagramLayout: false,
  },
  {
    name: '7001_ABERTURA_CUPOM.executionflow',
    relativePath: 'configuracao/padrao/diagramas/pdv/7001_ABERTURA_CUPOM.executionflow',
    folder: 'pdv',
    fullPath: 'pdv/7001_ABERTURA_CUPOM.executionflow',
    type: 'executionflow',
    isDiagramLayout: false,
  },
  {
    name: '5244_SUB_FLUXO_DE_RECEBIMENTO.executionflow',
    relativePath: 'configuracao/padrao/diagramas/pdv/5244_SUB_FLUXO_DE_RECEBIMENTO.executionflow',
    folder: 'pdv',
    fullPath: 'pdv/5244_SUB_FLUXO_DE_RECEBIMENTO.executionflow',
    type: 'executionflow',
    isDiagramLayout: false,
  },
  {
    name: '7050_FINALIZACAO_VENDA.executionflow',
    relativePath: 'configuracao/padrao/diagramas/pdv/7050_FINALIZACAO_VENDA.executionflow',
    folder: 'pdv',
    fullPath: 'pdv/7050_FINALIZACAO_VENDA.executionflow',
    type: 'executionflow',
    isDiagramLayout: false,
  },
  {
    name: 'SF_PRINCIPAL_PDV.stateflow',
    relativePath: 'configuracao/padrao/diagramas/pdv/SF_PRINCIPAL_PDV.stateflow',
    folder: 'pdv',
    fullPath: 'pdv/SF_PRINCIPAL_PDV.stateflow',
    type: 'stateflow',
    isDiagramLayout: false,
  },
  {
    name: 'SF_RETAGUARDA_SINCRONISMO.stateflow',
    relativePath: 'configuracao/padrao/diagramas/retaguarda/SF_RETAGUARDA_SINCRONISMO.stateflow',
    folder: 'retaguarda',
    fullPath: 'retaguarda/SF_RETAGUARDA_SINCRONISMO.stateflow',
    type: 'stateflow',
    isDiagramLayout: false,
  },
];

// Nós fiéis à captura 7026_CONFIRMACAO_AJUSTES
const sampleNodes: Node[] = [
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

const sampleEdges: Edge[] = [
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
  // Estado do Workspace selecionado
  const [workspacePath, setWorkspacePath] = useState<string>(() => {
    return localStorage.getItem('csi_workspace_path') || 'C:\\p2k-centauro-trunk';
  });
  const [showWorkspaceModal, setShowWorkspaceModal] = useState<boolean>(false);
  const [workspaceInput, setWorkspaceInput] = useState<string>(workspacePath);

  // Lista de TODOS os diagramas encontrados
  const [diagramList, setDiagramList] = useState<DiagramFileInfo[]>(defaultMockDiagrams);
  const [diagramSearch, setDiagramSearch] = useState<string>('');
  const [diagramTypeFilter, setDiagramTypeFilter] = useState<'all' | 'executionflow' | 'stateflow'>('all');

  // Abas e nós
  const [activeTab, setActiveTab] = useState<string>('7026_CONFIRMACAO_AJUSTES.executionflow');
  const [selectedNode, setSelectedNode] = useState<Node | null>(sampleNodes[1]);

  // Classes Java
  const [javaSearch, setJavaSearch] = useState<string>('');
  const [micOnly, setMicOnly] = useState<boolean>(true);

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

  // Varredura de diagramas via Electron IPC quando o workspace muda
  useEffect(() => {
    if (window.api && workspacePath) {
      window.api.scanDiagrams(workspacePath).then((files) => {
        if (files && files.length > 0) {
          setDiagramList(files);
        }
      });
    }
  }, [workspacePath]);

  // Classes Java mock para autocompletion rápido
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
      { name: 'MicValidaOperador', pkg: 'com.csi.oper.pos.MicValidaOperador' },
    ],
    []
  );

  const filteredClasses = useMemo(() => {
    return javaClasses.filter((c) => {
      if (micOnly && !c.name.startsWith('Mic')) return false;
      if (!javaSearch) return true;
      return (
        c.name.toLowerCase().includes(javaSearch.toLowerCase()) ||
        c.pkg.toLowerCase().includes(javaSearch.toLowerCase())
      );
    });
  }, [javaClasses, javaSearch, micOnly]);

  // Filtro da lista de diagramas no painel esquerdo
  const filteredDiagrams = useMemo(() => {
    return diagramList.filter((d) => {
      if (diagramTypeFilter !== 'all' && d.type !== diagramTypeFilter) return false;
      if (!diagramSearch) return true;
      const term = diagramSearch.toLowerCase();
      return (
        d.name.toLowerCase().includes(term) ||
        d.folder.toLowerCase().includes(term) ||
        d.relativePath.toLowerCase().includes(term)
      );
    });
  }, [diagramList, diagramSearch, diagramTypeFilter]);

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
          path: 'configuracao/padrao/diagramas/pdv/7025_AJUSTES_DURANTE_VENDA.executionflow',
          nodePathKey: '1386704906523',
        },
        {
          diagramName: '7026_CONFIRMACAO_AJUSTES.executionflow',
          path: 'configuracao/padrao/diagramas/pdv/7026_CONFIRMACAO_AJUSTES.executionflow',
          nodePathKey: '1386704906523',
        },
      ],
    });
  };

  const handleChooseWorkspaceFolder = async () => {
    if (window.api) {
      const selected = await window.api.openDirectory();
      if (selected) {
        setWorkspacePath(selected);
        localStorage.setItem('csi_workspace_path', selected);
        setShowWorkspaceModal(false);
      }
    } else {
      setShowWorkspaceModal(true);
    }
  };

  const handleConfirmManualWorkspace = () => {
    if (workspaceInput.trim()) {
      setWorkspacePath(workspaceInput.trim());
      localStorage.setItem('csi_workspace_path', workspaceInput.trim());
      setShowWorkspaceModal(false);
    }
  };

  const openHelpPortal = () => {
    window.open('site_suporte/index.html', '_blank');
  };

  return (
    <div
      onClick={() => setContextMenu(null)}
      className="flex flex-col h-screen w-screen bg-slate-950 text-slate-100 select-none font-sans overflow-hidden"
    >
      {/* 1. Header Superior */}
      <header className="h-12 bg-slate-900 border-b border-slate-800 flex items-center justify-between px-4 z-30 shadow-md">
        <div className="flex items-center gap-3">
          <div className="flex items-center gap-2 font-semibold text-sky-400">
            <Layers className="w-5 h-5 text-sky-400" />
            <span className="tracking-wide">CSI StateFlow Studio</span>
          </div>

          <div className="h-4 w-[1px] bg-slate-800 mx-2" />

          {/* Botão Indicador do Workspace Selecionado */}
          <button
            onClick={() => setShowWorkspaceModal(true)}
            className="flex items-center gap-2 px-3 py-1 rounded bg-slate-800/90 hover:bg-slate-700 text-xs text-slate-300 border border-slate-700 transition"
            title="Clique para trocar a pasta de Workspace"
          >
            <FolderTree className="w-3.5 h-3.5 text-amber-400" />
            <span className="text-slate-400">Workspace:</span>
            <span className="font-mono text-sky-300 font-medium truncate max-w-[280px]">
              {workspacePath || 'Nenhum selecionado'}
            </span>
          </button>
        </div>

        <div className="flex items-center gap-2">
          <button
            onClick={handleChooseWorkspaceFolder}
            className="flex items-center gap-1.5 px-3 py-1.5 text-xs font-medium rounded-md bg-slate-800 hover:bg-slate-700 text-slate-200 transition border border-slate-700 shadow-sm"
          >
            <FolderOpen className="w-3.5 h-3.5 text-sky-400" />
            <span>Trocar Workspace</span>
          </button>
          <button
            onClick={() => alert(`Diagrama ${activeTab} salvo com sucesso no formato XML legado!`)}
            className="flex items-center gap-1.5 px-3 py-1.5 text-xs font-medium rounded-md bg-sky-600 hover:bg-sky-500 text-white transition shadow-sm font-semibold"
          >
            <Save className="w-3.5 h-3.5" />
            <span>Salvar XML</span>
          </button>
          <button
            onClick={openHelpPortal}
            className="flex items-center gap-1.5 px-2.5 py-1.5 text-xs text-slate-300 hover:text-white bg-slate-800/80 hover:bg-slate-700 rounded-md transition border border-slate-700"
            title="Abrir Central de Ajuda & Suporte"
          >
            <HelpCircle className="w-4 h-4 text-emerald-400" />
            <span>Ajuda</span>
          </button>
        </div>
      </header>

      {/* 2. Main Workbench (3 Colunas) */}
      <div className="flex-1 flex overflow-hidden relative">
        {/* Painel Esquerdo: ÁREA DEDICADA DE TODOS OS DIAGRAMAS DO WORKSPACE */}
        <aside className="w-80 bg-slate-900/95 border-r border-slate-800 flex flex-col z-20 shadow-lg">
          {/* Header da Seção de Diagramas */}
          <div className="p-3 border-b border-slate-800 flex items-center justify-between bg-slate-900">
            <div className="flex items-center gap-2">
              <FileCode className="w-4 h-4 text-sky-400" />
              <span className="text-xs font-bold uppercase tracking-wider text-slate-300">
                Todos os Diagramas
              </span>
            </div>
            <span className="text-[11px] px-2 py-0.5 rounded-full bg-sky-950 text-sky-300 font-mono font-bold border border-sky-800">
              {filteredDiagrams.length}
            </span>
          </div>

          {/* Filtros e Busca Rápida de Diagramas */}
          <div className="p-2 border-b border-slate-800/80 bg-slate-950/40 space-y-2">
            <div className="relative">
              <Search className="w-3.5 h-3.5 text-slate-500 absolute left-2.5 top-2" />
              <input
                type="text"
                value={diagramSearch}
                onChange={(e) => setDiagramSearch(e.target.value)}
                placeholder="Buscar diagrama (.executionflow, .stateflow)..."
                className="w-full bg-slate-950 border border-slate-800 rounded pl-8 pr-2 py-1 text-xs text-slate-200 focus:outline-none focus:border-sky-600 placeholder:text-slate-600 font-sans"
              />
            </div>

            {/* Tabs de Filtro de Tipo */}
            <div className="flex items-center gap-1 text-[11px]">
              <button
                onClick={() => setDiagramTypeFilter('all')}
                className={`flex-1 py-1 rounded text-center transition font-medium ${
                  diagramTypeFilter === 'all'
                    ? 'bg-slate-800 text-sky-300 border border-slate-700 shadow-sm'
                    : 'text-slate-400 hover:text-slate-200 hover:bg-slate-900'
                }`}
              >
                Todos ({diagramList.length})
              </button>
              <button
                onClick={() => setDiagramTypeFilter('executionflow')}
                className={`flex-1 py-1 rounded text-center transition font-medium ${
                  diagramTypeFilter === 'executionflow'
                    ? 'bg-sky-950 text-sky-300 border border-sky-800 shadow-sm'
                    : 'text-slate-400 hover:text-slate-200 hover:bg-slate-900'
                }`}
              >
                Execution ({diagramList.filter((d) => d.type === 'executionflow').length})
              </button>
              <button
                onClick={() => setDiagramTypeFilter('stateflow')}
                className={`flex-1 py-1 rounded text-center transition font-medium ${
                  diagramTypeFilter === 'stateflow'
                    ? 'bg-amber-950 text-amber-300 border border-amber-800 shadow-sm'
                    : 'text-slate-400 hover:text-slate-200 hover:bg-slate-900'
                }`}
              >
                State ({diagramList.filter((d) => d.type === 'stateflow').length})
              </button>
            </div>
          </div>

          {/* Lista com TODOS os Arquivos de Diagrama */}
          <div className="flex-1 overflow-y-auto p-2 space-y-1">
            {filteredDiagrams.length === 0 ? (
              <div className="p-4 text-center text-xs text-slate-500 italic">
                Nenhum diagrama encontrado com os filtros atuais.
              </div>
            ) : (
              filteredDiagrams.map((d) => {
                const isActive = activeTab === d.name;
                const isExecution = d.type === 'executionflow';

                return (
                  <div
                    key={d.relativePath}
                    onClick={() => {
                      setActiveTab(d.name);
                    }}
                    className={`p-2 rounded cursor-pointer transition flex flex-col gap-1 border ${
                      isActive
                        ? isExecution
                          ? 'bg-sky-950/80 border-sky-700/80 text-sky-200 shadow-sm'
                          : 'bg-amber-950/80 border-amber-700/80 text-amber-200 shadow-sm'
                        : 'bg-slate-950/30 border-slate-800/40 text-slate-300 hover:bg-slate-800 hover:border-slate-700'
                    }`}
                  >
                    <div className="flex items-center justify-between gap-2">
                      <div className="flex items-center gap-1.5 truncate">
                        <span className={`text-[10px] ${isExecution ? 'text-sky-400' : 'text-amber-400'}`}>
                          {isExecution ? '⚙' : '🔄'}
                        </span>
                        <span className="font-semibold text-xs truncate tracking-tight">{d.name}</span>
                      </div>
                      <span
                        className={`text-[9px] px-1.5 py-0.2 rounded font-mono font-medium uppercase ${
                          isExecution
                            ? 'bg-sky-950 text-sky-400 border border-sky-800/60'
                            : 'bg-amber-950 text-amber-400 border border-amber-800/60'
                        }`}
                      >
                        {isExecution ? 'EXEC' : 'STATE'}
                      </span>
                    </div>

                    <div className="flex items-center gap-1 text-[10px] text-slate-500 font-mono truncate">
                      <span>📁</span>
                      <span className="truncate">{d.folder || 'raiz'}</span>
                    </div>
                  </div>
                );
              })
            )}
          </div>

          {/* Seção Inferior: Classes Java Scanner (Mic*) */}
          <div className="h-48 border-t border-slate-800 flex flex-col bg-slate-900/60">
            <div className="p-2 border-b border-slate-800/80 flex items-center justify-between">
              <div className="flex items-center gap-1.5">
                <span className="text-amber-400 font-mono text-[11px]">☕</span>
                <span className="text-xs font-semibold text-slate-300">Classes Java (MIC*)</span>
              </div>
              <button
                onClick={() => setMicOnly(!micOnly)}
                className={`text-[9px] px-1.5 py-0.2 rounded border transition ${
                  micOnly
                    ? 'bg-amber-950 text-amber-300 border-amber-800'
                    : 'bg-slate-800 text-slate-400 border-slate-700'
                }`}
              >
                {micOnly ? 'MIC*' : 'Todas'}
              </button>
            </div>

            <div className="px-2 pt-1.5">
              <input
                type="text"
                value={javaSearch}
                onChange={(e) => setJavaSearch(e.target.value)}
                placeholder="Filtrar classe Java..."
                className="w-full bg-slate-950 border border-slate-800 rounded px-2 py-0.5 text-xs text-slate-200 focus:outline-none focus:border-sky-600 placeholder:text-slate-600 font-mono"
              />
            </div>

            <ul className="flex-1 overflow-y-auto p-2 space-y-0.5 text-xs">
              {filteredClasses.map((item) => (
                <li
                  key={item.name}
                  onContextMenu={(e) => {
                    e.preventDefault();
                    handleSearchReferences(item.name);
                  }}
                  className="px-2 py-0.5 rounded text-slate-300 hover:bg-slate-800 hover:text-sky-300 cursor-pointer flex items-center justify-between group font-mono text-[11px]"
                  title="Botão direito para Search ExecutionFlow Diagram References"
                >
                  <span className="truncate">{item.name}</span>
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
        </aside>

        {/* Centro: Canvas e Header de Abas */}
        <main className="flex-1 flex flex-col bg-slate-950 relative overflow-hidden">
          {/* Abas */}
          <div className="h-9 bg-slate-900/60 border-b border-slate-800 flex items-center px-2 gap-1 overflow-x-auto z-10">
            <div className="px-3 py-1.5 bg-slate-950 border-t-2 border-sky-500 border-x border-slate-800 rounded-t text-xs font-medium text-slate-200 flex items-center gap-2 shadow-sm">
              <FileText className="w-3.5 h-3.5 text-sky-400" />
              <span>{activeTab}</span>
              <span className="w-1.5 h-1.5 rounded-full bg-emerald-400" title="Ativo no canvas" />
            </div>
          </div>

          {/* Interactive React Flow Canvas */}
          <div className="flex-1 relative overflow-hidden">
            <DiagramCanvas
              initialNodes={sampleNodes}
              initialEdges={sampleEdges}
              onSelectNode={handleSelectNode}
              onContextMenuNode={handleContextMenuNode}
            />
          </div>

          {/* Painel Inferior de Referências Cruzadas */}
          {referenceDrawer.visible && (
            <div className="h-44 bg-slate-900 border-t border-slate-800 p-3 flex flex-col z-20 shadow-2xl transition-all">
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

        {/* Painel Direito: Paleta & Propriedades */}
        <aside className="w-80 bg-slate-900/95 border-l border-slate-800 flex flex-col z-20 shadow-lg">
          {/* Paleta */}
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

          {/* Propriedades */}
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

      {/* 3. Modal de Seleção de Workspace na Abertura */}
      {showWorkspaceModal && (
        <div className="fixed inset-0 bg-slate-950/80 backdrop-blur-sm z-50 flex items-center justify-center p-4">
          <div className="w-full max-w-lg bg-slate-900 border border-slate-700 rounded-xl shadow-2xl p-6 space-y-4">
            <div className="flex items-center justify-between border-b border-slate-800 pb-3">
              <div className="flex items-center gap-2">
                <FolderOpen className="w-5 h-5 text-sky-400" />
                <h3 className="font-bold text-slate-100 text-base">Selecionar Workspace do Projeto</h3>
              </div>
              <button
                onClick={() => setShowWorkspaceModal(false)}
                className="text-slate-400 hover:text-white"
              >
                <X className="w-4 h-4" />
              </button>
            </div>

            <p className="text-xs text-slate-300 leading-relaxed">
              Informe ou selecione a pasta raiz do projeto. O estúdio irá varrer recursivamente e listar{' '}
              <strong>TODOS</strong> os arquivos de diagramas (<code className="text-sky-300">.executionflow</code> e{' '}
              <code className="text-sky-300">.stateflow</code>), além das classes Java executáveis (<code className="text-amber-300">MIC*</code>).
            </p>

            <div className="space-y-1.5">
              <label className="text-[11px] font-semibold text-slate-400 uppercase tracking-wider">
                Caminho da Pasta do Projeto
              </label>
              <div className="flex gap-2">
                <input
                  type="text"
                  value={workspaceInput}
                  onChange={(e) => setWorkspaceInput(e.target.value)}
                  placeholder="Ex: C:\workspace\p2k-centauro-trunk"
                  className="flex-1 bg-slate-950 border border-slate-700 rounded-md px-3 py-2 text-xs text-slate-100 focus:outline-none focus:border-sky-500 font-mono"
                />
                <button
                  onClick={handleChooseWorkspaceFolder}
                  className="px-3 py-2 bg-slate-800 hover:bg-slate-700 border border-slate-600 rounded-md text-xs font-medium text-slate-200 transition flex items-center gap-1.5"
                >
                  <FolderOpen className="w-4 h-4 text-sky-400" />
                  <span>Procurar</span>
                </button>
              </div>
            </div>

            <div className="pt-2 flex items-center justify-between border-t border-slate-800">
              <button
                onClick={() => {
                  setWorkspaceInput('C:\\p2k-centauro-trunk');
                  setWorkspacePath('C:\\p2k-centauro-trunk');
                  localStorage.setItem('csi_workspace_path', 'C:\\p2k-centauro-trunk');
                  setShowWorkspaceModal(false);
                }}
                className="text-xs text-sky-400 hover:underline"
              >
                Usar pasta padrão de demonstração
              </button>
              <div className="flex gap-2">
                <button
                  onClick={() => setShowWorkspaceModal(false)}
                  className="px-4 py-1.5 rounded-md text-xs text-slate-400 hover:text-white"
                >
                  Cancelar
                </button>
                <button
                  onClick={handleConfirmManualWorkspace}
                  className="px-4 py-1.5 rounded-md bg-sky-600 hover:bg-sky-500 text-white text-xs font-semibold shadow-sm transition"
                >
                  Confirmar Workspace
                </button>
              </div>
            </div>
          </div>
        </div>
      )}

      {/* 4. Menu de Contexto do Nó */}
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
