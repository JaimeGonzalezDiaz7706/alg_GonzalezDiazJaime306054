import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        LaberintoTodas laberinto = new LaberintoTodas();

        if (args == null || args.length == 0) {
            Path fichero = Path.of("caso1.txt");
            int[][] tablero = leerTablero(fichero);
            System.out.println("== " + fichero + " ==");
            imprimirSoluciones(laberinto.resolverTableros(tablero));
            return;
        }

        for (String arg : args) {
            if (arg == null || arg.isBlank()) continue;
            Path fichero = Path.of(arg);
            int[][] tablero = leerTablero(fichero);
            System.out.println("== " + fichero + " ==");
            imprimirSoluciones(laberinto.resolverTableros(tablero));
        }
    }

    private static void imprimirSoluciones(List<List<String>> soluciones) {
        if (soluciones == null || soluciones.isEmpty()) {
            System.out.println("(sin soluciones)");
            System.out.println();
            return;
        }

        for (int i = 0; i < soluciones.size(); i++) {
            System.out.println("-- solucion " + (i + 1) + " --");
            for (String fila : soluciones.get(i)) {
                System.out.println(fila);
            }
            System.out.println();
        }
    }

    private static int[][] leerTablero(Path fichero) throws IOException {
        List<String> lineas = Files.readAllLines(fichero);
        List<int[]> filas = new ArrayList<>();

        for (String linea : lineas) {
            if (linea == null) continue;
            String recortada = linea.trim();
            if (recortada.isEmpty()) continue;

            String[] partes = recortada.split("\\s+");
            int[] fila = new int[partes.length];
            for (int i = 0; i < partes.length; i++) {
                fila[i] = Integer.parseInt(partes[i]);
            }
            filas.add(fila);
        }

        if (filas.isEmpty()) {
            throw new IllegalArgumentException("El fichero no contiene un tablero válido: " + fichero);
        }

        int n = filas.size();
        int m = filas.get(0).length;
        for (int i = 0; i < n; i++) {
            if (filas.get(i).length != m) {
                throw new IllegalArgumentException("Tablero no rectangular en la fila " + (i + 1) + ": " + fichero);
            }
        }
        if (n != m) {
            throw new IllegalArgumentException("Se esperaba un tablero cuadrado NxN, pero es " + n + "x" + m + ": " + fichero);
        }

        int[][] tablero = new int[n][n];
        for (int i = 0; i < n; i++) {
            tablero[i] = filas.get(i);
        }
        return tablero;
    }
}