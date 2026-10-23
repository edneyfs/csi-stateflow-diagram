export type HostEnvironment = 'web' | 'electron' | 'vscode';

export interface EnvironmentFeatures {
  env: HostEnvironment;
  showLeftSidebar: boolean;
  showWorkspaceButton: boolean;
  showSaveButton: boolean;
  showHeaderTitle: boolean;
  enableNativeVsCodeSync: boolean;
  enableFileDrop: boolean;
}

export class EnvironmentService {
  private static overrideEnv: HostEnvironment | null = null;

  public static setOverride(env: HostEnvironment | null) {
    this.overrideEnv = env;
  }

  public static getEnvironment(mockWindow?: any): HostEnvironment {
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
          return urlEnv as HostEnvironment;
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

  public static getFeatures(mockWindow?: any): EnvironmentFeatures {
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
