import { describe, it, expect } from 'vitest';
import { XmlEngine } from '../src/services/xmlEngine';
import { ExecutionFlowModel } from '../src/types/model';

describe('XmlEngine Unit Tests (Round-Trip EMF/Ecore XML)', () => {
  const sampleExecutionFlowXml = `<?xml version="1.0" encoding="UTF-8"?>
<com.csi.gmf.executionflow:ExecutionFlow xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:com.csi.gmf.executionflow="http:///com/csi/gmf/executionflow.ecore" id="7026" title="7026_CONFIRMACAO_AJUSTES" description="Confirmacao de Ajustes de Venda" requestAuthorization="false">
  <startPoint/>
  <startPointConnection destinationObjectPathKey="1386704906523"/>
  <executableFlowObjects pathKey="1386704906523" className="com.csi.oper.pos.MicSalvaDisplay" simpleClassName="MicSalvaDisplay"/>
  <executableFlowObjects pathKey="1386704906524" className="com.csi.oper.pos.MicConfirmaAjustes" simpleClassName="MicConfirmaAjustes"/>
  <executableFlowObjects pathKey="1386704906525" className="com.csi.oper.pos.MicAbandonaAjustes" simpleClassName="MicAbandonaAjustes"/>
  <results pathKey="1386704906530" executionFlowResultValue="FINALIZA_AJUSTES"/>
  <flowConnections sourcePathKey="1386704906523" destinationPathKey="1386704906524" executionFlowResultValue="DEFAULT"/>
  <flowConnections sourcePathKey="1386704906524" destinationPathKey="1386704906525" executionFlowResultValue="ABANDONAR_AJUSTES"/>
  <flowConnections sourcePathKey="1386704906525" destinationPathKey="1386704906530" executionFlowResultValue="DEFAULT"/>
</com.csi.gmf.executionflow:ExecutionFlow>`;

  it('should parse ExecutionFlow XML into a structured typed model', () => {
    const model = XmlEngine.parse(sampleExecutionFlowXml) as ExecutionFlowModel;

    expect(model.type).toBe('executionflow');
    expect(model.id).toBe('7026');
    expect(model.title).toBe('7026_CONFIRMACAO_AJUSTES');
    expect(model.description).toBe('Confirmacao de Ajustes de Venda');
    expect(model.requestAuthorization).toBe(false);
    expect(model.startPoint?.exists).toBe(true);
    expect(model.startPointConnection?.destinationObjectPathKey).toBe('1386704906523');

    expect(model.executableFlowObjects).toHaveLength(3);
    expect(model.executableFlowObjects[0].simpleClassName).toBe('MicSalvaDisplay');
    expect(model.executableFlowObjects[0].className).toBe('com.csi.oper.pos.MicSalvaDisplay');

    expect(model.results).toHaveLength(1);
    expect(model.results[0].executionFlowResultValue).toBe('FINALIZA_AJUSTES');

    expect(model.flowConnections).toHaveLength(3);
    expect(model.flowConnections[1].executionFlowResultValue).toBe('ABANDONAR_AJUSTES');
  });

  it('should serialize model back into byte-compatible XML without data loss', () => {
    const model = XmlEngine.parse(sampleExecutionFlowXml);
    const serialized = XmlEngine.serialize(model);

    expect(serialized).toContain('<com.csi.gmf.executionflow:ExecutionFlow');
    expect(serialized).toContain('title="7026_CONFIRMACAO_AJUSTES"');
    expect(serialized).toContain('className="com.csi.oper.pos.MicSalvaDisplay"');
    expect(serialized).toContain('executionFlowResultValue="FINALIZA_AJUSTES"');
    expect(serialized).toContain('executionFlowResultValue="ABANDONAR_AJUSTES"');
  });
});
