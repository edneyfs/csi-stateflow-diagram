"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const electron_1 = require("electron");
const path_1 = __importDefault(require("path"));
const fs_1 = __importDefault(require("fs"));
const diagramScanner_1 = require("../src/services/diagramScanner");
const javaScanner_1 = require("../src/services/javaScanner");
const referenceEngine_1 = require("../src/services/referenceEngine");
let mainWindow = null;
function createWindow() {
    mainWindow = new electron_1.BrowserWindow({
        width: 1440,
        height: 900,
        minWidth: 1024,
        minHeight: 700,
        title: 'CSI StateFlow & ExecutionFlow Studio',
        backgroundColor: '#0f172a',
        webPreferences: {
            preload: path_1.default.join(__dirname, 'preload.js'),
            nodeIntegration: false,
            contextIsolation: true,
        },
    });
    const isDev = process.env.NODE_ENV === 'development' || !electron_1.app.isPackaged;
    if (isDev) {
        mainWindow.loadURL('http://localhost:5173');
    }
    else {
        mainWindow.loadFile(path_1.default.join(__dirname, '../../dist/index.html'));
    }
    mainWindow.on('closed', () => {
        mainWindow = null;
    });
}
electron_1.app.whenReady().then(() => {
    createWindow();
    electron_1.app.on('activate', () => {
        if (electron_1.BrowserWindow.getAllWindows().length === 0) {
            createWindow();
        }
    });
});
electron_1.app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        electron_1.app.quit();
    }
});
// IPC: Diálogo de abertura de pasta do workspace
electron_1.ipcMain.handle('dialog:openDirectory', async () => {
    const result = await electron_1.dialog.showOpenDialog(mainWindow, {
        properties: ['openDirectory'],
        title: 'Selecionar Pasta Workspace do Projeto CSI / P2K',
    });
    return result.filePaths[0] || null;
});
// IPC: Varredura de TODOS os diagramas da pasta
electron_1.ipcMain.handle('workspace:scanDiagrams', async (_, workspacePath) => {
    try {
        return await diagramScanner_1.DiagramScanner.scanWorkspaceDiagrams(workspacePath);
    }
    catch (error) {
        console.error('Erro ao varrer diagramas:', error);
        return [];
    }
});
// IPC: Varredura de classes Java
electron_1.ipcMain.handle('workspace:scanJavaClasses', async (_, workspacePath) => {
    try {
        return await javaScanner_1.JavaScanner.scanDirectory(workspacePath);
    }
    catch (error) {
        console.error('Erro ao varrer classes Java:', error);
        return [];
    }
});
// IPC: Busca de referências
electron_1.ipcMain.handle('workspace:findReferences', async (_, workspacePath, className) => {
    try {
        await referenceEngine_1.ReferenceEngine.indexWorkspace(workspacePath);
        return referenceEngine_1.ReferenceEngine.findReferences(className);
    }
    catch (error) {
        console.error('Erro ao buscar referências:', error);
        return [];
    }
});
// IPC: Leitura de arquivo
electron_1.ipcMain.handle('fs:readFile', async (_, filePath) => {
    try {
        return await fs_1.default.promises.readFile(filePath, 'utf-8');
    }
    catch (error) {
        throw new Error(`Erro ao ler arquivo: ${error.message}`);
    }
});
// IPC: Gravação de arquivo
electron_1.ipcMain.handle('fs:writeFile', async (_, filePath, content) => {
    try {
        await fs_1.default.promises.writeFile(filePath, content, 'utf-8');
        return true;
    }
    catch (error) {
        throw new Error(`Erro ao gravar arquivo: ${error.message}`);
    }
});
