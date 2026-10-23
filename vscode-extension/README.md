# CSI StateFlow Visual Studio (Extensão para VS Code)

Extensão oficial do **CSI StateFlow Studio** para Visual Studio Code. Permite visualizar e editar diagramas de fluxo de execução (`.executionflow`) e máquinas de estado (`.stateflow`) da arquitetura CSI / P2K diretamente no VS Code, com sincronização bidirecional em tempo real.

## 🚀 Recursos

- **100% Integrado ao VS Code:** Abre arquivos `.executionflow` e `.stateflow` como abas nativas (`CustomTextEditor`).
- **Sincronização Bidirecional:** Qualquer alteração no diagrama atualiza o buffer do VS Code (Ctrl+S, desfazer/refazer e diff do Git totalmente suportados).
- **Zero Poluição Visual:** Barra lateral de arquivos e botões manuais de salvar são substituídos pela árvore e comandos nativos do VS Code.
- **Auto-Descoberta de Classes MIC:** Varredura em segundo plano de arquivos `Mic*.java` no projeto para autocompletar na paleta e no painel de propriedades.
- **Edição Completa de Transições:** Suporte a duplo-clique inline e edição de rótulos (`DEFAULT`, `CONFIRMAR`, `SUCESSO`, `ERRO`, etc.) no painel lateral.

## 📦 Como Instalar

```bash
code --install-extension csi-stateflow-vscode-0.1.9.vsix
```

## 🛠️ Como Usar

1. Abra qualquer repositório CSI/P2K no VS Code.
2. Dê um duplo-clique em qualquer arquivo `.executionflow` ou `.stateflow` no explorador de arquivos.
3. O editor visual abrirá automaticamente como editor padrão.
4. Para alternar para visualização de texto puro XML, clique com o botão direito no arquivo > **Abrir com...** > **Editor de Texto**.
