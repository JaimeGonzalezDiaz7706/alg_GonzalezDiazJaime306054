import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AlmacenajeContenedores {
    static int capacidadMax;
    static int[] tamanos;
    static int mejorNum;
    static int[] asignacion;
    static int[] mejorAsignacion;
    static int[] contenedoresActuales;

    public static void resolver(int indice, int numContenedoresUsados) {
        if (numContenedoresUsados >= mejorNum) {
            return;
        }
        
        if (indice == tamanos.length) {
            mejorNum = numContenedoresUsados;
            System.arraycopy(asignacion, 0, mejorAsignacion, 0, tamanos.length);
            return;
        }

        for (int i = 0; i < numContenedoresUsados; i++) {
            if (contenedoresActuales[i] + tamanos[indice] <= capacidadMax) {
                contenedoresActuales[i] += tamanos[indice];
                asignacion[indice] = i;
                resolver(indice + 1, numContenedoresUsados);
                contenedoresActuales[i] -= tamanos[indice];
            }
        }

        if (numContenedoresUsados + 1 < mejorNum) {
            contenedoresActuales[numContenedoresUsados] = tamanos[indice];
            asignacion[indice] = numContenedoresUsados;
            resolver(indice + 1, numContenedoresUsados + 1);
            contenedoresActuales[numContenedoresUsados] = 0;
        }
    }

    public static void main(String args[]) throws NumberFormatException, IOException {
        main(args[0], true); 
    }

    public static void main(String fichero, boolean imprimir) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(fichero));
        capacidadMax = Integer.parseInt(br.readLine().trim());
        String[] tokens = br.readLine().trim().split("\\s+");
        br.close();

        int n = tokens.length;
        tamanos = new int[n];
        for (int i = 0; i < n; i++) {
            tamanos[i] = Integer.parseInt(tokens[i]);
        }

        mejorNum = n;
        contenedoresActuales = new int[n];
        asignacion = new int[n];
        mejorAsignacion = new int[n];

        resolver(0, 0);

        if (imprimir) {
            mostrarResultados();
        }
    }

    private static void mostrarResultados() {
        System.out.println("Lista de contenedores y objetos contenidos:");
        for (int i = 0; i < mejorNum; i++) {
            System.out.print("Contenedor " + (i + 1) + ": ");
            for (int j = 0; j < tamanos.length; j++)
                if (mejorAsignacion[j] == i)
                    System.out.print(tamanos[j] + " ");
            System.out.println();
        }
        System.out.println("El número de contenedores necesario es " + mejorNum + ".");
    }
}