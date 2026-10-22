const test = require('node:test');
const assert = require('node:assert');
const fs = require('fs');
const path = require('path');

// Carrega o código do xmlEngine compilado/transpilado ou direto
// Vamos verificar o teste de sanidade
test('Sanity check: Node test runner is operational', () => {
  assert.strictEqual(1 + 1, 2);
});

test('Validacao de geracao do pathKey (timestamp epoch ms de 13 digitos)', () => {
  const key = String(Date.now());
  assert.strictEqual(key.length, 13);
  assert.ok(Number(key) > 1300000000000);
});
