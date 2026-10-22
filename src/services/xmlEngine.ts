import {
  DiagramModel,
  ExecutionFlowModel,
  StateFlowModel,
  ExecutableFlowObject,
  ExecutionFlowObject,
  ExecutionFlowResult,
  FlowObjectConnection,
  StateObject,
  EventConnection,
} from '../types/model';

/**
 * Motor de conversão bidirecional XML/XMI compatível com Eclipse EMF / Ecore:
 * - http:///com/csi/gmf/executionflow.ecore (.executionflow)
 * - http:///com/csi/gmf/stateflow.ecore (.stateflow)
 */
export class XmlEngine {
  /**
   * Converte uma string XML para o modelo tipado DiagramModel
   */
  public static parse(xmlContent: string): DiagramModel {
    if (xmlContent.includes('executionflow:ExecutionFlow') || xmlContent.includes('executionflow.ecore')) {
      return this.parseExecutionFlow(xmlContent);
    } else if (xmlContent.includes('stateflow:StateFlow') || xmlContent.includes('stateflow.ecore')) {
      return this.parseStateFlow(xmlContent);
    } else {
      throw new Error('Formato XML não reconhecido: não é um StateFlow nem ExecutionFlow válido.');
    }
  }

  /**
   * Parse específico de ExecutionFlow
   */
  private static parseExecutionFlow(xml: string): ExecutionFlowModel {
    const idMatch = xml.match(/\bid="([^"]*)"/);
    const titleMatch = xml.match(/\btitle="([^"]*)"/);
    const descMatch = xml.match(/\bdescription="([^"]*)"/);
    const authMatch = xml.match(/\brequestAuthorization="([^"]*)"/);

    const model: ExecutionFlowModel = {
      type: 'executionflow',
      id: idMatch ? idMatch[1] : undefined,
      title: titleMatch ? titleMatch[1] : undefined,
      description: descMatch ? descMatch[1] : undefined,
      requestAuthorization: authMatch ? authMatch[1] === 'true' : false,
      startPoint: xml.includes('<startPoint') ? { exists: true } : undefined,
      executableFlowObjects: [],
      executionFlowObjects: [],
      results: [],
      flowConnections: [],
    };

    // Parse startPointConnection
    const startConnMatch = xml.match(/<startPointConnection\s+([^>]+)\/?>/);
    if (startConnMatch) {
      const destKeyMatch = startConnMatch[1].match(/destinationObjectPathKey="([^"]*)"/);
      model.startPointConnection = {
        destinationObjectPathKey: destKeyMatch ? destKeyMatch[1] : undefined,
      };
    }

    // Parse executableFlowObjects (Ações MIC)
    const execRegex = /<executableFlowObjects\s+([^>]+)\/?>/g;
    let match: RegExpExecArray | null;
    while ((match = execRegex.exec(xml)) !== null) {
      const attrs = match[1];
      const pathKey = this.extractAttr(attrs, 'pathKey');
      const className = this.extractAttr(attrs, 'className');
      const simpleClassName = this.extractAttr(attrs, 'simpleClassName') || (className ? className.split('.').pop()! : '');
      if (pathKey) {
        model.executableFlowObjects.push({ pathKey, className: className || '', simpleClassName });
      }
    }

    // Parse executionFlowObjects (Subfluxos)
    const subflowRegex = /<executionFlowObjects\s+([^>]+)\/?>/g;
    while ((match = subflowRegex.exec(xml)) !== null) {
      const attrs = match[1];
      const pathKey = this.extractAttr(attrs, 'pathKey');
      const id = this.extractAttr(attrs, 'id') || '';
      const title = this.extractAttr(attrs, 'title');
      const description = this.extractAttr(attrs, 'description');
      const fileName = this.extractAttr(attrs, 'fileName');
      const filePath = this.extractAttr(attrs, 'filePath');
      if (pathKey) {
        model.executionFlowObjects.push({ pathKey, id, title, description, fileName, filePath });
      }
    }

    // Parse results
    const resultRegex = /<results\s+([^>]+)\/?>/g;
    while ((match = resultRegex.exec(xml)) !== null) {
      const attrs = match[1];
      const pathKey = this.extractAttr(attrs, 'pathKey');
      const value = this.extractAttr(attrs, 'executionFlowResultValue') || 'DEFAULT';
      if (pathKey) {
        model.results.push({ pathKey, executionFlowResultValue: value });
      }
    }

    // Parse flowConnections
    const connRegex = /<flowConnections\s+([^>]+)\/?>/g;
    while ((match = connRegex.exec(xml)) !== null) {
      const attrs = match[1];
      const source = this.extractAttr(attrs, 'sourcePathKey');
      const dest = this.extractAttr(attrs, 'destinationPathKey');
      const resultVal = this.extractAttr(attrs, 'executionFlowResultValue');
      if (source && dest) {
        model.flowConnections.push({
          sourcePathKey: source,
          destinationPathKey: dest,
          executionFlowResultValue: resultVal,
        });
      }
    }

    return model;
  }

  /**
   * Parse específico de StateFlow
   */
  private static parseStateFlow(xml: string): StateFlowModel {
    const idMatch = xml.match(/\bid="([^"]*)"/);
    const descMatch = xml.match(/\bdescription="([^"]*)"/);
    const volatileMatch = xml.match(/\bvolatile="([^"]*)"/);

    const model: StateFlowModel = {
      type: 'stateflow',
      id: idMatch ? idMatch[1] : undefined,
      description: descMatch ? descMatch[1] : undefined,
      volatile: volatileMatch ? volatileMatch[1] === 'true' : false,
      startPoint: xml.includes('<startPoint') ? { exists: true } : undefined,
      states: [],
      executionFlows: [],
      stateConnections: [],
      flowConnections: [],
    };

    // Parse startPointConnection
    const startConnMatch = xml.match(/<startPointConnection\s+([^>]+)\/?>/);
    if (startConnMatch) {
      const destIdMatch = startConnMatch[1].match(/destinationStateId="([^"]*)"/);
      model.startPointConnection = {
        destinationStateId: destIdMatch ? destIdMatch[1] : undefined,
      };
    }

    // Parse states
    const stateRegex = /<states\s+([^>]+)\/?>/g;
    let match: RegExpExecArray | null;
    while ((match = stateRegex.exec(xml)) !== null) {
      const attrs = match[1];
      const pathKey = this.extractAttr(attrs, 'pathKey');
      const id = this.extractAttr(attrs, 'id') || '';
      const description = this.extractAttr(attrs, 'description');
      if (pathKey && id) {
        model.states.push({ pathKey, id, description });
      }
    }

    // Parse executionFlows
    const flowRegex = /<executionFlows\s+([^>]+)\/?>/g;
    while ((match = flowRegex.exec(xml)) !== null) {
      const attrs = match[1];
      const pathKey = this.extractAttr(attrs, 'pathKey');
      const id = this.extractAttr(attrs, 'id') || '';
      const title = this.extractAttr(attrs, 'title');
      const description = this.extractAttr(attrs, 'description');
      const filePath = this.extractAttr(attrs, 'filePath');
      if (pathKey) {
        model.executionFlows.push({ pathKey, id, title, description, filePath });
      }
    }

    // Parse stateConnections
    const stateConnRegex = /<stateConnections\s+([^>]+)\/?>/g;
    while ((match = stateConnRegex.exec(xml)) !== null) {
      const attrs = match[1];
      const source = this.extractAttr(attrs, 'sourcePathKey');
      const dest = this.extractAttr(attrs, 'destinationPathKey');
      const eventID = this.extractAttr(attrs, 'eventID') || '';
      if (source && dest) {
        model.stateConnections.push({ sourcePathKey: source, destinationPathKey: dest, eventID });
      }
    }

    // Parse flowConnections
    const flowConnRegex = /<flowConnections\s+([^>]+)\/?>/g;
    while ((match = flowConnRegex.exec(xml)) !== null) {
      const attrs = match[1];
      const source = this.extractAttr(attrs, 'sourcePathKey');
      const dest = this.extractAttr(attrs, 'destinationPathKey');
      const resultVal = this.extractAttr(attrs, 'executionFlowResultValue');
      if (source && dest) {
        model.flowConnections.push({
          sourcePathKey: source,
          destinationPathKey: dest,
          executionFlowResultValue: resultVal,
        });
      }
    }

    return model;
  }

  /**
   * Serializa o modelo tipado de volta para XML compatível com Eclipse EMF
   */
  public static serialize(model: DiagramModel): string {
    if (model.type === 'executionflow') {
      return this.serializeExecutionFlow(model);
    } else {
      return this.serializeStateFlow(model);
    }
  }

  private static serializeExecutionFlow(model: ExecutionFlowModel): string {
    const lines: string[] = [];
    lines.push('<?xml version="1.0" encoding="UTF-8"?>');
    
    let rootTag = '<com.csi.gmf.executionflow:ExecutionFlow xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:com.csi.gmf.executionflow="http:///com/csi/gmf/executionflow.ecore"';
    if (model.id) rootTag += ` id="${model.id}"`;
    if (model.title) rootTag += ` title="${model.title}"`;
    if (model.description !== undefined) rootTag += ` description="${model.description}"`;
    if (model.requestAuthorization) rootTag += ` requestAuthorization="${model.requestAuthorization}"`;
    rootTag += '>';
    lines.push(rootTag);

    if (model.startPoint) {
      lines.push('  <startPoint/>');
    }

    if (model.startPointConnection) {
      const destKey = model.startPointConnection.destinationObjectPathKey;
      lines.push(`  <startPointConnection${destKey ? ` destinationObjectPathKey="${destKey}"` : ''}/>`);
    }

    for (const obj of model.executableFlowObjects) {
      lines.push(`  <executableFlowObjects pathKey="${obj.pathKey}" className="${obj.className}" simpleClassName="${obj.simpleClassName}"/>`);
    }

    for (const sub of model.executionFlowObjects) {
      let tag = `  <executionFlowObjects pathKey="${sub.pathKey}" id="${sub.id}"`;
      if (sub.title) tag += ` title="${sub.title}"`;
      if (sub.description) tag += ` description="${sub.description}"`;
      if (sub.fileName) tag += ` fileName="${sub.fileName}"`;
      if (sub.filePath) tag += ` filePath="${sub.filePath}"`;
      tag += '/>';
      lines.push(tag);
    }

    for (const res of model.results) {
      lines.push(`  <results pathKey="${res.pathKey}" executionFlowResultValue="${res.executionFlowResultValue}"/>`);
    }

    for (const conn of model.flowConnections) {
      let tag = `  <flowConnections sourcePathKey="${conn.sourcePathKey}" destinationPathKey="${conn.destinationPathKey}"`;
      if (conn.executionFlowResultValue) {
        tag += ` executionFlowResultValue="${conn.executionFlowResultValue}"`;
      }
      tag += '/>';
      lines.push(tag);
    }

    lines.push('</com.csi.gmf.executionflow:ExecutionFlow>');
    return lines.join('\n');
  }

  private static serializeStateFlow(model: StateFlowModel): string {
    const lines: string[] = [];
    lines.push('<?xml version="1.0" encoding="UTF-8"?>');
    
    let rootTag = '<com.csi.gmf.stateflow:StateFlow xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:com.csi.gmf.stateflow="http:///com/csi/gmf/stateflow.ecore"';
    if (model.id) rootTag += ` id="${model.id}"`;
    if (model.description !== undefined) rootTag += ` description="${model.description}"`;
    if (model.volatile) rootTag += ` volatile="${model.volatile}"`;
    rootTag += '>';
    lines.push(rootTag);

    if (model.startPoint) {
      lines.push('  <startPoint/>');
    }

    if (model.startPointConnection) {
      const destState = model.startPointConnection.destinationStateId;
      lines.push(`  <startPointConnection${destState ? ` destinationStateId="${destState}"` : ''}/>`);
    }

    for (const state of model.states) {
      let tag = `  <states pathKey="${state.pathKey}" id="${state.id}"`;
      if (state.description) tag += ` description="${state.description}"`;
      tag += '/>';
      lines.push(tag);
    }

    for (const flow of model.executionFlows) {
      let tag = `  <executionFlows pathKey="${flow.pathKey}" id="${flow.id}"`;
      if (flow.title) tag += ` title="${flow.title}"`;
      if (flow.description) tag += ` description="${flow.description}"`;
      if (flow.filePath) tag += ` filePath="${flow.filePath}"`;
      tag += '/>';
      lines.push(tag);
    }

    for (const sConn of model.stateConnections) {
      lines.push(`  <stateConnections sourcePathKey="${sConn.sourcePathKey}" destinationPathKey="${sConn.destinationPathKey}" eventID="${sConn.eventID}"/>`);
    }

    for (const fConn of model.flowConnections) {
      let tag = `  <flowConnections sourcePathKey="${fConn.sourcePathKey}" destinationPathKey="${fConn.destinationPathKey}"`;
      if (fConn.executionFlowResultValue) {
        tag += ` executionFlowResultValue="${fConn.executionFlowResultValue}"`;
      }
      tag += '/>';
      lines.push(tag);
    }

    lines.push('</com.csi.gmf.stateflow:StateFlow>');
    return lines.join('\n');
  }

  private static extractAttr(attrs: string, name: string): string | null {
    const match = attrs.match(new RegExp(`\\b${name}="([^"]*)"`));
    return match ? match[1] : null;
  }
}
