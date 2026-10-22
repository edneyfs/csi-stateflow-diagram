export interface ExecutableFlowObject {
  pathKey: string;
  className: string;
  simpleClassName: string;
}

export interface ExecutionFlowObject {
  pathKey: string;
  id: string;
  title?: string;
  description?: string;
  fileName?: string;
  filePath?: string;
}

export interface ExecutionFlowResult {
  pathKey: string;
  executionFlowResultValue: string;
}

export interface FlowObjectConnection {
  sourcePathKey: string;
  destinationPathKey: string;
  executionFlowResultValue?: string;
}

export interface StartPointConnection {
  destinationObjectPathKey?: string;
  destinationStateId?: string;
}

export interface StateObject {
  pathKey: string;
  id: string;
  description?: string;
}

export interface EventConnection {
  sourcePathKey: string;
  destinationPathKey: string;
  eventID: string;
}

export interface ExecutionFlowModel {
  type: 'executionflow';
  id?: string;
  title?: string;
  description?: string;
  requestAuthorization?: boolean;
  startPoint?: { exists: boolean };
  startPointConnection?: StartPointConnection;
  executableFlowObjects: ExecutableFlowObject[];
  executionFlowObjects: ExecutionFlowObject[];
  results: ExecutionFlowResult[];
  flowConnections: FlowObjectConnection[];
}

export interface StateFlowModel {
  type: 'stateflow';
  id?: string;
  description?: string;
  volatile?: boolean;
  startPoint?: { exists: boolean };
  startPointConnection?: StartPointConnection;
  states: StateObject[];
  executionFlows: ExecutionFlowObject[];
  stateConnections: EventConnection[];
  flowConnections: FlowObjectConnection[];
}

export type DiagramModel = ExecutionFlowModel | StateFlowModel;

export interface NodeLayout {
  id: string;
  x: number;
  y: number;
  width?: number;
  height?: number;
}

export interface StickyNote {
  id: string;
  text: string;
  x: number;
  y: number;
  width: number;
  height: number;
}

export interface DiagramLayout {
  version: '1.0';
  viewport: { x: number; y: number; zoom: number };
  nodes: Record<string, NodeLayout>;
  notes?: StickyNote[];
}
