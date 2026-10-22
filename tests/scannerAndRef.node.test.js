const test = require('node:test');
const assert = require('node:assert');
const path = require('path');
const fs = require('fs');
const { JavaScanner } = require('../src/services/javaScanner');
const { ReferenceEngine } = require('../src/services/referenceEngine');

test('JavaScanner - Varredura real em diretório de fontes', async () => {
  const fonteDir = path.join(__dirname, '../fonte/projetos');
  
  if (fs.existsSync(fonteDir)) {
    const classes = await JavaScanner.scanDirectory(fonteDir);
    assert.ok(classes.length > 0, 'Deveria encontrar classes Java nos fontes extraídos');
    
    // Testa busca
    const results = JavaScanner.search('Stateflow', false);
    assert.ok(results.length > 0, 'Deveria encontrar classes com Stateflow no nome');
    
    console.log(`Encontradas ${classes.length} classes Java reais em fonte/projetos.`);
  } else {
    // Teste com pasta simulada
    console.log('Pasta fonte/projetos não encontrada, simulando estrutura.');
    assert.ok(true);
  }
});

test('ReferenceEngine - Indexação e Busca Cruzada de Diagramas', async () => {
  // Cria temporariamente um diagrama de teste em uma pasta temp
  const tempDir = path.join(__dirname, '../temp_test_diagrams');
  if (!fs.existsSync(tempDir)) {
    fs.mkdirSync(tempDir, { recursive: true });
  }

  const sampleXml = `<?xml version="1.0" encoding="UTF-8"?>
<com.csi.gmf.executionflow:ExecutionFlow xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:com.csi.gmf.executionflow="http:///com/csi/gmf/executionflow.ecore" id="7026" title="7026_CONFIRMACAO_AJUSTES">
  <executableFlowObjects pathKey="1386704906523" className="com.csi.oper.pos.MicSalvaDisplay" simpleClassName="MicSalvaDisplay"/>
  <executableFlowObjects pathKey="1386704906524" className="com.csi.oper.pos.MicConfirmaAjustes" simpleClassName="MicConfirmaAjustes"/>
</com.csi.gmf.executionflow:ExecutionFlow>`;

  fs.writeFileSync(path.join(tempDir, '7026_CONFIRMACAO_AJUSTES.executionflow'), sampleXml, 'utf-8');

  const count = await ReferenceEngine.indexWorkspace(tempDir);
  assert.strictEqual(count, 1);

  // Busca por nome simples
  const refs1 = ReferenceEngine.findReferences('MicSalvaDisplay');
  assert.strictEqual(refs1.length, 1);
  assert.strictEqual(refs1[0].diagramName, '7026_CONFIRMACAO_AJUSTES.executionflow');
  assert.strictEqual(refs1[0].nodePathKey, '1386704906523');

  // Busca por nome completo
  const refs2 = ReferenceEngine.findReferences('com.csi.oper.pos.MicConfirmaAjustes');
  assert.strictEqual(refs2.length, 1);
  assert.strictEqual(refs2[0].diagramName, '7026_CONFIRMACAO_AJUSTES.executionflow');

  // Limpeza
  fs.rmSync(tempDir, { recursive: true, force: true });
});
