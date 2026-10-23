const fs = require('fs');
const path = require('path');
const { execSync } = require('child_process');

console.log('🚀 Iniciando compilação do CSI StateFlow para VS Code...');

// 1. Diretórios
const rootDir = path.join(__dirname, '..');
const distDir = path.join(rootDir, 'dist');
const extDir = path.join(rootDir, 'vscode-extension');
const extWebviewDir = path.join(extDir, 'dist-webview');

// 2. Compilar o frontend web se necessário ou garantir que dist existe
console.log('📦 1/3 Compilando assets do React Webview (npm run build)...');
execSync('npm run build', { cwd: rootDir, stdio: 'inherit' });

// 3. Sincronizar dist/ para vscode-extension/dist-webview/
console.log('📂 2/3 Sincronizando assets para vscode-extension/dist-webview...');
if (fs.existsSync(extWebviewDir)) {
  fs.rmSync(extWebviewDir, { recursive: true, force: true });
}
fs.mkdirSync(extWebviewDir, { recursive: true });

function copyRecursive(src, dest) {
  const entries = fs.readdirSync(src, { withFileTypes: true });
  for (const entry of entries) {
    const srcPath = path.join(src, entry.name);
    const destPath = path.join(dest, entry.name);
    if (entry.isDirectory()) {
      fs.mkdirSync(destPath, { recursive: true });
      copyRecursive(srcPath, destPath);
    } else {
      fs.copyFileSync(srcPath, destPath);
    }
  }
}

copyRecursive(distDir, extWebviewDir);

// 4. Compilar o TypeScript da extensão
console.log('⚡ 3/4 Compilando TypeScript da extensão VS Code...');
execSync('npx tsc -p vscode-extension/tsconfig.json', { cwd: rootDir, stdio: 'inherit' });

// 5. Copiar package.json versão sincronizada
const mainPkg = JSON.parse(fs.readFileSync(path.join(rootDir, 'package.json'), 'utf8'));
const extPkgPath = path.join(extDir, 'package.json');
const extPkg = JSON.parse(fs.readFileSync(extPkgPath, 'utf8'));
extPkg.version = mainPkg.version;
fs.writeFileSync(extPkgPath, JSON.stringify(extPkg, null, 2) + '\n', 'utf8');

console.log(`✅ 4/4 Sincronização e compilação concluídas com sucesso! Extensão VS Code pronta na versão v${mainPkg.version}`);
