const test = require('node:test');
const assert = require('node:assert');
const { EnvironmentService } = require('../src/services/environmentService');
const { VsCodeBridge } = require('../src/services/vscodeBridge');

test('EnvironmentService - Detecção de ambiente via query parameter ?env=vscode', () => {
  const mockWindow = {
    location: {
      search: '?env=vscode',
    },
  };

  const env = EnvironmentService.getEnvironment(mockWindow);
  assert.strictEqual(env, 'vscode', 'Deveria identificar o ambiente como vscode');

  const features = EnvironmentService.getFeatures(mockWindow);
  assert.strictEqual(features.env, 'vscode');
  assert.strictEqual(features.showLeftSidebar, false, 'No VS Code, a barra lateral esquerda DEVE ser oculta');
  assert.strictEqual(features.showWorkspaceButton, false, 'No VS Code, o seletor de workspace DEVE ser oculto');
  assert.strictEqual(features.showSaveButton, false, 'No VS Code, o botão Salvar XML DEVE ser oculto');
  assert.strictEqual(features.enableNativeVsCodeSync, true, 'No VS Code, a sincronização nativa DEVE estar ativa');
  assert.strictEqual(features.enableFileDrop, false, 'No VS Code, o drag-and-drop de arquivos do SO é desnecessário');
});

test('EnvironmentService - Detecção automática via acquireVsCodeApi', () => {
  const mockWindow = {
    location: { search: '' },
    acquireVsCodeApi: () => ({ postMessage: () => {} }),
  };

  const env = EnvironmentService.getEnvironment(mockWindow);
  assert.strictEqual(env, 'vscode');

  const features = EnvironmentService.getFeatures(mockWindow);
  assert.strictEqual(features.showLeftSidebar, false);
  assert.strictEqual(features.showWorkspaceButton, false);
  assert.strictEqual(features.showSaveButton, false);
});

test('EnvironmentService - Detecção automática via __VSCODE_ENVIRONMENT__ flag', () => {
  const mockWindow = {
    location: { search: '' },
    __VSCODE_ENVIRONMENT__: true,
  };

  const env = EnvironmentService.getEnvironment(mockWindow);
  assert.strictEqual(env, 'vscode');

  const features = EnvironmentService.getFeatures(mockWindow);
  assert.strictEqual(features.showLeftSidebar, false);
  assert.strictEqual(features.showWorkspaceButton, false);
  assert.strictEqual(features.showSaveButton, false);
});

test('EnvironmentService - Ambiente Electron Desktop', () => {
  const mockWindow = {
    location: { search: '' },
    api: {
      openDirectory: () => {},
    },
  };

  const env = EnvironmentService.getEnvironment(mockWindow);
  assert.strictEqual(env, 'electron');

  const features = EnvironmentService.getFeatures(mockWindow);
  assert.strictEqual(features.showLeftSidebar, true, 'No Electron, a barra lateral DEVE estar visível');
  assert.strictEqual(features.showWorkspaceButton, true, 'No Electron, o botão de workspace DEVE estar visível');
  assert.strictEqual(features.showSaveButton, true, 'No Electron, o botão salvar XML DEVE estar visível');
  assert.strictEqual(features.enableNativeVsCodeSync, false);
});

test('EnvironmentService - Ambiente Web padrão (GitHub Pages)', () => {
  const mockWindow = {
    location: { search: '' },
  };

  const env = EnvironmentService.getEnvironment(mockWindow);
  assert.strictEqual(env, 'web');

  const features = EnvironmentService.getFeatures(mockWindow);
  assert.strictEqual(features.showLeftSidebar, true, 'Na Web, a barra lateral DEVE estar visível');
  assert.strictEqual(features.showWorkspaceButton, true, 'Na Web, o botão de workspace DEVE estar visível');
  assert.strictEqual(features.showSaveButton, true, 'Na Web, o botão salvar XML DEVE estar visível');
  assert.strictEqual(features.enableNativeVsCodeSync, false);
});

