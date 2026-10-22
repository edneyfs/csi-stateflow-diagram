# CSI StateFlow Studio ⚙️

> **Studio Desktop autônomo para modelagem visual de Máquinas de Estado (`.stateflow`) e Fluxos de Execução (`.executionflow`) CSI / P2K.**  
> Substitui com fidelidade visual absoluta o antigo plugin Eclipse Luna GMF de 2010 (`com.csi.gmf.*`), eliminando qualquer dependência do Eclipse e do Java 7, mantendo **100% de compatibilidade semântica com os XMLs legados consumidos pela aplicação em produção**.

---

## 📋 Sumário
- [Visão Geral](#-visão-geral)
- [Principais Funcionalidades](#-principais-funcionalidades)
- [Arquitetura & Tecnologias](#-arquitetura--tecnologias)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Pré-requisitos](#-pré-requisitos)
- [Guia de Comandos](#-guia-de-comandos)
- [Central de Documentação Viva & GIFs](#-central-de-documentação-viva--gifs)
- [Compatibilidade XML com P2K](#-compatibilidade-xml-com-p2k)
- [Testes Automatizados](#-testes-automatizados)

---

## 🎯 Visão Geral

O ecossistema legado do P2K utilizava plugins Eclipse baseados em GMF (Graphical Modeling Framework) e EMF (Eclipse Modeling Framework) datados de 2010 para a configuração visual dos fluxos de PDV e retaguarda. Versões modernas de IDEs e JDKs deixaram de suportar essas extensões e o runtime Java 7.

O **CSI StateFlow Studio** foi desenvolvido como uma solução Desktop moderna, veloz e desacoplada:
1. **Zero Dependência do Eclipse**: Executa de forma nativa e independente em ambiente Windows (Electron).
2. **Compatibilidade Semântica Estrita**: Lê e gera exatamente os mesmos arquivos XML consumidos pelos motores da máquina de estados em produção (`http:///com/csi/gmf/executionflow.ecore` e `stateflow.ecore`).
3. **Usabilidade Pro**: Canvas de alta performance com zoom/pan suave, minimapa, auto-layout DAG, atalhos de teclado e busca cruzada de classes em tempo real.

---

## ✨ Principais Funcionalidades

### 1. Seleção de Workspace na Inicialização
- Ao iniciar a aplicação, um diálogo modal solicita a pasta do projeto (ex: `C:\workspace\p2k-trunk`).
- O workspace fica persistido localmente e pode ser alterado a qualquer momento pelo botão **"Trocar Workspace"** no cabeçalho.

### 2. Painel Lateral com TODOS os Diagramas do Workspace
- Varredura recursiva de diretórios (`DiagramScanner`), identificando todos os arquivos `.executionflow` e `.stateflow`.
- Filtros rápidos por texto e abas de seleção: **Todos**, **ExecutionFlow** e **StateFlow**.
- Identificação visual imediata: badges de tipo (`EXEC` em azul, `STATE` em âmbar) e exibição da pasta de origem (`pdv`, `retaguarda`, etc.).
- Clique direto para abrir e carregar o diagrama no canvas com auto-layout em camadas.

### 3. Canvas Visual Fiel ao Eclipse Luna
- Renderizado com `@xyflow/react` (React Flow v12):
  - **Start Point**: Círculo preto de inicialização com link tracejado.
  - **Executable Flow Object**: Cartão azul suave (`#EAF1FB`, borda `#6C8EBF`) com ícone de engrenagem e identificação da classe Java `MIC*`.
  - **Execution Flow Object**: Cartão dourado com ícone de diagrama para subfluxos encadeados.
  - **Execution Flow Result**: Pílula verde com bandeira indicando o valor de retorno (`DEFAULT`, `FINALIZA_AJUSTES`, etc.).
  - **Sticky Notes**: Post-it amarelo redimensionável para notas contextuais.
  - **Conexões Ortogonais em 90°**: Rotas de transição magnéticas nos 4 lados dos nós com rótulos de condição interativos.

### 4. Scanner de Classes Java & Convenção `MIC*`
- Indexador assíncrono de código-fonte (`JavaScanner`) capaz de varrer centenas de arquivos `.java` em milissegundos.
- Filtro ativado por padrão para classes prefixadas com `MIC*` (ex: `MicSalvaDisplay`, `MicConfirmaAjustes`, `MicAberturaCupom`), com opção de alternar para todas as classes do projeto.

### 5. Busca Cruzada de Referências ("Search ExecutionFlow References...")
- Reprodução idêntica da funcionalidade clássica do Eclipse:
  - Clique com o botão direito em qualquer nó do canvas ou em uma classe Java na lista.
  - Selecione **"Search execution flow diagram references..."**.
  - Uma gaveta inferior lista instantaneamente todos os diagramas em que aquela classe participa e o ID único do nó (`pathKey`), permitindo navegação imediata.

### 6. Inspetor de Propriedades do Elemento
- Painel lateral direito exibindo tipo do nó, Simple Class Name, Class Name com o pacote completo (`com.csi.oper.pos.*`) e o `pathKey` (identificador epoch de 13 dígitos).

---

## 🏗️ Arquitetura & Tecnologias

```
csi-stateflow-diagram/
├── electron/                   # Processo principal do Electron & IPC Preload
│   ├── main.ts                 # Criação da janela, menus nativos e handlers de sistema de arquivos
│   └── preload.ts              # API segura exposta para o contexto do navegador (contextBridge)
├── src/
│   ├── components/
│   │   ├── canvas/             # DiagramCanvas.tsx (React Flow v12, Minimapa, Controles)
│   │   ├── nodes/              # Nós customizados (StartPoint, Executable, Subflow, Result, StickyNote)
│   │   └── edges/              # OrthogonalEdge.tsx (Arestas ortogonais em 90° com rótulos)
│   ├── services/
│   │   ├── xmlEngine.ts        # Parser & Serializador bidirecional Ecore XMI / XML
│   │   ├── javaScanner.ts      # Scanner de classes Java com regex compilada
│   │   ├── referenceEngine.ts  # Grafo em memória O(1) de referências de diagramas
│   │   ├── diagramScanner.ts   # Varredura recursiva de arquivos .executionflow e .stateflow
│   │   └── graphConverter.ts   # Conversor de modelo XML para nós/arestas com DAG Auto-Layout
│   ├── types/                  # Definições TypeScript dos metamodelos EMF
│   └── App.tsx                 # Workbench principal (Topbar, Explorador, Canvas, Propriedades)
├── samples/                    # Diagramas reais de exemplo (.executionflow e .stateflow)
├── site_suporte/               # Portal de documentação viva e central de ajuda com GIFs
│   ├── assets/gifs/            # GIFs fluidos gerados automaticamente pelos testes E2E
│   └── index.html              # Interface do portal de suporte
├── scripts/                    # Scripts da esteira de documentação e GIFs (100% local)
│   ├── record-doc-flows.js     # Gravação determinística dos fluxos felizes via Playwright
│   ├── compile-doc-gifs.py     # Compilação dos frames em GIFs com Python Pillow (256 cores adaptativas)
│   └── generate-doc-site.js    # Verificador e integrador do portal de suporte
└── tests/                      # Bateria de testes unitários automatizados (Node Test Runner)
```

---

## 📦 Pré-requisitos

- **Node.js**: Versão 18 ou superior (recomendado Node.js 20+ ou 24).
- **Python**: Versão 3.10 ou superior com a biblioteca **Pillow** instalada (utilizada para a geração determinística dos GIFs da documentação):
  ```bash
  pip install Pillow
  ```
- **Navegador**: Qualquer navegador Chromium (o Microsoft Edge nativo do Windows já é detectado e utilizado automaticamente pelo Playwright sem necessidade de downloads adicionais).

---

## 🚀 Guia de Comandos

### 1. Instalação das Dependências
```bash
npm install
```

### 2. Executar a Aplicação Desktop (Modo Desenvolvimento)
Inicia o Vite em conjunto com a janela do Electron:
```bash
npm run dev:electron
```

### 3. Executar apenas no Navegador (Web Preview)
Inicia o servidor de desenvolvimento web em `http://localhost:5173`:
```bash
npm run dev
```

### 4. Executar os Testes Automatizados (100% de Aprovação)
Executa a suíte de testes unitários nativa do Node.js, cobrindo parsing XML, serialização, scanner Java, referências cruzadas e conversão de grafos:
```bash
npm test
```

### 5. Gerar a Documentação Viva com GIFs Animados
Executa o teste dos fluxos felizes com Playwright, captura os frames, compila em GIFs fluidos de alta definição com Python Pillow e atualiza o portal de ajuda:
```bash
npm run doc:site
```
Para visualizar a central de ajuda gerada, basta abrir o arquivo [`site_suporte/index.html`](site_suporte/index.html) no navegador ou clicar no botão **"Ajuda"** no cabeçalho da aplicação.

### 6. Compilação de Produção
Compila o código TypeScript, gera o bundle otimizado do React com Vite e compila os scripts do processo principal do Electron:
```bash
npm run build
```

### 7. Gerar Instalador Executável Windows (`.exe`)
Empacota a aplicação gerando o executável standalone portátil para distribuição:
```bash
npm run package
```

---

## 🎞️ Central de Documentação Viva & GIFs

O projeto adota uma arquitetura de **documentação viva 100% determinística** inspirada na esteira do `FlowBusiness`. Nenhuma IA externa é necessária em tempo de execução:
- O script [`scripts/record-doc-flows.js`](scripts/record-doc-flows.js) abre a aplicação através do Playwright, exercita os fluxos felizes e salva os frames em `scratch/frames/`.
- O script [`scripts/compile-doc-gifs.py`](scripts/compile-doc-gifs.py) redimensiona os frames para 1024x576, aplica quantização adaptativa de 256 cores via Pillow e gera GIFs nítidos em `site_suporte/assets/gifs/`.
- O portal [`site_suporte/index.html`](site_suporte/index.html) embute as demonstrações animadas e documenta todos os atalhos de produtividade:
  - `abrir-diagrama.gif`: Seleção de workspace e navegação na lista de diagramas.
  - `busca-referencias.gif`: Busca cruzada de classes Java `MIC*` e exibição da gaveta de referências.
  - `modelar-fluxo.gif`: Interação no canvas, menu de contexto e inspetor de propriedades.
  - `salvar-xml.gif`: Persistência e garantia de compatibilidade com o XML legado.

---

## 💾 Compatibilidade XML com P2K

Os diagramas gerados pelo **CSI StateFlow Studio** são estritamente compatíveis com as tags e atributos esperados pela máquina de estados do P2K:

### Exemplo de `.executionflow` Gerado:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<com.csi.gmf.executionflow:ExecutionFlow xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:com.csi.gmf.executionflow="http:///com/csi/gmf/executionflow.ecore" id="7026" title="7026_CONFIRMACAO_AJUSTES" description="Confirmacao de Ajustes de Venda" requestAuthorization="false">
  <startPoint/>
  <startPointConnection destinationObjectPathKey="1386704906523"/>
  <executableFlowObjects pathKey="1386704906523" className="com.csi.oper.pos.MicSalvaDisplay" simpleClassName="MicSalvaDisplay"/>
  <executableFlowObjects pathKey="1386704906524" className="com.csi.oper.pos.MicConfirmaAjustes" simpleClassName="MicConfirmaAjustes"/>
  <results pathKey="1386704906530" executionFlowResultValue="FINALIZA_AJUSTES"/>
  <flowConnections sourcePathKey="1386704906523" destinationPathKey="1386704906524" executionFlowResultValue="DEFAULT"/>
  <flowConnections sourcePathKey="1386704906524" destinationPathKey="1386704906530" executionFlowResultValue="DEFAULT"/>
</com.csi.gmf.executionflow:ExecutionFlow>
```

- **PathKey Preservado**: Todo nó possui um identificador numérico único (timestamp epoch de 13 dígitos) respeitado na amarração das conexões de fluxo.
- **Isolamento de Metadados Visuais**: Posições visuais personalizadas e anotações são salvas de forma espelhada sem corromper a estrutura semântica necessária para o backend Java do P2K.

---

## 🧪 Testes Automatizados

A suíte de testes unitários é executada com `npm test`:

```text
✔ DiagramScanner - Varredura e Listagem de TODOS os Diagramas no Workspace
✔ GraphConverter - Conversão de ExecutionFlow para Nós e Arestas do React Flow
✔ GraphConverter - Conversão de StateFlow para Nós e Arestas do React Flow
✔ Sanity check: Node test runner is operational
✔ Validacao de geracao do pathKey (timestamp epoch ms de 13 digitos)
✔ JavaScanner - Varredura real em diretório de fontes (146 classes)
✔ ReferenceEngine - Indexação e Busca Cruzada de Diagramas
✔ XmlEngine - Parsing & Serialization de ExecutionFlow (7026_CONFIRMACAO_AJUSTES)
✔ XmlEngine - Parsing & Serialization de StateFlow
ℹ tests 9 | pass 9 | fail 0 (100% de sucesso)
```

---

## ⌨️ Teclas de Atalho

| Atalho | Ação |
|---|---|
| `Ctrl + S` | Salvar o diagrama ativo no formato XML legado |
| `Ctrl + F` | Buscar nós por nome dentro do diagrama aberto |
| `Ctrl + Shift + F` | Abrir busca global de referências de classes |
| `Espaço + Arrastar` | Pan fluído pelo canvas infinito |
| `Scroll do Mouse` | Zoom in / Zoom out centrado na posição do cursor |
| `Botão Direito no Nó` | Menu de contexto (Add Note, Search References, Show Properties, Delete) |
| `Botão Direito na Classe` | Disparar busca cruzada de referências da classe nos diagramas |

---

## 📄 Licença
Desenvolvido para modernização e sustentação do ecossistema CSI / P2K.
