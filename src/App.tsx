import React, { useState, useMemo, useCallback, useEffect } from 'react';
import {
  Node,
  Edge,
  MarkerType,
  applyNodeChanges,
  applyEdgeChanges,
  type NodeChange,
  type EdgeChange,
  type Connection,
} from '@xyflow/react';
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
  CheckCircle2,
  AlertTriangle,
  RotateCcw,
  HardDrive,
  Download,
  Trash2,
} from 'lucide-react';
import { DiagramCanvas } from './components/canvas/DiagramCanvas';
import packageJson from '../package.json';
import type { DiagramFileInfo, JavaClassInfo, DiagramReference } from './types/workspace';
import { XmlEngine } from './services/xmlEngine';
import { GraphConverter } from './services/graphConverter';
import { WebFileSystemService } from './services/webFileSystem';
import type { DiagramModel, ExecutionFlowModel } from './types/model';

const initialSampleXml = `<?xml version="1.0" encoding="UTF-8"?>
<com.csi.gmf.executionflow:ExecutionFlow xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:com.csi.gmf.executionflow="http:///com/csi/gmf/executionflow.ecore" id="7026" title="7026_CONFIRMACAO_AJUSTES" description="Confirmacao de Ajustes de Venda" requestAuthorization="false">
  <startPoint/>
  <startPointConnection destinationObjectPathKey="1386704906523"/>
  <executableFlowObjects pathKey="1386704906523" className="com.csi.oper.pos.MicSalvaDisplay" simpleClassName="MicSalvaDisplay"/>
  <executableFlowObjects pathKey="1386704906524" className="com.csi.oper.pos.MicConfirmaAjustes" simpleClassName="MicConfirmaAjustes"/>
  <executableFlowObjects pathKey="1386704906525" className="com.csi.oper.pos.MicSolicitaDadosClienteAjuste" simpleClassName="MicSolicitaDadosClienteAjuste"/>
  <executableFlowObjects pathKey="1386704906526" className="com.csi.oper.pos.MicAbandonaAjustes" simpleClassName="MicAbandonaAjustes"/>
  <executableFlowObjects pathKey="1386704906527" className="com.csi.oper.pos.MicRecuperaDisplay" simpleClassName="MicRecuperaDisplay"/>
  <results pathKey="1386704906528" executionFlowResultValue="FINALIZA_AJUSTES"/>
  <flowObjectsConnections sourcePathKey="1386704906523" destinationPathKey="1386704906524" executionFlowResultValue="DEFAULT"/>
  <flowObjectsConnections sourcePathKey="1386704906524" destinationPathKey="1386704906525" executionFlowResultValue="CONFIRMAR"/>
  <flowObjectsConnections sourcePathKey="1386704906524" destinationPathKey="1386704906526" executionFlowResultValue="ABANDONAR"/>
  <flowObjectsConnections sourcePathKey="1386704906525" destinationPathKey="1386704906527" executionFlowResultValue="DEFAULT"/>
  <flowObjectsConnections sourcePathKey="1386704906526" destinationPathKey="1386704906528" executionFlowResultValue="DEFAULT"/>
  <flowObjectsConnections sourcePathKey="1386704906527" destinationPathKey="1386704906528" executionFlowResultValue="DEFAULT"/>
</com.csi.gmf.executionflow:ExecutionFlow>`;

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
  // Estado do Workspace selecionado (solicita na inicialização se não configurado)
  const [workspacePath, setWorkspacePath] = useState<string>(() => {
    return localStorage.getItem('csi_workspace_path') || 'C:\\p2k-centauro-trunk';
  });
  const [showWorkspaceModal, setShowWorkspaceModal] = useState<boolean>(() => {
    return !localStorage.getItem('csi_workspace_path');
  });
  const [workspaceInput, setWorkspaceInput] = useState<string>(workspacePath);

  // Lista de TODOS os diagramas encontrados no workspace
  const [diagramList, setDiagramList] = useState<DiagramFileInfo[]>(defaultMockDiagrams);
  const [diagramSearch, setDiagramSearch] = useState<string>('');
  const [diagramTypeFilter, setDiagramTypeFilter] = useState<'all' | 'semantic' | 'executionflow' | 'stateflow' | 'layout'>('all');
  const [showLayoutDiagrams, setShowLayoutDiagrams] = useState<boolean>(false);

  // Abas e nós ativos no canvas
  const [activeTab, setActiveTab] = useState<string>('7026_CONFIRMACAO_AJUSTES.executionflow');
  const [currentFileRelativePath, setCurrentFileRelativePath] = useState<string>(
    'configuracao/padrao/diagramas/pdv/7026_CONFIRMACAO_AJUSTES.executionflow'
  );
  const [currentFileFullPath, setCurrentFileFullPath] = useState<string>('');
  const [currentNodes, setCurrentNodes] = useState<Node[]>(sampleNodes);
  const [currentEdges, setCurrentEdges] = useState<Edge[]>(sampleEdges);
  const [selectedNodeId, setSelectedNodeId] = useState<string | null>(sampleNodes[1]?.id || null);
  const [selectedNode, setSelectedNode] = useState<Node | null>(sampleNodes[1]);
  const [selectedEdge, setSelectedEdge] = useState<Edge | null>(null);
  const [isAutocompleteOpen, setIsAutocompleteOpen] = useState<boolean>(false);

  const activeNode = useMemo(() => {
    if (!selectedNodeId) return null;
    return currentNodes.find((n) => n.id === selectedNodeId) || selectedNode;
  }, [currentNodes, selectedNodeId, selectedNode]);

  // Modelo de dados estruturado do diagrama ativo (ExecutionFlow / StateFlow)
  const [currentModel, setCurrentModel] = useState<DiagramModel | null>(() => {
    try {
      return XmlEngine.parse(initialSampleXml);
    } catch {
      return null;
    }
  });

  // Notificações Toast no canto da tela
  const [toast, setToast] = useState<{ message: string; type: 'success' | 'error' | 'info' } | null>(null);

  const showToast = useCallback((message: string, type: 'success' | 'error' | 'info' = 'info') => {
    setToast({ message, type });
  }, []);

  useEffect(() => {
    if (toast) {
      const timer = setTimeout(() => setToast(null), 4500);
      return () => clearTimeout(timer);
    }
  }, [toast]);

  // Pasta persistida no IndexedDB para reconexão em 1 clique
  const [storedDirectoryName, setStoredDirectoryName] = useState<string | null>(null);
  const [isScanningWorkspace, setIsScanningWorkspace] = useState<boolean>(false);

  // Classes Java escaneadas dinamicamente
  const [scannedJavaClasses, setScannedJavaClasses] = useState<Array<{ name: string; pkg: string }>>([]);
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

  // Menu de Contexto do Nó e Transição
  const [contextMenu, setContextMenu] = useState<{
    x: number;
    y: number;
    node: Node | null;
    edge?: Edge | null;
  } | null>(null);

  // Fecha o menu de contexto ao pressionar Escape
  useEffect(() => {
    const handleKeyDown = (e: KeyboardEvent) => {
      if (e.key === 'Escape') {
        setContextMenu(null);
      }
    };
    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, []);

  // Carrega e renderiza um diagrama a partir do arquivo XML (Desktop ou Web)
  // Suporta resolução de layout companion (.executionflow_diagram e .stateflow_diagram)
  const handleOpenDiagram = useCallback(
    async (d: DiagramFileInfo) => {
      // 1. Redirecionamento transparente caso o usuário clique em um arquivo de layout _diagram
      let semanticInfo = d;
      let companionLayoutPath = '';
      let companionLayoutRel = '';

      if (d.isDiagramLayout || d.name.endsWith('_diagram')) {
        const semanticName = d.name.replace(/_diagram$/, '');
        const semanticRel = d.relativePath.replace(/_diagram$/, '');
        const semanticFull = d.fullPath ? d.fullPath.replace(/_diagram$/, '') : '';
        const isExecution = semanticName.includes('executionflow');
        semanticInfo = {
          name: semanticName,
          relativePath: semanticRel,
          folder: d.folder,
          fullPath: semanticFull,
          type: isExecution ? 'executionflow' : 'stateflow',
          isDiagramLayout: false,
          hasCompanionLayout: true,
        };
        companionLayoutPath = d.fullPath || '';
        companionLayoutRel = d.relativePath;
      } else {
        companionLayoutPath = d.fullPath ? `${d.fullPath}_diagram` : '';
        companionLayoutRel = `${d.relativePath}_diagram`;
      }

      setActiveTab(semanticInfo.name);
      setCurrentFileRelativePath(semanticInfo.relativePath);
      setCurrentFileFullPath(semanticInfo.fullPath || '');

      let semanticXml: string | null = null;
      let notationXml: string | null = null;

      // 2. Leitura via Electron IPC
      if (window.api) {
        if (semanticInfo.fullPath) {
          try {
            semanticXml = await window.api.readFile(semanticInfo.fullPath);
          } catch (err) {
            console.warn('Erro ao ler diagrama semântico via IPC:', err);
          }
        }
        if (companionLayoutPath) {
          try {
            notationXml = await window.api.readFile(companionLayoutPath);
          } catch {
            // Arquivo de layout notation é opcional
          }
        }
      }

      // 3. Leitura via File System Access API no navegador
      if (!semanticXml && WebFileSystemService.getCurrentHandle()) {
        try {
          semanticXml = await WebFileSystemService.readFile(semanticInfo.relativePath);
        } catch (err) {
          console.warn('Erro ao ler diagrama via Web File System API:', err);
        }
        if (companionLayoutRel) {
          try {
            notationXml = await WebFileSystemService.readFile(companionLayoutRel);
          } catch {
            // Arquivo de layout notation é opcional
          }
        }
      }

      if (semanticXml) {
        try {
          const model = XmlEngine.parse(semanticXml);
          setCurrentModel(model);

          let notationPositions: Map<string, { x: number; y: number }> | undefined;
          if (notationXml) {
            try {
              notationPositions = XmlEngine.parseNotationBounds(notationXml);
            } catch (boundsErr) {
              console.warn('Falha ao extrair bounds do arquivo .diagram:', boundsErr);
            }
          }

          const { nodes, edges } = GraphConverter.modelToGraph(model, notationPositions);
          setCurrentNodes(nodes);
          setCurrentEdges(edges);
          const initialNode = nodes.find((n) => n.type === 'executable') || nodes[0] || null;
          setSelectedNode(initialNode);
          setSelectedNodeId(initialNode ? initialNode.id : null);

          if (notationPositions && notationPositions.size > 0) {
            showToast(
              `Diagrama ${semanticInfo.name} carregado com layout visual original do Eclipse (${nodes.length} nós)!`,
              'success'
            );
          } else {
            showToast(`Diagrama ${semanticInfo.name} carregado com sucesso (${nodes.length} nós)!`, 'success');
          }
          return;
        } catch (parseErr: any) {
          console.error('Erro no parser do XML:', parseErr);
          showToast(`Erro na estrutura XML de ${semanticInfo.name}: ${parseErr.message || parseErr}`, 'error');
        }
      }

      // Fallback para mock caso não esteja conectado ou ocorra erro
      if (d.name.includes('7026')) {
        setCurrentNodes(sampleNodes);
        setCurrentEdges(sampleEdges);
        setSelectedNode(sampleNodes[1]);
        setSelectedNodeId(sampleNodes[1]?.id || null);
        setCurrentModel(XmlEngine.parse(initialSampleXml));
      }
    },
    [showToast]
  );

  // Varredura unificada e automática do workspace (Diagramas + Classes MIC)
  const scanWorkspace = useCallback(async (target: string | any) => {
    setIsScanningWorkspace(true);
    try {
      if (typeof target === 'string' && window.api) {
        const [files, classes] = await Promise.all([
          window.api.scanDiagrams(target),
          window.api.scanJavaClasses(target),
        ]);
        if (files && files.length > 0) setDiagramList(files);
        if (classes && classes.length > 0) {
          setScannedJavaClasses(
            classes.map((c) => ({
              name: c.simpleClassName || c.className.split('.').pop() || c.className,
              pkg: c.className || c.packageName,
            }))
          );
        }
        const micCount = (classes || []).filter((c) => c.isMic || /^mic/i.test(c.simpleClassName || c.className)).length;
        const semanticCount = (files || []).filter((f) => !f.isDiagramLayout).length;
        const layoutCount = (files || []).filter((f) => f.isDiagramLayout).length;
        showToast(
          `Workspace indexado: ${semanticCount} diagramas (${layoutCount} layouts) e ${micCount} classes MIC localizadas!`,
          'success'
        );
      } else if (target && typeof target === 'object') {
        const [files, classes] = await Promise.all([
          WebFileSystemService.scanDiagrams(target),
          WebFileSystemService.scanJavaClasses(target, true),
        ]);
        if (files && files.length > 0) setDiagramList(files);
        if (classes && classes.length > 0) {
          setScannedJavaClasses(
            classes.map((c) => ({
              name: c.simpleClassName || c.className.split('.').pop() || c.className,
              pkg: c.className || c.packageName,
            }))
          );
        }
        const micCount = (classes || []).filter((c) => c.isMic || /^mic/i.test(c.simpleClassName || c.className)).length;
        const semanticCount = (files || []).filter((f) => !f.isDiagramLayout).length;
        const layoutCount = (files || []).filter((f) => f.isDiagramLayout).length;
        showToast(
          `Workspace indexado: ${semanticCount} diagramas (${layoutCount} layouts) e ${micCount} classes MIC localizadas!`,
          'success'
        );
      }
    } catch (err: any) {
      console.error('Erro na varredura do workspace:', err);
      showToast(`Aviso na varredura: ${err.message || err}`, 'error');
    } finally {
      setIsScanningWorkspace(false);
    }
  }, [showToast]);

  // Inicialização Web: auto-reconecta e varre se permissão já concedida
  useEffect(() => {
    if (!window.api && WebFileSystemService.isSupported()) {
      WebFileSystemService.getStoredDirectory().then(async (handle) => {
        if (handle) {
          try {
            const hasPerm = await (handle as any).queryPermission({ mode: 'readwrite' });
            if (hasPerm === 'granted') {
              WebFileSystemService.setCurrentHandle(handle);
              setWorkspacePath(handle.name);
              setStoredDirectoryName(null);
              scanWorkspace(handle);
              return;
            }
          } catch {
            // Requer interação do usuário
          }
          setStoredDirectoryName(handle.name);
        }
      });
    }
  }, [scanWorkspace]);

  // Inicialização Electron: varre quando o workspacePath muda
  useEffect(() => {
    if (window.api && workspacePath) {
      scanWorkspace(workspacePath);
    }
  }, [workspacePath, scanWorkspace]);

  // Lista combinada de classes Java (mock + varredura real)
  const defaultJavaClasses = useMemo(
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
      { name: 'MicSolicitaBaseACarregar', pkg: 'com.csi.oper.av.MicSolicitaBaseACarregar' },
    ],
    []
  );

  const javaClasses = useMemo(() => {
    if (scannedJavaClasses.length > 0) {
      return scannedJavaClasses;
    }
    return defaultJavaClasses;
  }, [scannedJavaClasses, defaultJavaClasses]);

  // Função de busca com suporte a curingas (ex: Mic**ajustes, Mic*Display, ou partes 'ajustes')
  const matchClassPattern = useCallback((query: string, item: { name: string; pkg: string }) => {
    if (!query) return true;
    const clean = query.trim();
    if (!clean) return true;

    // Se possui asterisco (* ou **), interpreta como wildcard
    if (clean.includes('*')) {
      const regexPattern = clean
        .replace(/[\-\[\]\/\{\}\(\)\+\?\.\\\^\$\|]/g, '\\$&')
        .replace(/\*+/g, '.*');
      try {
        const regex = new RegExp(regexPattern, 'i');
        return regex.test(item.name) || regex.test(item.pkg);
      } catch {
        // Fallback se regex falhar
      }
    }

    // Busca por partes (subpalavras)
    const terms = clean.toLowerCase().split(/\s+/).filter(Boolean);
    const target = `${item.name} ${item.pkg}`.toLowerCase();
    return terms.every((t) => target.includes(t));
  }, []);

  // Sugestões calculadas dinamicamente para o nó Action selecionado (100% estrito MIC*)
  const activeNodeTitle = (activeNode?.data?.title as string) || '';
  const micSuggestions = useMemo(() => {
    if (!activeNode || activeNode.type !== 'executable') return [];
    return javaClasses
      .filter((c) => /^mic/i.test(c.name) && matchClassPattern(activeNodeTitle, c))
      .slice(0, 25);
  }, [activeNode, activeNodeTitle, javaClasses, matchClassPattern]);

  const filteredClasses = useMemo(() => {
    return javaClasses.filter((c) => {
      if (!/^mic/i.test(c.name)) return false;
      if (!javaSearch) return true;
      return (
        matchClassPattern(javaSearch, c) ||
        c.name.toLowerCase().includes(javaSearch.toLowerCase()) ||
        c.pkg.toLowerCase().includes(javaSearch.toLowerCase())
      );
    });
  }, [javaClasses, javaSearch, matchClassPattern]);

  // Filtro da lista de diagramas no painel esquerdo com abas dedicadas
  const filteredDiagrams = useMemo(() => {
    return diagramList.filter((d) => {
      if (diagramTypeFilter === 'semantic' && d.isDiagramLayout) return false;
      if (diagramTypeFilter === 'layout' && !d.isDiagramLayout) return false;
      if (diagramTypeFilter === 'executionflow' && (d.type !== 'executionflow' || d.isDiagramLayout)) return false;
      if (diagramTypeFilter === 'stateflow' && (d.type !== 'stateflow' || d.isDiagramLayout)) return false;

      if (!diagramSearch) return true;
      const term = diagramSearch.toLowerCase();
      return (
        d.name.toLowerCase().includes(term) ||
        d.folder.toLowerCase().includes(term) ||
        d.relativePath.toLowerCase().includes(term)
      );
    });
  }, [diagramList, diagramSearch, diagramTypeFilter]);

  const onNodesChange = useCallback((changes: NodeChange[]) => {
    setCurrentNodes((nds) => applyNodeChanges(changes, nds));
  }, []);

  const onEdgesChange = useCallback((changes: EdgeChange[]) => {
    setCurrentEdges((eds) => applyEdgeChanges(changes, eds));
  }, []);

  const onConnect = useCallback((connection: Connection) => {
    const newEdgeId = `e-${connection.source}-${connection.target}-${Date.now()}`;
    const newEdge: Edge = {
      ...connection,
      id: newEdgeId,
      type: 'orthogonal',
      markerEnd: { type: MarkerType.ArrowClosed, color: '#94a3b8' },
      data: { label: 'DEFAULT' },
    };

    setCurrentEdges((eds) => [...eds, newEdge]);
    setSelectedEdge(newEdge);
    setSelectedNode(null);

    // Sincroniza diretamente com o modelo XML
    setCurrentModel((prev) => {
      if (!prev || prev.type !== 'executionflow') return prev;
      const exec = prev as ExecutionFlowModel;
      const newConns = [
        ...(exec.flowConnections || []),
        {
          sourcePathKey: connection.source,
          destinationPathKey: connection.target,
          executionFlowResultValue: 'DEFAULT',
        },
      ];
      return { ...exec, flowConnections: newConns };
    });

    showToast('Nova transição conectada! Rótulo padrão "DEFAULT" atribuído.', 'info');
  }, [showToast]);

  const handleSelectNode = useCallback((node: Node | null) => {
    setSelectedNodeId(node ? node.id : null);
    setSelectedNode(node);
    setSelectedEdge(null);
    setIsAutocompleteOpen(false);
  }, []);

  const handleSelectEdge = useCallback((edge: Edge | null) => {
    setSelectedEdge(edge);
    setSelectedNodeId(null);
    setSelectedNode(null);
    setIsAutocompleteOpen(false);
  }, []);

  const handleCreateNode = useCallback(
    (type: 'startPoint' | 'executable' | 'subflow' | 'result', position?: { x: number; y: number }) => {
      const pathKey = String(Date.now());
      const pos = position || {
        x: 320 + (currentNodes.length % 6) * 45,
        y: 160 + (currentNodes.length % 6) * 35,
      };

      let newNode: Node;

      if (type === 'startPoint') {
        newNode = {
          id: 'start-point',
          type: 'startPoint',
          position: pos,
          data: {},
        };
        setCurrentModel((prev) => (prev ? { ...prev, startPoint: { exists: true } } : prev));
      } else if (type === 'executable') {
        const sampleClassName = 'com.csi.oper.pos.MicNovaAcao';
        const sampleSimpleName = 'MicNovaAcao';

        newNode = {
          id: pathKey,
          type: 'executable',
          position: pos,
          data: {
            title: sampleSimpleName,
            className: sampleClassName,
            pathKey,
          },
        };

        setCurrentModel((prev) => {
          if (!prev || prev.type !== 'executionflow') return prev;
          const exec = prev as ExecutionFlowModel;
          return {
            ...exec,
            executableFlowObjects: [
              ...(exec.executableFlowObjects || []),
              { pathKey, className: sampleClassName, simpleClassName: sampleSimpleName },
            ],
          };
        });
      } else if (type === 'subflow') {
        newNode = {
          id: pathKey,
          type: 'subflow',
          position: pos,
          data: {
            title: 'Subfluxo_Novo',
            id: 'SUB_' + pathKey.slice(-4),
            pathKey,
          },
        };

        setCurrentModel((prev) => {
          if (!prev || prev.type !== 'executionflow') return prev;
          const exec = prev as ExecutionFlowModel;
          return {
            ...exec,
            executionFlowObjects: [
              ...(exec.executionFlowObjects || []),
              { pathKey, id: 'SUB_' + pathKey.slice(-4), title: 'Subfluxo_Novo' },
            ],
          };
        });
      } else {
        newNode = {
          id: pathKey,
          type: 'result',
          position: pos,
          data: {
            title: 'SUCESSO',
            pathKey,
          },
        };

        setCurrentModel((prev) => {
          if (!prev || prev.type !== 'executionflow') return prev;
          const exec = prev as ExecutionFlowModel;
          return {
            ...exec,
            results: [
              ...(exec.results || []),
              { pathKey, executionFlowResultValue: 'SUCESSO' },
            ],
          };
        });
      }

      setCurrentNodes((nds) => [...nds, newNode]);
      setSelectedNodeId(newNode.id);
      setSelectedNode(newNode);
      setSelectedEdge(null);
      setIsAutocompleteOpen(false);
      showToast(`Elemento "${newNode.data.title || type}" adicionado ao diagrama!`, 'success');
    },
    [currentNodes.length, showToast]
  );

  const handleUpdateEdgeLabelById = useCallback(
    (edgeId: string, newLabel: string, showNotification: boolean = false) => {
      const targetEdge = currentEdges.find((e) => e.id === edgeId) || selectedEdge;
      if (!targetEdge) return;

      const updatedEdge = {
        ...targetEdge,
        data: { ...targetEdge.data, label: newLabel },
      };
      setSelectedEdge(updatedEdge);

      setCurrentEdges((eds) =>
        eds.map((e) => (e.id === edgeId ? updatedEdge : e))
      );

      // Sincroniza com o modelo XML
      setCurrentModel((prev) => {
        if (!prev) return prev;
        if (prev.type === 'executionflow') {
          const exec = prev as ExecutionFlowModel;
          const newConns = exec.flowConnections?.map((conn) => {
            if (
              conn.sourcePathKey === targetEdge.source &&
              conn.destinationPathKey === targetEdge.target
            ) {
              return { ...conn, executionFlowResultValue: newLabel };
            }
            return conn;
          });
          return { ...exec, flowConnections: newConns };
        }
        return prev;
      });

      if (showNotification) {
        showToast(`Saída da transição alterada para "${newLabel}"`, 'success');
      }
    },
    [currentEdges, selectedEdge, showToast]
  );

  const handleUpdateEdgeLabel = useCallback(
    (newLabel: string, showNotification: boolean = false) => {
      if (!selectedEdge) return;
      handleUpdateEdgeLabelById(selectedEdge.id, newLabel, showNotification);
    },
    [selectedEdge, handleUpdateEdgeLabelById]
  );

  const handleDeleteEdge = useCallback(
    (edgeId: string) => {
      const edge = currentEdges.find((e) => e.id === edgeId);
      setCurrentEdges((eds) => eds.filter((e) => e.id !== edgeId));

      if (edge && currentModel?.type === 'executionflow') {
        setCurrentModel((prev) => {
          if (!prev || prev.type !== 'executionflow') return prev;
          const exec = prev as ExecutionFlowModel;
          const newConns = exec.flowConnections?.filter(
            (c) => !(c.sourcePathKey === edge.source && c.destinationPathKey === edge.target)
          );
          return { ...exec, flowConnections: newConns };
        });
      }

      setSelectedEdge(null);
      showToast('Transição removida com sucesso!', 'info');
    },
    [currentEdges, currentModel, showToast]
  );

  const handleDeleteNode = useCallback(
    (nodeId: string) => {
      // 1. Remove da lista de nós visuais
      setCurrentNodes((nds) => nds.filter((n) => n.id !== nodeId));
      // 2. Remove arestas conectadas a este nó
      setCurrentEdges((eds) => eds.filter((e) => e.source !== nodeId && e.target !== nodeId));

      // 3. Remove do modelo XML
      setCurrentModel((prev) => {
        if (!prev) return prev;
        if (prev.type === 'executionflow') {
          const exec = prev as ExecutionFlowModel;
          return {
            ...exec,
            executableFlowObjects: exec.executableFlowObjects?.filter((o) => o.pathKey !== nodeId),
            results: exec.results?.filter((r) => r.pathKey !== nodeId),
            executionFlowObjects: exec.executionFlowObjects?.filter((s) => s.pathKey !== nodeId),
            flowConnections: exec.flowConnections?.filter(
              (c) => c.sourcePathKey !== nodeId && c.destinationPathKey !== nodeId
            ),
            startPoint: nodeId === 'start-point' ? undefined : exec.startPoint,
            startPointConnection:
              exec.startPointConnection?.destinationObjectPathKey === nodeId
                ? undefined
                : exec.startPointConnection,
          };
        } else {
          const sf = prev as any;
          return {
            ...sf,
            states: sf.states?.filter((s: any) => s.pathKey !== nodeId),
            executionFlows: sf.executionFlows?.filter((e: any) => e.pathKey !== nodeId),
            stateConnections: sf.stateConnections?.filter(
              (c: any) => c.sourcePathKey !== nodeId && c.destinationPathKey !== nodeId
            ),
            flowConnections: sf.flowConnections?.filter(
              (c: any) => c.sourcePathKey !== nodeId && c.destinationPathKey !== nodeId
            ),
            startPoint: nodeId === 'start-point' ? undefined : sf.startPoint,
          };
        }
      });

      setSelectedNodeId(null);
      setSelectedNode(null);
      setIsAutocompleteOpen(false);
      showToast('Nó removido do diagrama com sucesso!', 'info');
    },
    [showToast]
  );

  // Atualização em tempo real de propriedades do nó com sincronização bidirecional no modelo XML
  const handleUpdateNodeProperty = useCallback(
    (field: 'title' | 'className', value: string) => {
      const targetNode = activeNode || selectedNode;
      if (!targetNode) return;

      const updatedData = { ...targetNode.data, [field]: value };
      if (field === 'title' && targetNode.type === 'executable') {
        const oldClass = (targetNode.data?.className as string) || '';
        const pkg = oldClass.includes('.') ? oldClass.substring(0, oldClass.lastIndexOf('.')) : '';
        if (pkg && !value.includes('*')) {
          updatedData.className = `${pkg}.${value}`;
        }
      }

      const updatedNode = { ...targetNode, data: updatedData };
      setSelectedNode(updatedNode);
      setSelectedNodeId(targetNode.id);

      // Atualiza na árvore de nós visuais do React Flow
      setCurrentNodes((prev) => prev.map((n) => (n.id === targetNode.id ? updatedNode : n)));

      // Sincroniza diretamente no modelo de dados do XML
      setCurrentModel((prevModel) => {
        if (!prevModel) return prevModel;
        const pathKey = (targetNode.data?.pathKey as string) || targetNode.id;

        if (prevModel.type === 'executionflow') {
          const execModel = prevModel as any;
          const newExecs = execModel.executableFlowObjects?.map((obj: any) => {
            if (obj.pathKey === pathKey) {
              return {
                ...obj,
                simpleClassName: field === 'title' ? value : obj.simpleClassName,
                className: field === 'className' ? value : (updatedData.className as string) || obj.className,
              };
            }
            return obj;
          });

          const newResults = execModel.results?.map((res: any) => {
            if (res.pathKey === pathKey && field === 'title') {
              return { ...res, executionFlowResultValue: value };
            }
            return res;
          });

          return {
            ...execModel,
            executableFlowObjects: newExecs || execModel.executableFlowObjects,
            results: newResults || execModel.results,
          };
        }

        return prevModel;
      });
    },
    [activeNode, selectedNode]
  );

  // Aplicação de classe Java completa sugerida pelo Autocomplete
  const handleSetNodeClass = useCallback(
    (simpleName: string, fullName: string) => {
      const targetNode = activeNode || selectedNode;
      if (!targetNode) return;

      const updatedData = {
        ...targetNode.data,
        title: simpleName,
        className: fullName,
      };
      const updatedNode = { ...targetNode, data: updatedData };
      setSelectedNode(updatedNode);
      setSelectedNodeId(targetNode.id);

      setCurrentNodes((prev) => prev.map((n) => (n.id === targetNode.id ? updatedNode : n)));

      setCurrentModel((prevModel) => {
        if (!prevModel || prevModel.type !== 'executionflow') return prevModel;
        const execModel = prevModel as ExecutionFlowModel;
        const pathKey = (targetNode.data?.pathKey as string) || targetNode.id;

        const newExecs = execModel.executableFlowObjects?.map((obj) => {
          if (obj.pathKey === pathKey) {
            return {
              ...obj,
              simpleClassName: simpleName,
              className: fullName,
            };
          }
          return obj;
        });

        return {
          ...execModel,
          executableFlowObjects: newExecs || execModel.executableFlowObjects,
        };
      });
    },
    [activeNode, selectedNode]
  );

  const handleContextMenuNode = useCallback((event: React.MouseEvent, node: Node) => {
    event.preventDefault();
    setSelectedNode(node);
    setSelectedEdge(null);
    setContextMenu({
      x: event.clientX,
      y: event.clientY,
      node,
      edge: null,
    });
  }, []);

  const handleContextMenuEdge = useCallback((event: React.MouseEvent, edge: Edge) => {
    event.preventDefault();
    setSelectedEdge(edge);
    setSelectedNode(null);
    setSelectedNodeId(null);
    setContextMenu({
      x: event.clientX,
      y: event.clientY,
      node: null,
      edge,
    });
  }, []);

  const handleSearchReferences = async (className: string) => {
    setContextMenu(null);
    if (window.api && workspacePath) {
      try {
        const refs = await window.api.findReferences(workspacePath, className);
        if (refs && refs.length > 0) {
          setReferenceDrawer({
            visible: true,
            className,
            results: refs.map((r) => ({
              diagramName: r.diagramName,
              path: r.diagramPath,
              nodePathKey: r.nodePathKey,
            })),
          });
          return;
        }
      } catch (err) {
        console.warn('Busca nativa de referências via IPC:', err);
      }
    } else if (WebFileSystemService.getCurrentHandle()) {
      try {
        const refs = await WebFileSystemService.findReferences(className);
        if (refs && refs.length > 0) {
          setReferenceDrawer({
            visible: true,
            className,
            results: refs.map((r) => ({
              diagramName: r.diagramName,
              path: r.diagramPath,
              nodePathKey: r.nodePathKey,
            })),
          });
          showToast(`${refs.length} referências encontradas para "${className}"`, 'info');
          return;
        } else {
          showToast(`Nenhuma referência encontrada para "${className}" nos diagramas.`, 'info');
        }
      } catch (err: any) {
        showToast(`Erro na busca de referências: ${err.message || err}`, 'error');
      }
    }

    // Demonstração com dados da referência real de 7025 e 7026
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

  const handleReconnectWorkspace = async () => {
    try {
      const handle = await WebFileSystemService.reconnectStoredDirectory();
      if (handle) {
        setWorkspacePath(handle.name);
        setStoredDirectoryName(null);
        await scanWorkspace(handle);
      } else {
        showToast('Permissão de acesso à pasta não foi autorizada.', 'error');
      }
    } catch (err: any) {
      showToast(`Falha ao reconectar pasta: ${err.message || err}`, 'error');
    }
  };

  const handleChooseWorkspaceFolder = async () => {
    if (window.api) {
      const selected = await window.api.openDirectory();
      if (selected) {
        setWorkspacePath(selected);
        localStorage.setItem('csi_workspace_path', selected);
        setShowWorkspaceModal(false);
        await scanWorkspace(selected);
      }
    } else if (WebFileSystemService.isSupported()) {
      try {
        const handle = await WebFileSystemService.selectDirectory();
        if (handle) {
          setWorkspacePath(handle.name);
          setStoredDirectoryName(null);
          localStorage.setItem('csi_workspace_path', handle.name);
          setShowWorkspaceModal(false);
          await scanWorkspace(handle);
        }
      } catch (err: any) {
        showToast(`Erro ao selecionar pasta: ${err.message || err}`, 'error');
      }
    } else {
      setShowWorkspaceModal(true);
    }
  };

  const handleSaveXml = async () => {
    if (!currentModel) {
      showToast('Nenhum modelo de diagrama ativo para salvar.', 'error');
      return;
    }

    try {
      const xml = XmlEngine.serialize(currentModel);

      // 1. Electron Desktop
      if (window.api) {
        let targetPath = currentFileFullPath;
        if (!targetPath) {
          if (workspacePath && currentFileRelativePath) {
            targetPath = `${workspacePath.replace(/[\\/]+$/, '')}/${currentFileRelativePath.replace(/^[\\/]+/, '')}`;
          } else {
            targetPath = currentFileRelativePath || activeTab;
          }
        }
        await window.api.writeFile(targetPath, xml);
        showToast(`Diagrama ${activeTab} salvo no disco via Desktop!`, 'success');
        return;
      }

      // 2. Navegador com File System Access API
      if (WebFileSystemService.getCurrentHandle()) {
        const targetRelPath = currentFileRelativePath || activeTab;
        const res = await WebFileSystemService.writeFile(targetRelPath, xml, undefined, true);
        if (res.backupPath) {
          showToast(`Diagrama salvo com sucesso! Backup criado em ${res.backupPath}`, 'success');
        } else {
          showToast(`Diagrama ${activeTab} salvo com sucesso no disco!`, 'success');
        }
        return;
      }

      // 3. Fallback: Download via navegador se nenhuma pasta local estiver conectada
      const blob = new Blob([xml.replace(/\r?\n/g, '\r\n')], { type: 'application/xml;charset=utf-8' });
      const url = URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = activeTab;
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
      URL.revokeObjectURL(url);
      showToast(`Arquivo ${activeTab} baixado pelo navegador!`, 'info');
    } catch (err: any) {
      console.error('Erro ao salvar XML:', err);
      showToast(`Erro ao salvar XML: ${err.message || err}`, 'error');
    }
  };

  const handleConfirmManualWorkspace = () => {
    if (workspaceInput.trim()) {
      setWorkspacePath(workspaceInput.trim());
      localStorage.setItem('csi_workspace_path', workspaceInput.trim());
      setShowWorkspaceModal(false);
      if (!window.api && WebFileSystemService.isSupported()) {
        handleChooseWorkspaceFolder();
      }
    }
  };

  const openHelpPortal = () => {
    const basePath = import.meta.env.BASE_URL || './';
    const cleanBase = basePath.endsWith('/') ? basePath : basePath + '/';
    window.open(cleanBase + 'site_suporte/index.html', '_blank');
  };

  return (
    <div
      className="flex flex-col h-screen w-screen bg-slate-950 text-slate-100 select-none font-sans overflow-hidden"
    >
      {/* 1. Header Superior */}
      <header className="h-12 bg-slate-900 border-b border-slate-800 flex items-center justify-between px-4 z-30 shadow-md">
        <div className="flex items-center gap-3">
          <div className="flex items-center gap-2 font-semibold text-sky-400">
            <Layers className="w-5 h-5 text-sky-400" />
            <span className="tracking-wide">CSI StateFlow Studio</span>
            <span className="text-[11px] font-mono font-medium px-1.5 py-0.5 rounded bg-sky-950/80 text-sky-300 border border-sky-800/60 shadow-inner select-none" title={`Versão do Sistema: ${packageJson.version}`}>
              v.{packageJson.version}
            </span>
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
            onClick={handleSaveXml}
            className="flex items-center gap-1.5 px-3 py-1.5 text-xs font-medium rounded-md bg-sky-600 hover:bg-sky-500 text-white transition shadow-sm font-semibold"
            title="Salvar XML no disco local (com backup automático .bak)"
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

      {/* Banner de Reconexão de Workspace ou Seleção Inicial na Web */}
      {!window.api && !WebFileSystemService.getCurrentHandle() && (
        <div className="bg-sky-950/80 border-b border-sky-700/60 px-4 py-2 flex items-center justify-between text-xs text-sky-200 z-20 shadow-inner">
          <div className="flex items-center gap-2">
            <HardDrive className="w-4 h-4 text-sky-400 animate-pulse" />
            <span>
              {storedDirectoryName ? (
                <>
                  Pasta memorizada da sua sessão anterior: <strong className="text-white font-mono">{storedDirectoryName}</strong>
                </>
              ) : (
                <>
                  Modo Web puro ativo. Conecte a pasta raiz do projeto para carregar todos os 573 diagramas e 2.269 classes MIC.
                </>
              )}
            </span>
          </div>
          <div className="flex items-center gap-2">
            {storedDirectoryName && (
              <button
                onClick={handleReconnectWorkspace}
                className="flex items-center gap-1.5 px-3 py-1 bg-sky-600 hover:bg-sky-500 text-white rounded text-xs font-semibold shadow transition"
              >
                <RotateCcw className="w-3.5 h-3.5" />
                <span>Reconectar com 1 Clique</span>
              </button>
            )}
            <button
              onClick={handleChooseWorkspaceFolder}
              className="flex items-center gap-1.5 px-3 py-1 bg-slate-800 hover:bg-slate-700 text-slate-100 border border-slate-600 rounded text-xs font-semibold shadow transition"
            >
              <FolderOpen className="w-3.5 h-3.5 text-sky-400" />
              <span>Selecionar Pasta do Workspace</span>
            </button>
            {storedDirectoryName && (
              <button
                onClick={async () => {
                  await WebFileSystemService.clearStoredDirectory();
                  setStoredDirectoryName(null);
                  showToast('Pasta esquecida do armazenamento do navegador.', 'info');
                }}
                className="px-2 py-1 text-slate-400 hover:text-slate-200 text-xs transition"
                title="Esquecer pasta salva"
              >
                Esquecer
              </button>
            )}
          </div>
        </div>
      )}

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

            {/* Tabs de Filtro de Tipo com Contadores Reais */}
            <div className="flex flex-wrap items-center gap-1 text-[10px]">
              <button
                onClick={() => setDiagramTypeFilter('all')}
                className={`px-2 py-1 rounded text-center transition font-medium ${
                  diagramTypeFilter === 'all'
                    ? 'bg-slate-800 text-sky-300 border border-slate-700 shadow-sm'
                    : 'text-slate-400 hover:text-slate-200 hover:bg-slate-900'
                }`}
                title="Exibir todos os arquivos do workspace (fluxos + layouts)"
              >
                Todos ({diagramList.length})
              </button>
              <button
                onClick={() => setDiagramTypeFilter('semantic')}
                className={`px-2 py-1 rounded text-center transition font-medium ${
                  diagramTypeFilter === 'semantic'
                    ? 'bg-sky-950 text-sky-300 border border-sky-800 shadow-sm'
                    : 'text-slate-400 hover:text-slate-200 hover:bg-slate-900'
                }`}
                title="Exibir apenas os fluxos sem arquivos de layout duplicados"
              >
                Fluxos ({diagramList.filter((d) => !d.isDiagramLayout).length})
              </button>
              <button
                onClick={() => setDiagramTypeFilter('executionflow')}
                className={`px-2 py-1 rounded text-center transition font-medium ${
                  diagramTypeFilter === 'executionflow'
                    ? 'bg-sky-950 text-sky-300 border border-sky-800 shadow-sm'
                    : 'text-slate-400 hover:text-slate-200 hover:bg-slate-900'
                }`}
              >
                Execution ({diagramList.filter((d) => d.type === 'executionflow' && !d.isDiagramLayout).length})
              </button>
              <button
                onClick={() => setDiagramTypeFilter('stateflow')}
                className={`px-2 py-1 rounded text-center transition font-medium ${
                  diagramTypeFilter === 'stateflow'
                    ? 'bg-amber-950 text-amber-300 border border-amber-800 shadow-sm'
                    : 'text-slate-400 hover:text-slate-200 hover:bg-slate-900'
                }`}
              >
                State ({diagramList.filter((d) => d.type === 'stateflow' && !d.isDiagramLayout).length})
              </button>
              <button
                onClick={() => setDiagramTypeFilter('layout')}
                className={`px-2 py-1 rounded text-center transition font-medium ${
                  diagramTypeFilter === 'layout'
                    ? 'bg-indigo-950 text-indigo-300 border border-indigo-800 shadow-sm'
                    : 'text-slate-400 hover:text-slate-200 hover:bg-slate-900'
                }`}
                title="Exibir apenas arquivos de layout .diagram"
              >
                Layouts ({diagramList.filter((d) => d.isDiagramLayout).length})
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
                      handleOpenDiagram(d);
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
                      <div className="flex items-center gap-1 shrink-0">
                        {d.hasCompanionLayout && (
                          <span
                            className="text-[9px] px-1.5 py-0.2 rounded font-mono bg-indigo-950/90 text-indigo-300 border border-indigo-800/60"
                            title="Possui layout visual original do Eclipse (.diagram)"
                          >
                            📐 Layout
                          </span>
                        )}
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
          <div className="h-52 border-t border-slate-800 flex flex-col bg-slate-900/60">
            <div className="p-2 border-b border-slate-800/80 flex items-center justify-between">
              <div className="flex items-center gap-1.5">
                <span className="text-amber-400 font-mono text-[11px]">☕</span>
                <span className="text-xs font-semibold text-slate-300">
                  Classes MIC ({filteredClasses.length})
                </span>
              </div>
              {isScanningWorkspace && (
                <span className="flex items-center gap-1 text-[10px] text-sky-400 font-mono animate-pulse">
                  <RefreshCw className="w-3 h-3 animate-spin" />
                  <span>Indexando...</span>
                </span>
              )}
            </div>

            <div className="px-2 pt-1.5">
              <input
                type="text"
                value={javaSearch}
                onChange={(e) => setJavaSearch(e.target.value)}
                placeholder="Buscar MIC* (ex: Mic**ajustes)..."
                className="w-full bg-slate-950 border border-slate-800 rounded px-2 py-1 text-xs text-slate-200 focus:outline-none focus:border-sky-600 placeholder:text-slate-600 font-mono"
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
              nodes={currentNodes}
              edges={currentEdges}
              onNodesChange={onNodesChange}
              onEdgesChange={onEdgesChange}
              onConnect={onConnect}
              onSelectNode={handleSelectNode}
              onSelectEdge={handleSelectEdge}
              selectedEdgeId={selectedEdge?.id}
              selectedNodeId={selectedNodeId}
              onContextMenuNode={handleContextMenuNode}
              onContextMenuEdge={handleContextMenuEdge}
              onDropNewNode={(type, pos) => handleCreateNode(type, pos)}
              onDeleteNode={handleDeleteNode}
              onDeleteEdge={handleDeleteEdge}
              onUpdateEdgeLabel={(id, label) => handleUpdateEdgeLabelById(id, label, true)}
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
                      const found = diagramList.find((d) => d.name === ref.diagramName);
                      if (found) {
                        handleOpenDiagram(found);
                      } else {
                        setActiveTab(ref.diagramName);
                      }
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
            <div className="flex items-center justify-between">
              <span className="text-xs font-bold uppercase tracking-wider text-slate-400">
                Paleta de Ferramentas
              </span>
              <span className="text-[10px] text-slate-500 font-medium">Clique ou arraste</span>
            </div>
            <div className="grid grid-cols-2 gap-2 mt-2">
              <div
                draggable
                onDragStart={(e) => {
                  e.dataTransfer.setData('application/reactflow', 'startPoint');
                  e.dataTransfer.effectAllowed = 'move';
                }}
                onClick={() => handleCreateNode('startPoint')}
                className="p-2.5 rounded bg-slate-800/80 border border-slate-700/60 hover:border-sky-500 hover:bg-slate-800 cursor-pointer flex items-center gap-2 text-xs text-slate-200 transition shadow-sm select-none active:scale-95 group"
                title="Clique para adicionar ou arraste para o canvas"
              >
                <div className="w-3.5 h-3.5 rounded-full bg-black border border-slate-400 shrink-0 group-hover:border-sky-400" />
                <span className="font-medium">Start Point</span>
              </div>
              <div
                draggable
                onDragStart={(e) => {
                  e.dataTransfer.setData('application/reactflow', 'executable');
                  e.dataTransfer.effectAllowed = 'move';
                }}
                onClick={() => handleCreateNode('executable')}
                className="p-2.5 rounded bg-slate-800/80 border border-slate-700/60 hover:border-sky-500 hover:bg-slate-800 cursor-pointer flex items-center gap-2 text-xs text-slate-200 transition shadow-sm select-none active:scale-95 group"
                title="Clique para adicionar ou arraste para o canvas"
              >
                <span className="text-amber-500 text-sm font-bold group-hover:scale-110 transition">⚙</span>
                <span className="font-medium">Mic Action</span>
              </div>
              <div
                draggable
                onDragStart={(e) => {
                  e.dataTransfer.setData('application/reactflow', 'subflow');
                  e.dataTransfer.effectAllowed = 'move';
                }}
                onClick={() => handleCreateNode('subflow')}
                className="p-2.5 rounded bg-slate-800/80 border border-slate-700/60 hover:border-sky-500 hover:bg-slate-800 cursor-pointer flex items-center gap-2 text-xs text-slate-200 transition shadow-sm select-none active:scale-95 group"
                title="Clique para adicionar ou arraste para o canvas"
              >
                <span className="text-amber-400 text-sm group-hover:scale-110 transition">🗂</span>
                <span className="font-medium">Subfluxo</span>
              </div>
              <div
                draggable
                onDragStart={(e) => {
                  e.dataTransfer.setData('application/reactflow', 'result');
                  e.dataTransfer.effectAllowed = 'move';
                }}
                onClick={() => handleCreateNode('result')}
                className="p-2.5 rounded bg-slate-800/80 border border-slate-700/60 hover:border-sky-500 hover:bg-slate-800 cursor-pointer flex items-center gap-2 text-xs text-slate-200 transition shadow-sm select-none active:scale-95 group"
                title="Clique para adicionar ou arraste para o canvas"
              >
                <span className="text-emerald-400 text-sm group-hover:scale-110 transition">🚩</span>
                <span className="font-medium">Resultado</span>
              </div>
            </div>
          </div>

          {/* Propriedades */}
          <div className="flex-1 flex flex-col p-3 overflow-y-auto">
            <span className="text-xs font-bold uppercase tracking-wider text-slate-400 mb-3 flex items-center gap-1.5">
              <Settings className="w-3.5 h-3.5 text-sky-400" />
              <span>Propriedades do Elemento</span>
            </span>

            {selectedEdge ? (
              <div className="space-y-4 text-xs">
                <div>
                  <div className="flex items-center justify-between">
                    <label className="text-sky-400 text-[10px] uppercase font-bold tracking-wider">
                      Transição (Step / Aresta)
                    </label>
                    <span className="text-[10px] bg-sky-950 text-sky-300 px-1.5 py-0.5 rounded border border-sky-800 font-mono">
                      Step
                    </span>
                  </div>
                  <div className="mt-1 font-mono text-[11px] text-slate-300 bg-slate-950 p-2 rounded border border-slate-800 truncate">
                    {selectedEdge.id}
                  </div>
                </div>

                <div>
                  <label className="text-slate-300 text-[10px] uppercase font-semibold flex items-center justify-between">
                    <span>Rótulo / Resultado de Saída</span>
                    <span className="text-sky-400 font-mono text-[9px]">executionFlowResultValue</span>
                  </label>
                  <input
                    type="text"
                    value={(selectedEdge.data?.label as string) || ''}
                    onChange={(e) => handleUpdateEdgeLabel(e.target.value)}
                    placeholder="Ex: DEFAULT, CONFIRMAR, ABANDONAR..."
                    className="mt-1 w-full font-mono font-bold text-sky-300 bg-slate-950 p-2 rounded border border-sky-500 focus:outline-none focus:ring-1 focus:ring-sky-400 transition text-xs shadow-inner"
                  />
                </div>

                <div>
                  <label className="text-slate-500 text-[10px] uppercase font-semibold block mb-1.5">
                    Resultados Rápidos CSI:
                  </label>
                  <div className="flex flex-wrap gap-1.5">
                    {['DEFAULT', 'CONFIRMAR', 'ABANDONAR', 'SUCESSO', 'ERRO', 'CANCELAR', 'NAO_HA_AJUSTES'].map(
                      (val) => (
                        <button
                          key={val}
                          onClick={() => handleUpdateEdgeLabel(val)}
                          className={`px-2 py-1 rounded text-[10px] font-mono font-semibold transition border ${
                            (selectedEdge.data?.label as string) === val
                              ? 'bg-sky-600 text-white border-sky-400 shadow-sm'
                              : 'bg-slate-800 text-slate-300 border-slate-700 hover:bg-slate-700 hover:text-white'
                          }`}
                        >
                          {val}
                        </button>
                      )
                    )}
                  </div>
                </div>

                <div className="p-2.5 rounded bg-slate-950/70 border border-slate-800 space-y-1.5 font-mono text-[11px]">
                  <div className="flex justify-between text-slate-400">
                    <span>Origem:</span>
                    <span className="text-slate-200 truncate max-w-[150px]">
                      {(currentNodes.find((n) => n.id === selectedEdge.source)?.data?.title as string) ||
                        selectedEdge.source}
                    </span>
                  </div>
                  <div className="flex justify-between text-slate-400">
                    <span>Destino:</span>
                    <span className="text-slate-200 truncate max-w-[150px]">
                      {(currentNodes.find((n) => n.id === selectedEdge.target)?.data?.title as string) ||
                        selectedEdge.target}
                    </span>
                  </div>
                </div>

                <div className="pt-2">
                  <button
                    onClick={() => handleDeleteEdge(selectedEdge.id)}
                    className="w-full py-1.5 px-3 bg-red-950/80 hover:bg-red-900 border border-red-800 text-red-300 rounded text-xs font-semibold transition flex items-center justify-center gap-1.5"
                  >
                    <Trash2 className="w-3.5 h-3.5" />
                    <span>Excluir Transição (Delete Step)</span>
                  </button>
                </div>
              </div>
            ) : activeNode ? (
              <div className="space-y-3 text-xs">
                <div>
                  <div className="flex items-center justify-between">
                    <label className="text-slate-500 text-[10px] uppercase font-semibold">Tipo do Nó</label>
                    <span className="text-[10px] bg-slate-800 text-sky-400 px-1.5 py-0.2 rounded font-mono border border-slate-700">
                      {activeNode.type}
                    </span>
                  </div>
                  <div className="mt-0.5 font-mono text-slate-300 bg-slate-950 p-2 rounded border border-slate-800">
                    {activeNode.type === 'executable'
                      ? 'Executable Flow Object (Mic Action)'
                      : activeNode.type === 'result'
                      ? 'Execution Flow Result'
                      : activeNode.type === 'startPoint'
                      ? 'Start Point'
                      : activeNode.type === 'subflow'
                      ? 'Subflow (Subfluxo)'
                      : activeNode.type}
                  </div>
                </div>

                {/* Campo de Nome / Rótulo com Autocomplete e Curinga para Nós Executable (MIC) */}
                <div className="relative">
                  <div className="flex items-center justify-between">
                    <label className="text-slate-300 text-[10px] uppercase font-semibold">
                      Simple Class Name / Rótulo
                    </label>
                    {activeNode.type === 'executable' && micSuggestions.length > 0 && (
                      <span className="text-[9px] text-amber-400 font-mono">
                        {micSuggestions.length} MICs encontradas
                      </span>
                    )}
                  </div>
                  <div className="relative mt-1">
                    <input
                      type="text"
                      value={(activeNode.data?.title as string) || ''}
                      onFocus={() => {
                        if (activeNode.type === 'executable') {
                          setIsAutocompleteOpen(true);
                        }
                      }}
                      onChange={(e) => {
                        handleUpdateNodeProperty('title', e.target.value);
                        if (activeNode.type === 'executable') {
                          setIsAutocompleteOpen(true);
                        }
                      }}
                      placeholder={
                        activeNode.type === 'executable'
                          ? 'Ex: Mic**ajustes ou parte do nome...'
                          : 'Nome do elemento...'
                      }
                      className="w-full font-semibold text-slate-100 bg-slate-950 p-2 pr-7 rounded border border-slate-700 focus:outline-none focus:border-sky-500 transition text-xs shadow-inner"
                    />
                    {activeNode.type === 'executable' && (
                      <button
                        type="button"
                        onClick={() => setIsAutocompleteOpen(!isAutocompleteOpen)}
                        className="absolute right-1.5 top-1/2 -translate-y-1/2 text-slate-400 hover:text-sky-300 p-1"
                        title="Buscar classes Java mapeadas"
                      >
                        <Search className="w-3.5 h-3.5" />
                      </button>
                    )}
                  </div>

                  {/* Dropdown de Autocomplete com Suporte a Curingas (ex: Mic**ajustes) */}
                  {activeNode.type === 'executable' && isAutocompleteOpen && (
                    <div className="absolute left-0 right-0 mt-1 bg-slate-900 border border-sky-500/80 rounded-md shadow-2xl z-50 max-h-60 overflow-y-auto divide-y divide-slate-800 text-xs backdrop-blur-md">
                      <div className="p-1.5 bg-slate-950/90 flex items-center justify-between text-[10px] text-slate-400 border-b border-slate-800">
                        <span className="font-mono text-sky-400">
                          Sugestões MIC ({micSuggestions.length})
                        </span>
                        <button
                          type="button"
                          onClick={() => setIsAutocompleteOpen(false)}
                          className="hover:text-white px-1 font-bold text-slate-400"
                        >
                          ✕
                        </button>
                      </div>

                      {micSuggestions.length > 0 ? (
                        micSuggestions.map((cls) => (
                          <div
                            key={cls.name + cls.pkg}
                            onClick={() => {
                              handleSetNodeClass(cls.name, cls.pkg);
                              setIsAutocompleteOpen(false);
                              showToast(`Classe "${cls.name}" vinculada com sucesso!`, 'success');
                            }}
                            className="p-2 hover:bg-sky-950/80 hover:text-sky-200 cursor-pointer transition flex flex-col gap-0.5"
                          >
                            <div className="flex items-center gap-1.5 font-bold text-slate-200">
                              <span className="text-amber-400 text-xs">⚙</span>
                              <span>{cls.name}</span>
                            </div>
                            <span className="text-[10px] font-mono text-slate-400 truncate">
                              {cls.pkg}
                            </span>
                          </div>
                        ))
                      ) : (
                        <div className="p-2.5 text-[11px] text-slate-400 italic">
                          Nenhuma classe MIC encontrada para este filtro. Você pode continuar digitando um nome customizado.
                        </div>
                      )}
                    </div>
                  )}
                </div>

                {activeNode.type === 'executable' && (
                  <div>
                    <label className="text-slate-500 text-[10px] uppercase font-semibold">
                      Class Name (Pacote Completo)
                    </label>
                    <input
                      type="text"
                      value={(activeNode.data?.className as string) || ''}
                      onChange={(e) => handleUpdateNodeProperty('className', e.target.value)}
                      placeholder="Ex: com.csi.oper.pos.MicNome..."
                      className="mt-0.5 w-full font-mono text-[11px] text-sky-300 bg-slate-950 p-2 rounded border border-slate-700 focus:outline-none focus:border-sky-500 transition break-all"
                    />
                  </div>
                )}

                <div>
                  <label className="text-slate-500 text-[10px] uppercase font-semibold">
                    Path Key (Epoch ID Único)
                  </label>
                  <div className="mt-0.5 font-mono text-[11px] text-slate-400 bg-slate-950 p-2 rounded border border-slate-800">
                    {(activeNode.data?.pathKey as string) || activeNode.id}
                  </div>
                </div>

                <div className="pt-2 flex flex-col gap-2">
                  <button
                    onClick={() =>
                      handleSearchReferences(
                        (activeNode.data?.title as string) || activeNode.id
                      )
                    }
                    className="w-full py-2 rounded bg-sky-950 hover:bg-sky-900 text-sky-300 border border-sky-800 text-xs font-medium transition flex items-center justify-center gap-1.5 shadow-sm"
                  >
                    <Search className="w-3.5 h-3.5" />
                    <span>Search ExecutionFlow References...</span>
                  </button>
                  <button
                    onClick={() => handleDeleteNode(activeNode.id)}
                    className="w-full py-1.5 px-3 bg-red-950/80 hover:bg-red-900 border border-red-800 text-red-300 rounded text-xs font-semibold transition flex items-center justify-center gap-1.5 shadow-sm"
                  >
                    <Trash2 className="w-3.5 h-3.5" />
                    <span>Excluir Nó do Modelo</span>
                  </button>
                </div>
              </div>
            ) : (
              <div className="text-slate-500 text-xs italic mt-4 text-center">
                Selecione um nó ou transição no canvas para visualizar e editar suas propriedades.
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

            {/* Botão de Destaque Web para Navegador */}
            {!window.api && WebFileSystemService.isSupported() && (
              <div className="p-3.5 rounded-lg bg-sky-950/60 border border-sky-600/50 flex flex-col gap-2">
                <div className="flex items-center justify-between">
                  <span className="text-xs font-semibold text-sky-300 flex items-center gap-1.5">
                    <HardDrive className="w-4 h-4 text-sky-400" />
                    Acesso Nativo ao Disco Local (Web API)
                  </span>
                  <span className="text-[10px] uppercase font-bold px-2 py-0.5 rounded bg-sky-600/30 text-sky-300 border border-sky-500/40">
                    Chrome / Edge
                  </span>
                </div>
                <p className="text-[11px] text-slate-300 leading-normal">
                  Permite abrir a pasta local do seu projeto diretamente no navegador, com gravação de XML e buscas completas sem precisar instalar nada.
                </p>
                <button
                  onClick={handleChooseWorkspaceFolder}
                  className="w-full py-2 px-3 bg-sky-600 hover:bg-sky-500 text-white rounded-md text-xs font-bold transition flex items-center justify-center gap-2 shadow"
                >
                  <FolderOpen className="w-4 h-4" />
                  <span>Selecionar Pasta do Projeto no Computador</span>
                </button>
              </div>
            )}

            <div className="space-y-1.5">
              <label className="text-[11px] font-semibold text-slate-400 uppercase tracking-wider">
                Caminho da Pasta do Projeto (ou Modo Manual)
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

      {/* 4. Backdrop e Menu de Contexto do Nó e Transição */}
      {contextMenu && (
        <div
          className="fixed inset-0 z-40 bg-transparent"
          onClick={(e) => {
            e.stopPropagation();
            setContextMenu(null);
          }}
          onContextMenu={(e) => {
            e.preventDefault();
            e.stopPropagation();
            setContextMenu(null);
          }}
        />
      )}

      {contextMenu && (
        <div
          style={{
            top: Math.max(10, Math.min(contextMenu.y, (typeof window !== 'undefined' ? window.innerHeight : 800) - 240)),
            left: Math.max(10, Math.min(contextMenu.x, (typeof window !== 'undefined' ? window.innerWidth : 1200) - 260)),
          }}
          className="fixed bg-slate-900/95 border border-slate-700 shadow-2xl rounded-lg py-1.5 z-50 text-xs text-slate-200 min-w-[240px] backdrop-blur-md"
          onClick={(e) => e.stopPropagation()}
          onMouseDown={(e) => e.stopPropagation()}
          onContextMenu={(e) => {
            e.preventDefault();
            e.stopPropagation();
          }}
        >
          {contextMenu.edge ? (
            <>
              <div className="px-3 py-1.5 text-[11px] font-mono font-bold text-sky-400 border-b border-slate-800 flex items-center justify-between">
                <span>Transição</span>
                <span className="text-[10px] bg-sky-950 px-1.5 py-0.5 rounded border border-sky-800 text-sky-300">
                  {(contextMenu.edge.data?.label as string) || 'DEFAULT'}
                </span>
              </div>
              <button
                onClick={() => {
                  setSelectedEdge(contextMenu.edge!);
                  setSelectedNode(null);
                  setSelectedNodeId(null);
                  setContextMenu(null);
                }}
                className="w-full px-3 py-1.5 hover:bg-slate-800 flex items-center gap-2 text-left text-sky-300 font-medium"
              >
                <span>✏️</span>
                <span>Editar Rótulo em Propriedades</span>
              </button>
              <div className="h-[1px] bg-slate-800 my-1" />
              <div className="px-3 py-1 text-[10px] uppercase font-semibold text-slate-500">
                Atalhos Rápidos:
              </div>
              {['DEFAULT', 'CONFIRMAR', 'ABANDONAR', 'SUCESSO'].map((preset) => (
                <button
                  key={preset}
                  onClick={() => {
                    handleUpdateEdgeLabelById(contextMenu.edge!.id, preset, true);
                    setContextMenu(null);
                  }}
                  className="w-full px-3 py-1 hover:bg-slate-800 flex items-center justify-between text-left text-slate-300 font-mono text-[11px]"
                >
                  <span>Definir "{preset}"</span>
                  <span className="text-[9px] text-slate-500 font-sans">preset</span>
                </button>
              ))}
              <div className="h-[1px] bg-slate-800 my-1" />
              <button
                onClick={() => {
                  handleDeleteEdge(contextMenu.edge!.id);
                  setContextMenu(null);
                }}
                className="w-full px-3 py-1.5 hover:bg-red-950 text-red-400 flex items-center gap-2 text-left"
              >
                <span>❌</span>
                <span>Excluir Transição (Delete Step)</span>
              </button>
            </>
          ) : contextMenu.node ? (
            <>
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
                  if (contextMenu.node) {
                    setSelectedNode(contextMenu.node);
                    setSelectedNodeId(contextMenu.node.id);
                  }
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
                  if (contextMenu.node) {
                    handleDeleteNode(contextMenu.node.id);
                  }
                  setContextMenu(null);
                }}
                className="w-full px-3 py-1.5 hover:bg-red-950 text-red-400 flex items-center gap-2 text-left"
              >
                <span>❌</span>
                <span>Delete from Model</span>
              </button>
            </>
          ) : null}
        </div>
      )}

      {/* 5. Notificação Flutuante Toast */}
      {toast && (
        <div
          className={`fixed bottom-6 right-6 z-50 flex items-center gap-2.5 px-4 py-3 rounded-lg shadow-2xl border text-xs font-medium backdrop-blur-md transition-all ${
            toast.type === 'success'
              ? 'bg-slate-900/95 border-emerald-500/80 text-emerald-300'
              : toast.type === 'error'
              ? 'bg-slate-900/95 border-red-500/80 text-red-300'
              : 'bg-slate-900/95 border-sky-500/80 text-sky-300'
          }`}
        >
          {toast.type === 'success' ? (
            <CheckCircle2 className="w-4 h-4 text-emerald-400 shrink-0" />
          ) : toast.type === 'error' ? (
            <AlertTriangle className="w-4 h-4 text-red-400 shrink-0" />
          ) : (
            <HardDrive className="w-4 h-4 text-sky-400 shrink-0" />
          )}
          <span>{toast.message}</span>
          <button
            onClick={() => setToast(null)}
            className="ml-2 text-slate-500 hover:text-slate-300 p-0.5 rounded transition"
          >
            <X className="w-3.5 h-3.5" />
          </button>
        </div>
      )}
    </div>
  );
}
