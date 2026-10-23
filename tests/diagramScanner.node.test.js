const test = require('node:test');
const assert = require('node:assert');
const path = require('path');
const fs = require('fs');
const { DiagramScanner } = require('../src/services/diagramScanner');

test('DiagramScanner - Varredura e Listagem de TODOS os Diagramas no Workspace', async () => {
  const tempWorkspace = path.join(__dirname, '../temp_test_workspace');
  const subPdv = path.join(tempWorkspace, 'configuracao/padrao/diagramas/pdv');
  const subRetaguarda = path.join(tempWorkspace, 'configuracao/padrao/diagramas/retaguarda');

  fs.mkdirSync(subPdv, { recursive: true });
  fs.mkdirSync(subRetaguarda, { recursive: true });

  // Cria arquivos de teste simulando a árvore do projeto
  fs.writeFileSync(path.join(subPdv, '7025_AJUSTES_DURANTE_VENDA.executionflow'), '<xml></xml>');
  fs.writeFileSync(path.join(subPdv, '7025_AJUSTES_DURANTE_VENDA.executionflow_diagram'), '<xml></xml>');
  fs.writeFileSync(path.join(subPdv, '7026_CONFIRMACAO_AJUSTES.executionflow'), '<xml></xml>');
  fs.writeFileSync(path.join(subRetaguarda, 'SF_RETAGUARDA_01.stateflow'), '<xml></xml>');
  fs.writeFileSync(path.join(tempWorkspace, 'outro_arquivo.txt'), 'ignorar');

  const diagrams = await DiagramScanner.scanWorkspaceDiagrams(tempWorkspace);

  // Deve encontrar exatamente 4 diagramas
  assert.strictEqual(diagrams.length, 4);

  // Verifica tipos identificados
  const executionFlows = diagrams.filter(d => d.type === 'executionflow');
  const stateFlows = diagrams.filter(d => d.type === 'stateflow');
  assert.strictEqual(executionFlows.length, 3);
  assert.strictEqual(stateFlows.length, 1);

  // Verifica pastas relativas
  assert.ok(diagrams.some(d => d.folder.includes('pdv')));
  assert.ok(diagrams.some(d => d.name === '7026_CONFIRMACAO_AJUSTES.executionflow'));
  assert.ok(diagrams.some(d => d.name === 'SF_RETAGUARDA_01.stateflow'));

  // Verifica identificação de companion layout (.diagram)
  const f7025 = diagrams.find(d => d.name === '7025_AJUSTES_DURANTE_VENDA.executionflow');
  const f7026 = diagrams.find(d => d.name === '7026_CONFIRMACAO_AJUSTES.executionflow');
  assert.strictEqual(f7025.hasCompanionLayout, true, '7025 deve possuir companion layout');
  assert.strictEqual(f7026.hasCompanionLayout, false, '7026 não possui companion layout');

  // Limpeza
  fs.rmSync(tempWorkspace, { recursive: true, force: true });
});
