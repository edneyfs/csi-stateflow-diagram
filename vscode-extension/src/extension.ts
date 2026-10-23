import * as vscode from 'vscode';
import { CsiEditorProvider } from './CsiEditorProvider';

export function activate(context: vscode.ExtensionContext) {
  // Registra o CustomTextEditorProvider para *.executionflow e *.stateflow
  context.subscriptions.push(CsiEditorProvider.register(context));

  // Comando manual para abrir o arquivo atual no editor visual
  const openVisualCommand = vscode.commands.registerCommand(
    'csi.stateflow.openVisual',
    async (uri?: vscode.Uri) => {
      const targetUri = uri || vscode.window.activeTextEditor?.document.uri;
      if (targetUri) {
        await vscode.commands.executeCommand(
          'vscode.openWith',
          targetUri,
          CsiEditorProvider.viewType
        );
      } else {
        vscode.window.showWarningMessage('Abra ou selecione um arquivo .executionflow ou .stateflow para visualizar.');
      }
    }
  );

  context.subscriptions.push(openVisualCommand);
}

export function deactivate() {}
