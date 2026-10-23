const path = require('path');
const { execSync } = require('child_process');

console.log('📦 Iniciando empacotamento VSIX da extensão VS Code...');

const rootDir = path.join(__dirname, '..');
const extDir = path.join(rootDir, 'vscode-extension');

// 1. Executa o build da extensão primeiro
console.log('1/2 Executando build dos componentes e TypeScript...');
execSync('node scripts/build-vscode.js', { cwd: rootDir, stdio: 'inherit' });

// 2. Empacota com vsce
console.log('2/2 Gerando arquivo .vsix com vsce...');
execSync('npx @vscode/vsce package --no-dependencies --allow-missing-repository', { cwd: extDir, stdio: 'inherit' });

console.log('🎉 Extensão empacotada com sucesso em vscode-extension/!');
