import { contextBridge, ipcRenderer } from 'electron';

export interface ElectronAPI {
  openDirectory: () => Promise<string | null>;
  readFile: (filePath: string) => Promise<string>;
  writeFile: (filePath: string, content: string) => Promise<boolean>;
}

const api: ElectronAPI = {
  openDirectory: () => ipcRenderer.invoke('dialog:openDirectory'),
  readFile: (filePath: string) => ipcRenderer.invoke('fs:readFile', filePath),
  writeFile: (filePath: string, content: string) => ipcRenderer.invoke('fs:writeFile', filePath, content),
};

contextBridge.exposeInMainWorld('api', api);

declare global {
  interface Window {
    api?: ElectronAPI;
  }
}
