class VsCodeBridge {
  static vscodeApi = null;
  static initialized = false;

  static getApi() {
    if (!this.initialized && typeof window !== 'undefined') {
      this.initialized = true;
      if (typeof window.acquireVsCodeApi === 'function') {
        try {
          this.vscodeApi = window.acquireVsCodeApi();
        } catch {
          // acquireVsCodeApi may throw if called multiple times
        }
      }
    }
    return this.vscodeApi;
  }

  static isVsCode() {
    return Boolean(this.getApi() || (typeof window !== 'undefined' && window.__VSCODE_ENVIRONMENT__));
  }

  static notifyReady() {
    const api = this.getApi();
    if (api) {
      api.postMessage({ type: 'ready' });
    }
  }

  static notifyDocumentChange(xmlContent) {
    const api = this.getApi();
    if (api) {
      api.postMessage({
        type: 'change',
        content: xmlContent,
      });
    }
  }

  static notifyToast(message, alertType = 'info') {
    const api = this.getApi();
    if (api) {
      api.postMessage({
        type: 'toast',
        message,
        alertType,
      });
    }
  }

  static onMessage(handler) {
    if (typeof window === 'undefined') {
      return () => {};
    }

    const listener = (event) => {
      if (event.data && typeof event.data === 'object' && event.data.type) {
        handler(event.data);
      }
    };

    window.addEventListener('message', listener);
    return () => {
      window.removeEventListener('message', listener);
    };
  }
}

module.exports = { VsCodeBridge };
