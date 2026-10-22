"""
Compilador de GIFs Fluidos para a Central de Ajuda & Suporte Viva
Lê os frames capturados pelo teste E2E do Playwright e os converte em GIFs animados de alta definição
100% determinístico, reproduzindo a mesma esteira do FlowBusiness
"""
import os
import sys
import glob
from PIL import Image

# Força codificação UTF-8 na saída
if sys.platform == "win32":
    import io
    sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')

FRAMES_BASE = "scratch/frames"
OUTPUT_BASE = "site_suporte/assets/gifs"

modules = [
    "abrir-diagrama",
    "busca-referencias",
    "modelar-fluxo",
    "salvar-xml",
]

os.makedirs(OUTPUT_BASE, exist_ok=True)

TARGET_WIDTH = 1024
TARGET_HEIGHT = 576

print("========================================================")
print("[DOC-GIFS] Compilando frames em GIFs animados para o site_suporte")
print("========================================================")

for mod in modules:
    mod_dir = os.path.join(FRAMES_BASE, mod)
    if not os.path.exists(mod_dir):
        print(f"Pular {mod}: pasta {mod_dir} nao encontrada")
        continue

    frame_files = sorted(glob.glob(os.path.join(mod_dir, "frame_*.png")))
    if not frame_files:
        print(f"Pular {mod}: nenhum frame encontrado")
        continue

    images = []
    for f in frame_files:
        img = Image.open(f).convert("RGB")
        # Redimensionamento suave para 1024x576
        img_resized = img.resize((TARGET_WIDTH, TARGET_HEIGHT), Image.Resampling.LANCZOS)
        # Quantizacao adaptativa de 256 cores para GIF limpo e nitido
        img_quantized = img_resized.convert("P", palette=Image.Palette.ADAPTIVE, colors=256)
        images.append(img_quantized)

    output_path = os.path.join(OUTPUT_BASE, f"{mod}.gif")
    # Salva GIF animado com duracao de 750ms por quadro e loop infinito
    images[0].save(
        output_path,
        save_all=True,
        append_images=images[1:],
        duration=750,
        loop=0,
        optimize=True,
    )
    size_kb = os.path.getsize(output_path) / 1024
    print(f" [OK] GIF gerado: {output_path} ({len(images)} frames, {size_kb:.1f} KB)")

print("\n[SUCESSO] Compilacao de GIFs da Documentacao concluida!")
