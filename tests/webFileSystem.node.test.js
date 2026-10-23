const test = require('node:test');
const assert = require('node:assert');
const { WebFileSystemService } = require('../src/services/webFileSystem');

// Mock de FileSystemFileHandle e FileSystemDirectoryHandle para ambiente Node/Testes
function createMockFileHandle(name, initialContent = '') {
  let content = initialContent;
  return {
    kind: 'file',
    name,
    getFile: async () => ({
      size: content.length,
      text: async () => content,
    }),
    createWritable: async () => {
      let buffer = '';
      return {
        write: async (chunk) => {
          buffer += chunk;
        },
        close: async () => {
          content = buffer;
        },
      };
    },
    _getContent: () => content,
  };
}

function createMockDirectoryHandle(name, entries = {}) {
  const store = new Map(Object.entries(entries));
  return {
    kind: 'directory',
    name,
    values: async function* () {
      for (const val of store.values()) {
        yield val;
      }
    },
    getDirectoryHandle: async (dirName, opts) => {
      if (!store.has(dirName)) {
        if (opts?.create) {
          const sub = createMockDirectoryHandle(dirName);
          store.set(dirName, sub);
          return sub;
        }
        throw new Error(`Directory not found: ${dirName}`);
      }
      return store.get(dirName);
    },
    getFileHandle: async (fileName, opts) => {
      if (!store.has(fileName)) {
        if (opts?.create) {
          const file = createMockFileHandle(fileName);
          store.set(fileName, file);
          return file;
        }
        throw new Error(`File not found: ${fileName}`);
      }
      return store.get(fileName);
    },
    _getEntry: (k) => store.get(k),
  };
}

test('WebFileSystemService - Gravação Atômica com Backup (.bak) e Padronização CRLF', async () => {
  const originalXml = '<ExecutionFlow id="test">\n  <startPoint/>\n</ExecutionFlow>';
  const initialFile = createMockFileHandle('fluxo.executionflow', originalXml);
  const root = createMockDirectoryHandle('root', {
    'fluxo.executionflow': initialFile,
  });

  const newXml = '<ExecutionFlow id="test_updated">\n  <startPoint/>\n  <executableFlowObjects/>\n</ExecutionFlow>';

  const result = await WebFileSystemService.writeFile('fluxo.executionflow', newXml, root, true);

  assert.strictEqual(result.success, true);
  assert.strictEqual(result.backupPath, 'fluxo.executionflow.bak');

  // Verifica se o arquivo .bak foi criado com o conteúdo original
  const bakHandle = root._getEntry('fluxo.executionflow.bak');
  assert.ok(bakHandle, 'Arquivo .bak deve existir');
  const bakContent = await (await bakHandle.getFile()).text();
  assert.strictEqual(bakContent, originalXml);

  // Verifica se o arquivo principal foi atualizado com quebras de linha CRLF (\r\n)
  const updatedHandle = root._getEntry('fluxo.executionflow');
  const updatedContent = await (await updatedHandle.getFile()).text();
  assert.ok(updatedContent.includes('\r\n'), 'Deve conter quebras de linha CRLF');
  assert.ok(updatedContent.includes('test_updated'));
});

test('WebFileSystemService - Varredura de Diagramas em mock de diretório', async () => {
  const root = createMockDirectoryHandle('workspace', {
    '7026_CONFIRMACAO.executionflow': createMockFileHandle('7026_CONFIRMACAO.executionflow', '<xml/>'),
    'SF_VENDA.stateflow': createMockFileHandle('SF_VENDA.stateflow', '<xml/>'),
    '7026_CONFIRMACAO.executionflow_diagram': createMockFileHandle('7026_CONFIRMACAO.executionflow_diagram', '<xml/>'),
    'README.txt': createMockFileHandle('README.txt', 'docs'),
  });

  const diagrams = await WebFileSystemService.scanDiagrams(root);

  assert.strictEqual(diagrams.length, 3);
  assert.ok(diagrams.some(d => d.name === '7026_CONFIRMACAO.executionflow' && d.type === 'executionflow'));
  assert.ok(diagrams.some(d => d.name === 'SF_VENDA.stateflow' && d.type === 'stateflow'));
  assert.ok(diagrams.some(d => d.isDiagramLayout === true));
});

test('WebFileSystemService - Varredura de Classes Java e extração de Mic*', async () => {
  const javaCode = `
package com.csi.oper.pos;

public class MicConfirmaAjustes extends GenericMic {
    public void execute() {}
}
`;
  const root = createMockDirectoryHandle('workspace', {
    'MicConfirmaAjustes.java': createMockFileHandle('MicConfirmaAjustes.java', javaCode),
    'Ignorar.txt': createMockFileHandle('Ignorar.txt', 'texto'),
  });

  const classes = await WebFileSystemService.scanJavaClasses(root);

  assert.strictEqual(classes.length, 1);
  assert.strictEqual(classes[0].simpleClassName, 'MicConfirmaAjustes');
  assert.strictEqual(classes[0].packageName, 'com.csi.oper.pos');
  assert.strictEqual(classes[0].className, 'com.csi.oper.pos.MicConfirmaAjustes');
  assert.strictEqual(classes[0].isMic, true);
});

test('WebFileSystemService - Busca de Referências a classes em diagramas', async () => {
  const sampleXml = `<?xml version="1.0" encoding="UTF-8"?>
<com.csi.gmf.executionflow:ExecutionFlow xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:com.csi.gmf.executionflow="http:///com/csi/gmf/executionflow.ecore" id="7026" title="7026_CONFIRMACAO_AJUSTES">
  <startPoint/>
  <executableFlowObjects pathKey="1386704906523" className="com.csi.oper.pos.MicSalvaDisplay" simpleClassName="MicSalvaDisplay"/>
</com.csi.gmf.executionflow:ExecutionFlow>`;

  const root = createMockDirectoryHandle('workspace', {
    '7026_CONFIRMACAO.executionflow': createMockFileHandle('7026_CONFIRMACAO.executionflow', sampleXml),
  });

  const refs = await WebFileSystemService.findReferences('MicSalvaDisplay', root);

  assert.strictEqual(refs.length, 1);
  assert.strictEqual(refs[0].diagramName, '7026_CONFIRMACAO.executionflow');
  assert.strictEqual(refs[0].simpleClassName, 'MicSalvaDisplay');
  assert.strictEqual(refs[0].nodePathKey, '1386704906523');
});
