export interface DiagramFileInfo {
  name: string;
  relativePath: string;
  folder: string;
  fullPath: string;
  type: 'executionflow' | 'stateflow';
  isDiagramLayout: boolean;
  hasCompanionLayout?: boolean;
}

export interface JavaClassInfo {
  className: string;
  simpleClassName: string;
  packageName: string;
  filePath: string;
  isMic: boolean;
}

export interface DiagramReference {
  diagramPath: string;
  diagramName: string;
  nodePathKey: string;
  simpleClassName: string;
  className: string;
}

export interface WorkspaceState {
  folderName: string;
  isWebMode: boolean;
  canReconnect: boolean;
}
