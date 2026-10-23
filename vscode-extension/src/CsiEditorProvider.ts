import * as vscode from 'vscode';
import * as path from 'path';
import * as fs from 'fs';

export class CsiEditorProvider implements vscode.CustomTextEditorProvider {
  public static readonly viewType = 'csi.stateflow.editor';

  constructor(private readonly context: vscode.ExtensionContext) {}

  public static register(context: vscode.ExtensionContext): vscode.Disposable {
    const provider = new CsiEditorProvider(context);
    return vscode.window.registerCustomEditorProvider(CsiEditorProvider.viewType, provider, {
      webviewOptions: {
        retainContextWhenHidden: true,
      },
      supportsMultipleEditorsPerDocument: false,
    });
  }

  public async resolveCustomTextEditor(
    document: vscode.TextDocument,
    webviewPanel: vscode.WebviewPanel,
    _token: vscode.CancellationToken
  ): Promise<void> {
    const webview = webviewPanel.webview;

    // Configura permissão de scripts e acesso aos arquivos estáticos do React
    const webviewDistPath = path.join(this.context.extensionPath, 'dist-webview');
    webview.options = {
      enableScripts: true,
      localResourceRoots: [
        vscode.Uri.file(webviewDistPath),
        vscode.Uri.file(path.join(this.context.extensionPath, '..', 'dist')),
      ],
    };

    webview.html = this.getHtmlForWebview(webview, webviewDistPath, document);

    let isUpdatingFromWebview = false;

    // 1. Mensagens recebidas do React Webview
    const messageListener = webview.onDidReceiveMessage(async (msg: any) => {
      switch (msg.type) {
        case 'ready': {
          // Diagrama pronto no React: envia o conteúdo XML inicial do documento
          webview.postMessage({
            type: 'loadDiagram',
            content: document.getText(),
            fileName: path.basename(document.fileName),
          });

          // Varre automaticamente classes MIC no workspace do VS Code
          try {
            const javaFiles = await vscode.workspace.findFiles('**/Mic*.java', '**/node_modules/**', 1500);
            const classes = javaFiles.map((uri: vscode.Uri) => {
              const name = path.basename(uri.fsPath, '.java');
              return { name, pkg: 'workspace' };
            });
            if (classes.length > 0) {
              webview.postMessage({
                type: 'updateJavaClasses',
                classes,
              });
            }
          } catch (err) {
            console.error('Falha ao varrer classes MIC no workspace:', err);
          }
          break;
        }

        case 'change': {
          // Atualização de nós/arestas/rótulos no React sincroniza com o buffer do VS Code
          if (typeof msg.content === 'string') {
            isUpdatingFromWebview = true;
            try {
              const edit = new vscode.WorkspaceEdit();
              const fullRange = new vscode.Range(
                document.positionAt(0),
                document.positionAt(document.getText().length)
              );
              edit.replace(document.uri, fullRange, msg.content);
              await vscode.workspace.applyEdit(edit);
            } finally {
              isUpdatingFromWebview = false;
            }
          }
          break;
        }

        case 'toast': {
          if (msg.message) {
            if (msg.alertType === 'error') {
              vscode.window.showErrorMessage(msg.message);
            } else if (msg.alertType === 'warning') {
              vscode.window.showWarningMessage(msg.message);
            } else {
              vscode.window.showInformationMessage(msg.message);
            }
          }
          break;
        }
      }
    });

    // 2. Mudanças externas no arquivo (ex: git checkout, edição em texto puro)
    const changeDocumentSubscription = vscode.workspace.onDidChangeTextDocument((e: vscode.TextDocumentChangeEvent) => {
      if (e.document.uri.toString() === document.uri.toString()) {
        if (!isUpdatingFromWebview && e.contentChanges.length > 0) {
          webview.postMessage({
            type: 'loadDiagram',
            content: document.getText(),
            fileName: path.basename(document.fileName),
          });
        }
      }
    });

    webviewPanel.onDidDispose(() => {
      messageListener.dispose();
      changeDocumentSubscription.dispose();
    });
  }

  private getHtmlForWebview(
    webview: vscode.Webview,
    webviewDistPath: string,
    document: vscode.TextDocument
  ): string {
    // Procura os arquivos compilados do Vite em dist-webview ou fallback para ../dist
    let distFolder = webviewDistPath;
    if (!fs.existsSync(distFolder)) {
      distFolder = path.join(this.context.extensionPath, '..', 'dist');
    }

    let jsFile = '';
    let cssFile = '';

    const assetsDir = path.join(distFolder, 'assets');
    if (fs.existsSync(assetsDir)) {
      const files = fs.readdirSync(assetsDir);
      jsFile = files.find((f) => f.endsWith('.js')) || '';
      cssFile = files.find((f) => f.endsWith('.css')) || '';
    }

    const scriptUri = jsFile
      ? webview.asWebviewUri(vscode.Uri.file(path.join(assetsDir, jsFile)))
      : '';
    const styleUri = cssFile
      ? webview.asWebviewUri(vscode.Uri.file(path.join(assetsDir, cssFile)))
      : '';

    const nonce = getNonce();
    const fileName = path.basename(document.fileName);

    return /* html */ `<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta http-equiv="Content-Security-Policy" content="default-src 'none'; style-src ${webview.cspSource} 'unsafe-inline'; script-src 'nonce-${nonce}' ${webview.cspSource}; font-src ${webview.cspSource}; img-src ${webview.cspSource} data:;" />
  <title>CSI StateFlow - ${fileName}</title>
  ${styleUri ? `<link rel="stylesheet" href="${styleUri}">` : ''}
  <script nonce="${nonce}">
    window.__VSCODE_ENVIRONMENT__ = true;
  </script>
</head>
<body class="bg-slate-950 text-slate-100 overflow-hidden m-0 p-0">
  <div id="root"></div>
  ${scriptUri ? `<script nonce="${nonce}" type="module" src="${scriptUri}"></script>` : '<p class="p-4 text-amber-300">Carregando componentes do estúdio visual CSI...</p>'}
</body>
</html>`;
  }
}

function getNonce(): string {
  let text = '';
  const possible = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
  for (let i = 0; i < 32; i++) {
    text += possible.charAt(Math.floor(Math.random() * possible.length));
  }
  return text;
}