test('EnvironmentService - Validação da ocultação de componentes quando env=vscode', () => {
  // Simulação de renderização condicional da interface React
  function renderLayoutSimulation(features) {
    const renderedComponents = [];

    // Header Components
    renderedComponents.push('AppTitle');
    if (features.showWorkspaceButton) {
      renderedComponents.push('WorkspaceSelectorButton');
      renderedComponents.push('ChangeWorkspaceButton');
    }
    if (features.showSaveButton) {
      renderedComponents.push('SaveXmlButton');
    }
    if (features.enableNativeVsCodeSync) {
      renderedComponents.push('VsCodeSyncBadge');
    }

    // Body Components
    if (features.showLeftSidebar) {
      renderedComponents.push('LeftSidebar');
      renderedComponents.push('DiagramsList');
      renderedComponents.push('JavaClassesList');
    }

    renderedComponents.push('DiagramCanvas');
    renderedComponents.push('RightPropertiesPanel');

    return renderedComponents;
  }

  // 1. Cenário Web
  const webFeatures = EnvironmentService.getFeatures({ location: { search: '?env=web' } });
  const webComponents = renderLayoutSimulation(webFeatures);
  assert.ok(webComponents.includes('LeftSidebar'), 'Web DEVE conter LeftSidebar');
  assert.ok(webComponents.includes('WorkspaceSelectorButton'), 'Web DEVE conter WorkspaceSelectorButton');
  assert.ok(webComponents.includes('SaveXmlButton'), 'Web DEVE conter SaveXmlButton');
  assert.ok(!webComponents.includes('VsCodeSyncBadge'), 'Web NÃO DEVE conter VsCodeSyncBadge');

  // 2. Cenário VS Code
  const vscFeatures = EnvironmentService.getFeatures({ location: { search: '?env=vscode' } });
  const vscComponents = renderLayoutSimulation(vscFeatures);
  assert.strictEqual(vscComponents.includes('LeftSidebar'), false, 'VS Code NÃO PODE renderizar LeftSidebar');
  assert.strictEqual(vscComponents.includes('DiagramsList'), false, 'VS Code NÃO PODE renderizar DiagramsList');
  assert.strictEqual(vscComponents.includes('JavaClassesList'), false, 'VS Code NÃO PODE renderizar JavaClassesList');
  assert.strictEqual(vscComponents.includes('WorkspaceSelectorButton'), false, 'VS Code NÃO PODE renderizar WorkspaceSelectorButton');
  assert.strictEqual(vscComponents.includes('SaveXmlButton'), false, 'VS Code NÃO PODE renderizar SaveXmlButton');
  assert.ok(vscComponents.includes('VsCodeSyncBadge'), 'VS Code DEVE renderizar VsCodeSyncBadge');
  assert.ok(vscComponents.includes('DiagramCanvas'), 'VS Code DEVE renderizar DiagramCanvas com 100% de largura');
  assert.ok(vscComponents.includes('RightPropertiesPanel'), 'VS Code DEVE manter RightPropertiesPanel');
});

test('VsCodeBridge - Protocolo de Mensagens bidirecional', () => {
  const postedMessages = [];
  const mockApi = {
    postMessage: (msg) => postedMessages.push(msg),
  };

  // Mock global window
  global.window = {
    acquireVsCodeApi: () => mockApi,
  };

  // Forçar reinicialização do singleton no teste
  VsCodeBridge.initialized = false;
  VsCodeBridge.vscodeApi = null;

  assert.strictEqual(VsCodeBridge.isVsCode(), true);

  // 1. notifyReady
  VsCodeBridge.notifyReady();
  assert.deepStrictEqual(postedMessages[0], { type: 'ready' });

  // 2. notifyDocumentChange
  const sampleXml = '<executionFlow id="123"></executionFlow>';
  VsCodeBridge.notifyDocumentChange(sampleXml);
  assert.deepStrictEqual(postedMessages[1], {
    type: 'change',
    content: sampleXml,
  });

  // 3. notifyToast
  VsCodeBridge.notifyToast('Diagrama sincronizado', 'info');
  assert.deepStrictEqual(postedMessages[2], {
    type: 'toast',
    message: 'Diagrama sincronizado',
    alertType: 'info',
  });

  // Limpeza
  delete global.window;
});
