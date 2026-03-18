import json

from auxiliar import dibujar_mapa_coloreado, generar_mapa_grafo

colores = ["red", "blue", "green", "yellow", "orange", "purple"]

def realizar_voraz(grafo):
    resultado = {}
 
    for i in range(len(grafo)):
        id = str(i)
        vecinos = grafo[id]
        
        colores_prohibidos = set()
        for vecino in vecinos:
            id_vecino = str(vecino)
            if id_vecino in resultado:
                colores_prohibidos.add(resultado[id_vecino])
 
        for color in colores:
            if color not in colores_prohibidos:
                resultado[id] = color
                break
 
    return resultado
    
    
if __name__ == "__main__":
    n = 4
    mapa = generar_mapa_grafo(n)
    solucion = realizar_voraz(mapa["grafo"])

    if solucion:
        print("Solución encontrada:", solucion)
        dibujar_mapa_coloreado(mapa, solucion)
        with open('sols/solucion.json', 'w') as f:
            json.dump(solucion, f)
            f.close()
    else:
        print("No se encontró solución.")


