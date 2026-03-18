import json
import time
import sys

from coloreado_grafo import realizar_voraz

tamanos = [8, 16, 32, 64, 100, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536]

if __name__ == "__main__":
    repeticiones = int(sys.argv[1]) if len(sys.argv) > 1 else 1

    for n in tamanos:
        nombre_archivo = f"sols/g{n}.json"

        try:
            with open(nombre_archivo) as f:
                mapa = json.load(f)

            grafo = mapa["grafo"]
            t = 0

            for _ in range(repeticiones):
                t1 = time.time()
                realizar_voraz(grafo)
                t2 = time.time()
                t += (t2 - t1) * 1000  

            print(f"n={n}\tTiempo={t:.3f}  \tRepeticiones={repeticiones}")

        except Exception as e:
            print(f"n={n}\tError: {e}")