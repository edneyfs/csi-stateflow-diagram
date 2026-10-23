export interface VsCodeMessage {
  type: 'ready' | 'change' | 'toast' | 'loadDiagram' | 'updateJavaClasses';
  content?: string;
  fileName?: string;
  message?: string;
  alertType?: 'info' | 'error' | 'warning';
  classes?: any[];
}

export class VsCodeBridge {
  private static vscodeApi: any = null;
  private static initialized: boolean = false;

  public static getApi(): any {
    if (!this.initialized && typeof window !== 'undefined') {
      this.initialized = true;
      if (typeof (window as any).acquireVsCodeApi === 'function') {
        try {
          this.vscodeApi = (window as any).acquireVsCodeApi();
        } catch {
          // acquireVsCodeApi pode lançar erro se chamado múltiplas vezes
        }
      }
    }
    return this.vscodeApi;
  }

  public static isVsCode(): boolean {
    return Boolean(this.getApi() || (typeof window !== 'undefined' && (window as any).__VSCODE_ENVIRONMENT__));
  }

  public static notifyReady(): void {
    const api = this.getApi();
    if (api) {
      api.postMessage({ type: 'ready' });
    }
  }

  public static notifyDocumentChange(xmlContent: string): void {
    const api = this.getApi();
    if (api) {
      api.postMessage({
        type: 'change',
        content: xmlContent,
      });
    }
  }

  public static notifyToast(message: string, alertType: 'info' | 'error' | 'warning' = 'info'): void {
    const api = this.getApi();
    if (api) {
      api.postMessage({
        type: 'toast',
        message,
        alertType,
      });
    }
  }

  public static onMessage(handler: (eventData: VsCodeMessage) => void): () => void {
    if (typeof window === 'undefined') {
      return () => {};
    }

    const listener = (event: MessageEvent) => {
      if (event.data && typeof event.data === 'object' && event.data.type) {
        handler(event.data as VsCodeMessage);
      }
    };

    window.addEventListener('message', listener);
    return () => {
      window.removeEventListener('message', listener);
    };
  }
}
