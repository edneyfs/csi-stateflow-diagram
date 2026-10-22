/**
 * Script de Gravação de Fluxos E2E para Documentação Viva
 * Utiliza Playwright (canal MSEdge do sistema) para capturar frames dos fluxos felizes
 * 100% determinístico e local, sem dependência de agentes externos no build
 */
const { chromium } = require('playwright');
const http = require('http');
const fs = require('fs');
const path = require('path');

const PORT = 8089;
const DIST_DIR = path.join(__dirname, '../dist');
const FRAMES_BASE = path.join(__dirname, '../scratch/frames');

// Servidor estático ultra-leve para o build do Vite
function startStaticServer() {
  const mimeTypes = {
    '.html': 'text/html',
    '.js': 'application/javascript',
    '.css': 'text/css',
    '.svg': 'image/svg+xml',
    '.json': 'application/json',
  };

  const server = http.createServer((req, res) => {
    let reqPath = req.url.split('?')[0];
    if (reqPath === '/') reqPath = '/index.html';
    const filePath = path.join(DIST_DIR, reqPath);

    if (fs.existsSync(filePath) && fs.statSync(filePath).isFile()) {
      const ext = path.extname(filePath);
      res.writeHead(200, { 'Content-Type': mimeTypes[ext] || 'application/octet-stream' });
      fs.createReadStream(filePath).pipe(res);
    } else {
      const indexFallback = path.join(DIST_DIR, 'index.html');
      res.writeHead(200, { 'Content-Type': 'text/html' });
      fs.createReadStream(indexFallback).pipe(res);
    }
  });

  return new Promise((resolve) => {
    server.listen(PORT, () => {
      console.log(`[DOC-RECORD] Servidor de documentação iniciado em http://localhost:${PORT}`);
      resolve(server);
    });
  });
}

function ensureDir(dir) {
  if (!fs.existsSync(dir)) fs.mkdirSync(dir, { recursive: true });
}

async function record() {
  console.log('========================================================');
  console.log('🎬 Iniciando Gravação Determinística dos Fluxos da Documentação');
  console.log('========================================================');

  const server = await startStaticServer();
  const browser = await chromium.launch({
    channel: 'msedge',
    headless: true,
  });

  const context = await browser.newContext({
    viewport: { width: 1280, height: 720 },
  });

  const page = await context.newPage();

  try {
    // ------------------------------------------------------------------
    // FLUXO 1: ABRIR DIAGRAMA E LISTAR TODOS OS DIAGRAMAS DO WORKSPACE
    // ------------------------------------------------------------------
    console.log('[1/4] Gravando fluxo: abrir-diagrama...');
    const dirAbrir = path.join(FRAMES_BASE, 'abrir-diagrama');
    ensureDir(dirAbrir);

    await page.goto(`http://localhost:${PORT}`, { waitUntil: 'networkidle' });
    await page.waitForTimeout(1000);

    // Frame 1: Modal de inicialização de Workspace (se visível) ou tela inicial
    await page.screenshot({ path: path.join(dirAbrir, 'frame_01.png') });

    // Fecha o modal se estiver aberto clicando na confirmação
    const confirmBtn = page.locator('text=Confirmar Workspace');
    if (await confirmBtn.isVisible()) {
      await confirmBtn.click();
      await page.waitForTimeout(500);
    }
    await page.screenshot({ path: path.join(dirAbrir, 'frame_02.png') });

    // Filtra diagramas por 'Execution'
    const execTab = page.locator('button:has-text("Execution (")').first();
    if (await execTab.isVisible()) {
      await execTab.click();
      await page.waitForTimeout(400);
      await page.screenshot({ path: path.join(dirAbrir, 'frame_03.png') });
    }

    // Clica no diagrama 7026
    const diag7026 = page.locator('text=7026_CONFIRMACAO_AJUSTES.executionflow').first();
    if (await diag7026.isVisible()) {
      await diag7026.click();
      await page.waitForTimeout(600);
      await page.screenshot({ path: path.join(dirAbrir, 'frame_04.png') });
    }

    // ------------------------------------------------------------------
    // FLUXO 2: BUSCA CRUZADA DE REFERÊNCIAS (MIC*)
    // ------------------------------------------------------------------
    console.log('[2/4] Gravando fluxo: busca-referencias...');
    const dirBusca = path.join(FRAMES_BASE, 'busca-referencias');
    ensureDir(dirBusca);

    // Digita no filtro de classes Java
    const javaInput = page.locator('input[placeholder*="Filtrar classe Java"]');
    if (await javaInput.isVisible()) {
      await javaInput.fill('MicConfirma');
      await page.waitForTimeout(400);
      await page.screenshot({ path: path.join(dirBusca, 'frame_01.png') });
    }

    // Clica no botão de busca da classe MicConfirmaAjustes
    const searchBtn = page.locator('li:has-text("MicConfirmaAjustes") button').first();
    if (await searchBtn.isVisible()) {
      await searchBtn.click();
      await page.waitForTimeout(600);
      await page.screenshot({ path: path.join(dirBusca, 'frame_02.png') });
    }

    // Captura o drawer inferior com os diagramas encontrados
    await page.waitForTimeout(400);
    await page.screenshot({ path: path.join(dirBusca, 'frame_03.png') });

    // ------------------------------------------------------------------
    // FLUXO 3: MODELAR FLUXO & INSPECIONAR PROPRIEDADES
    // ------------------------------------------------------------------
    console.log('[3/4] Gravando fluxo: modelar-fluxo...');
    const dirModelar = path.join(FRAMES_BASE, 'modelar-fluxo');
    ensureDir(dirModelar);

    // Fecha o drawer de referências se estiver aberto
    const closeDrawerBtn = page.locator('div.h-44 button').first();
    if (await closeDrawerBtn.isVisible()) {
      await closeDrawerBtn.click();
      await page.waitForTimeout(300);
    }

    // Clica em um nó executável do canvas
    const nodeEl = page.locator('.react-flow__node-executable').first();
    if (await nodeEl.isVisible()) {
      await nodeEl.click();
      await page.waitForTimeout(500);
      await page.screenshot({ path: path.join(dirModelar, 'frame_01.png') });

      // Abre menu de contexto no nó
      await nodeEl.click({ button: 'right' });
      await page.waitForTimeout(400);
      await page.screenshot({ path: path.join(dirModelar, 'frame_02.png') });
    }

    // ------------------------------------------------------------------
    // FLUXO 4: SALVAR XML COMPATÍVEL
    // ------------------------------------------------------------------
    console.log('[4/4] Gravando fluxo: salvar-xml...');
    const dirSalvar = path.join(FRAMES_BASE, 'salvar-xml');
    ensureDir(dirSalvar);

    const saveBtn = page.locator('button:has-text("Salvar XML")');
    if (await saveBtn.isVisible()) {
      await saveBtn.hover();
      await page.waitForTimeout(300);
      await page.screenshot({ path: path.join(dirSalvar, 'frame_01.png') });
      await page.screenshot({ path: path.join(dirSalvar, 'frame_02.png') });
    }

    console.log('✅ Todos os frames dos fluxos capturados com sucesso em scratch/frames!');
  } finally {
    await browser.close();
    server.close();
  }
}

record().catch((err) => {
  console.error('Erro na gravação dos fluxos:', err);
  process.exit(1);
});
