import { app, BrowserWindow, ipcMain, dialog } from 'electron';
import path from 'path';
import fs from 'fs';
import { DiagramScanner } from '../src/services/diagramScanner';
import { JavaScanner } from '../src/services/javaScanner';
import { ReferenceEngine } from '../src/services/referenceEngine';

let mainWindow: BrowserWindow | null = null;

function createWindow() {
  mainWindow = new BrowserWindow({
    width: 1440,
    height: 900,
    minWidth: 1024,
    minHeight: 700,
    title: 'CSI StateFlow & ExecutionFlow Studio',
    backgroundColor: '#0f172a',
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
      nodeIntegration: false,
      contextIsolation: true,
    },
  });

  const isDev = process.env.NODE_ENV === 'development' || !app.isPackaged;

  if (isDev) {
    mainWindow.loadURL('http://localhost:5173');
  } else {
    mainWindow.loadFile(path.join(__dirname, '../dist/index.html'));
  }

  mainWindow.on('closed', () => {
    mainWindow = null;
  });
}

app.whenReady().then(() => {
  createWindow();

  app.on('activate', () => {
    if (BrowserWindow.getAllWindows().length === 0) {
      createWindow();
    }
  });
});

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit();
  }
});

// IPC: Diálogo de abertura de pasta do workspace
ipcMain.handle('dialog:openDirectory', async () => {
  const result = await dialog.showOpenDialog(mainWindow!, {
    properties: ['openDirectory'],
    title: 'Selecionar Pasta Workspace do Projeto CSI / P2K',
  });
  return result.filePaths[0] || null;
});

// IPC: Varredura de TODOS os diagramas da pasta
ipcMain.handle('workspace:scanDiagrams', async (_, workspacePath: string) => {
  try {
    return await DiagramScanner.scanWorkspaceDiagrams(workspacePath);
  } catch (error: any) {
    console.error('Erro ao varrer diagramas:', error);
    return [];
  }
});

// IPC: Varredura de classes Java
ipcMain.handle('workspace:scanJavaClasses', async (_, workspacePath: string) => {
  try {
    return await JavaScanner.scanDirectory(workspacePath);
  } catch (error: any) {
    console.error('Erro ao varrer classes Java:', error);
    return [];
  }
});

// IPC: Busca de referências
ipcMain.handle('workspace:findReferences', async (_, workspacePath: string, className: string) => {
  try {
    await ReferenceEngine.indexWorkspace(workspacePath);
    return ReferenceEngine.findReferences(className);
  } catch (error: any) {
    console.error('Erro ao buscar referências:', error);
    return [];
  }
});

// IPC: Leitura de arquivo
ipcMain.handle('fs:readFile', async (_, filePath: string) => {
  try {
    return await fs.promises.readFile(filePath, 'utf-8');
  } catch (error: any) {
    throw new Error(`Erro ao ler arquivo: ${error.message}`);
  }
});

// IPC: Gravação de arquivo
ipcMain.handle('fs:writeFile', async (_, filePath: string, content: string) => {
  try {
    await fs.promises.writeFile(filePath, content, 'utf-8');
    return true;
  } catch (error: any) {
    throw new Error(`Erro ao gravar arquivo: ${error.message}`);
  }
});
