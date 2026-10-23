/**
 * Serviço de detecção de ambiente e parametrização de interface (Web, Electron, VS Code)
 */
class EnvironmentService {
  static overrideEnv = null;

  static setOverride(env) {
    this.overrideEnv = env;
  }

  static getEnvironment(mockWindow) {
    if (this.overrideEnv) {
      return this.overrideEnv;
    }

    const currentWindow = mockWindow !== undefined ? mockWindow : (typeof window !== 'undefined' ? window : null);
    if (!currentWindow) {
      return 'web';
    }

    // 1. Verificação explícita por URL query string (?env=vscode | electron | web)
    try {
      if (currentWindow.location && currentWindow.location.search) {
        const params = new URLSearchParams(currentWindow.location.search);
        const urlEnv = params.get('env')?.toLowerCase();
        if (urlEnv === 'vscode' || urlEnv === 'electron' || urlEnv === 'web') {
          return urlEnv;
        }
      }
    } catch {
      // Falhas ao ler location tratadas silenciosamente
    }

    // 2. Detecção automática de injeção da API VS Code Webview
    if (
      typeof currentWindow.acquireVsCodeApi === 'function' ||
      Boolean(currentWindow.__VSCODE_ENVIRONMENT__)
    ) {
      return 'vscode';
    }

    // 3. Detecção automática de runtime Electron
    if (currentWindow.api && typeof currentWindow.api.openDirectory === 'function') {
      return 'electron';
    }

    // 4. Padrão: Navegador Web (GitHub Pages / Localhost)
    return 'web';
  }

  static getFeatures(mockWindow) {
    const env = this.getEnvironment(mockWindow);
    const isVsCode = env === 'vscode';

    return {
      env,
      showLeftSidebar: !isVsCode,
      showWorkspaceButton: !isVsCode,
      showSaveButton: !isVsCode,
      showHeaderTitle: true,
      enableNativeVsCodeSync: isVsCode,
      enableFileDrop: !isVsCode,
    };
  }
}

module.exports = { EnvironmentService };
