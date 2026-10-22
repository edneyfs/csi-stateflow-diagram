const test = require('node:test');
const assert = require('node:assert');
const { XmlEngine } = require('../src/services/xmlEngine');

describe = test.describe;
it = test.it;

test('XmlEngine - Parsing & Serialization de ExecutionFlow (7026_CONFIRMACAO_AJUSTES)', () => {
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

  // 1. Parse
  const model = XmlEngine.parse(sampleExecutionFlowXml);
  assert.strictEqual(model.type, 'executionflow');
  assert.strictEqual(model.id, '7026');
  assert.strictEqual(model.title, '7026_CONFIRMACAO_AJUSTES');
  assert.strictEqual(model.description, 'Confirmacao de Ajustes de Venda');
  assert.strictEqual(model.requestAuthorization, false);
  assert.strictEqual(model.startPoint?.exists, true);
  assert.strictEqual(model.startPointConnection?.destinationObjectPathKey, '1386704906523');

  assert.strictEqual(model.executableFlowObjects.length, 3);
  assert.strictEqual(model.executableFlowObjects[0].simpleClassName, 'MicSalvaDisplay');
  assert.strictEqual(model.executableFlowObjects[0].className, 'com.csi.oper.pos.MicSalvaDisplay');

  assert.strictEqual(model.results.length, 1);
  assert.strictEqual(model.results[0].executionFlowResultValue, 'FINALIZA_AJUSTES');

  assert.strictEqual(model.flowConnections.length, 3);
  assert.strictEqual(model.flowConnections[1].executionFlowResultValue, 'ABANDONAR_AJUSTES');

  // 2. Serialize Round-Trip
  const serialized = XmlEngine.serialize(model);
  assert.ok(serialized.includes('<com.csi.gmf.executionflow:ExecutionFlow'));
  assert.ok(serialized.includes('title="7026_CONFIRMACAO_AJUSTES"'));
  assert.ok(serialized.includes('className="com.csi.oper.pos.MicSalvaDisplay"'));
  assert.ok(serialized.includes('executionFlowResultValue="FINALIZA_AJUSTES"'));
  assert.ok(serialized.includes('executionFlowResultValue="ABANDONAR_AJUSTES"'));
});

test('XmlEngine - Parsing & Serialization de StateFlow', () => {
  const sampleStateFlowXml = `<?xml version="1.0" encoding="UTF-8"?>
<com.csi.gmf.stateflow:StateFlow xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:com.csi.gmf.stateflow="http:///com/csi/gmf/stateflow.ecore" id="SF_PDV_01" description="Fluxo Principal PDV" volatile="false">
  <startPoint/>
  <startPointConnection destinationStateId="STATE_INICIAL"/>
  <states pathKey="1386704906100" id="STATE_INICIAL" description="Estado de Espera de Cupom"/>
  <states pathKey="1386704906101" id="STATE_EM_VENDA" description="Registro de Itens"/>
  <executionFlows pathKey="1386704906200" id="FLOW_ABRE_CUPOM" title="Abertura de Cupom" filePath="7001_ABERTURA.executionflow"/>
  <stateConnections sourcePathKey="1386704906100" destinationPathKey="1386704906200" eventID="EVT_INICIAR_VENDA"/>
  <flowConnections sourcePathKey="1386704906200" destinationPathKey="1386704906101" executionFlowResultValue="SUCESSO"/>
</com.csi.gmf.stateflow:StateFlow>`;

  const model = XmlEngine.parse(sampleStateFlowXml);
  assert.strictEqual(model.type, 'stateflow');
  assert.strictEqual(model.id, 'SF_PDV_01');
  assert.strictEqual(model.states.length, 2);
  assert.strictEqual(model.executionFlows.length, 1);
  assert.strictEqual(model.stateConnections.length, 1);
  assert.strictEqual(model.stateConnections[0].eventID, 'EVT_INICIAR_VENDA');
  assert.strictEqual(model.flowConnections[0].executionFlowResultValue, 'SUCESSO');

  const serialized = XmlEngine.serialize(model);
  assert.ok(serialized.includes('<com.csi.gmf.stateflow:StateFlow'));
  assert.ok(serialized.includes('eventID="EVT_INICIAR_VENDA"'));
  assert.ok(serialized.includes('id="STATE_INICIAL"'));
});
