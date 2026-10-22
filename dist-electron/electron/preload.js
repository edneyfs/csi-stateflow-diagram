"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const electron_1 = require("electron");
const api = {
    openDirectory: () => electron_1.ipcRenderer.invoke('dialog:openDirectory'),
    scanDiagrams: (workspacePath) => electron_1.ipcRenderer.invoke('workspace:scanDiagrams', workspacePath),
    scanJavaClasses: (workspacePath) => electron_1.ipcRenderer.invoke('workspace:scanJavaClasses', workspacePath),
    findReferences: (workspacePath, className) => electron_1.ipcRenderer.invoke('workspace:findReferences', workspacePath, className),
    readFile: (filePath) => electron_1.ipcRenderer.invoke('fs:readFile', filePath),
    writeFile: (filePath, content) => electron_1.ipcRenderer.invoke('fs:writeFile', filePath, content),
};
electron_1.contextBridge.exposeInMainWorld('api', api);
