const test = require('node:test');
const assert = require('node:assert');
const { XmlEngine } = require('../src/services/xmlEngine');
const { GraphConverter } = require('../src/services/graphConverter');

test('GraphConverter - Conversão de ExecutionFlow para Nós e Arestas do React Flow', () => {
  const xml = `<?xml version="1.0" encoding="UTF-8"?>
<com.csi.gmf.executionflow:ExecutionFlow xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:com.csi.gmf.executionflow="http:///com/csi/gmf/executionflow.ecore" id="7026" title="7026_CONFIRMACAO_AJUSTES">
  <startPoint/>
  <startPointConnection destinationObjectPathKey="1386704906523"/>
  <executableFlowObjects pathKey="1386704906523" className="com.csi.oper.pos.MicSalvaDisplay" simpleClassName="MicSalvaDisplay"/>
  <executableFlowObjects pathKey="1386704906524" className="com.csi.oper.pos.MicConfirmaAjustes" simpleClassName="MicConfirmaAjustes"/>
  <results pathKey="1386704906530" executionFlowResultValue="FINALIZA_AJUSTES"/>
  <flowConnections sourcePathKey="1386704906523" destinationPathKey="1386704906524" executionFlowResultValue="DEFAULT"/>
  <flowConnections sourcePathKey="1386704906524" destinationPathKey="1386704906530" executionFlowResultValue="DEFAULT"/>
</com.csi.gmf.executionflow:ExecutionFlow>`;

  const model = XmlEngine.parse(xml);
  const { nodes, edges } = GraphConverter.modelToGraph(model);

  assert.strictEqual(nodes.length, 4, 'Deve conter 4 nós (1 startPoint, 2 executáveis, 1 result)');
  assert.strictEqual(edges.length, 3, 'Deve conter 3 arestas (1 startConn + 2 flowConns)');

  const startNode = nodes.find((n) => n.id === 'start-point');
  assert.ok(startNode, 'Nó start-point deve existir');
  assert.strictEqual(startNode.position.x, 50);

  const micSalva = nodes.find((n) => n.id === '1386704906523');
  assert.ok(micSalva, 'Nó MicSalvaDisplay deve existir');
  assert.strictEqual(micSalva.data.title, 'MicSalvaDisplay');
  assert.strictEqual(micSalva.position.x, 280, 'Segunda camada x deve ser 50 + 1 * 230 = 280');

  const micConfirma = nodes.find((n) => n.id === '1386704906524');
  assert.ok(micConfirma, 'Nó MicConfirmaAjustes deve existir');
  assert.strictEqual(micConfirma.position.x, 510, 'Terceira camada x deve ser 280 + 230 = 510');

  const resultNode = nodes.find((n) => n.id === '1386704906530');
  assert.ok(resultNode, 'Nó de resultado deve existir');
  assert.strictEqual(resultNode.data.title, 'FINALIZA_AJUSTES');
  assert.strictEqual(resultNode.position.x, 740, 'Quarta camada x deve ser 510 + 230 = 740');
});

test('GraphConverter - Conversão de StateFlow para Nós e Arestas do React Flow', () => {
  const xml = `<?xml version="1.0" encoding="UTF-8"?>
<com.csi.gmf.stateflow:StateFlow xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:com.csi.gmf.stateflow="http:///com/csi/gmf/stateflow.ecore" id="SF_01">
  <startPoint/>
  <startPointConnection destinationStateId="ST_INIT"/>
  <states pathKey="1001" id="ST_INIT" description="Estado Inicial"/>
  <states pathKey="1002" id="ST_RUN" description="Estado Executando"/>
  <stateConnections sourcePathKey="1001" destinationPathKey="1002" eventID="EVT_START"/>
</com.csi.gmf.stateflow:StateFlow>`;

  const model = XmlEngine.parse(xml);
  const { nodes, edges } = GraphConverter.modelToGraph(model);

  assert.strictEqual(nodes.length, 3, 'Deve conter 3 nós (startPoint + 2 states)');
  assert.strictEqual(edges.length, 2, 'Deve conter 2 arestas (startConn + stateConn)');

  const edge = edges.find((e) => e.data.label === 'EVT_START');
  assert.ok(edge, 'Aresta de transição com rótulo EVT_START deve existir');
  assert.strictEqual(edge.source, '1001');
  assert.strictEqual(edge.target, '1002');
});

test('GraphConverter - Aplicação de Coordenadas Visuais Originais do Eclipse Notation (.diagram)', () => {
  const xml = `<?xml version="1.0" encoding="UTF-8"?>
<com.csi.gmf.executionflow:ExecutionFlow xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:com.csi.gmf.executionflow="http:///com/csi/gmf/executionflow.ecore" title="CARGA_DE_BASE" id="1126">
  <executableFlowObjects pathKey="1432750890148" className="com.csi.oper.av.MicSolicitaBaseACarregar" simpleClassName="MicSolicitaBaseACarregar"/>
  <results pathKey="1432752232864" executionFlowResultValue="MicSolicitaBaseACarregar"/>
  <flowConnections sourcePathKey="1432750890148" destinationPathKey="1432752232864" executionFlowResultValue="DEFAULT"/>
  <startPoint/>
  <startPointConnection destinationObjectPathKey="1432750890148"/>
</com.csi.gmf.executionflow:ExecutionFlow>`;

  const notationXml = `<?xml version="1.0" encoding="UTF-8"?>
<notation:Diagram xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:com.csi.gmf.executionflow="http:///com/csi/gmf/executionflow.ecore" xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.2/notation" type="Executionflow">
  <children xmi:type="notation:Node" type="1004">
    <element href="1126_CARGA_DE_BASE.executionflow#//@startPoint"/>
    <layoutConstraint xmi:type="notation:Bounds" x="130" y="210"/>
  </children>
  <children xmi:type="notation:Node" type="1002">
    <element href="1126_CARGA_DE_BASE.executionflow#//@executableFlowObjects.0"/>
    <layoutConstraint xmi:type="notation:Bounds" x="65" y="115"/>
  </children>
  <children xmi:type="notation:Node" type="1003">
    <element href="1126_CARGA_DE_BASE.executionflow#//@results.0"/>
    <layoutConstraint xmi:type="notation:Bounds" x="65" y="45"/>
  </children>
</notation:Diagram>`;

  const model = XmlEngine.parse(xml);
  const notationPositions = XmlEngine.parseNotationBounds(notationXml);
  const { nodes, edges } = GraphConverter.modelToGraph(model, notationPositions);

  assert.strictEqual(nodes.length, 3);
  assert.strictEqual(edges.length, 2);

  const startNode = nodes.find((n) => n.id === 'start-point');
  assert.strictEqual(startNode.position.x, 130);
  assert.strictEqual(startNode.position.y, 210);

  const micNode = nodes.find((n) => n.id === '1432750890148');
  assert.strictEqual(micNode.position.x, 65);
  assert.strictEqual(micNode.position.y, 115);

  const resNode = nodes.find((n) => n.id === '1432752232864');
  assert.strictEqual(resNode.position.x, 65);
  assert.strictEqual(resNode.position.y, 45);
});
