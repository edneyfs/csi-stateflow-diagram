const fs = require('fs');
const path = require('path');

class JavaScanner {
  static cachedClasses = [];

  static async scanDirectory(directoryPath) {
    const results = [];

    async function walk(dir) {
      const entries = await fs.promises.readdir(dir, { withFileTypes: true });
      for (const entry of entries) {
        const fullPath = path.join(dir, entry.name);
        if (entry.isDirectory()) {
          if (entry.name !== '.git' && entry.name !== 'node_modules' && entry.name !== 'bin' && entry.name !== 'target') {
            await walk(fullPath);
          }
        } else if (entry.isFile() && entry.name.endsWith('.java')) {
          try {
            const content = await fs.promises.readFile(fullPath, 'utf-8');
            const pkgMatch = content.match(/package\s+([\w\.]+);/);
            const classMatch = content.match(/public\s+(?:abstract\s+)?class\s+(\w+)/);

            if (classMatch) {
              const simpleName = classMatch[1];
              const pkg = pkgMatch ? pkgMatch[1] : '';
              const fullName = pkg ? `${pkg}.${simpleName}` : simpleName;
              results.push({
                className: fullName,
                simpleClassName: simpleName,
                packageName: pkg,
                filePath: fullPath,
                isMic: simpleName.startsWith('Mic'),
              });
            }
          } catch {
            // Ignora arquivos ilegíveis
          }
        }
      }
    }

    if (fs.existsSync(directoryPath)) {
      await walk(directoryPath);
    }

    this.cachedClasses = results;
    return results;
  }

  static search(query, onlyMic = false) {
    const term = (query || '').toLowerCase().trim();
    return this.cachedClasses.filter((item) => {
      if (onlyMic && !item.isMic) return false;
      if (!term) return true;
      return (
        item.simpleClassName.toLowerCase().includes(term) ||
        item.packageName.toLowerCase().includes(term)
      );
    });
  }

  static getCached() {
    return this.cachedClasses;
  }
}

module.exports = { JavaScanner };
