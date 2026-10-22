import { contextBridge, ipcRenderer } from 'electron';
import type { DiagramFileInfo } from '../src/services/diagramScanner';
import type { JavaClassInfo } from '../src/services/javaScanner';
import type { DiagramReference } from '../src/services/referenceEngine';

export interface ElectronAPI {
  openDirectory: () => Promise<string | null>;
  scanDiagrams: (workspacePath: string) => Promise<DiagramFileInfo[]>;
  scanJavaClasses: (workspacePath: string) => Promise<JavaClassInfo[]>;
  findReferences: (workspacePath: string, className: string) => Promise<DiagramReference[]>;
  readFile: (filePath: string) => Promise<string>;
  writeFile: (filePath: string, content: string) => Promise<boolean>;
}

const api: ElectronAPI = {
  openDirectory: () => ipcRenderer.invoke('dialog:openDirectory'),
  scanDiagrams: (workspacePath: string) => ipcRenderer.invoke('workspace:scanDiagrams', workspacePath),
  scanJavaClasses: (workspacePath: string) => ipcRenderer.invoke('workspace:scanJavaClasses', workspacePath),
  findReferences: (workspacePath: string, className: string) =>
    ipcRenderer.invoke('workspace:findReferences', workspacePath, className),
  readFile: (filePath: string) => ipcRenderer.invoke('fs:readFile', filePath),
  writeFile: (filePath: string, content: string) => ipcRenderer.invoke('fs:writeFile', filePath, content),
};

contextBridge.exposeInMainWorld('api', api);

declare global {
  interface Window {
    api?: ElectronAPI;
  }
}
