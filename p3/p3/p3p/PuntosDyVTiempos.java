package p3p;

import java.util.Random;

public class PuntosDyVTiempos {

    public static Random r = new Random();

    public PuntosDyVTiempos() {}

    private static double distancia(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }


    public static void quicksort(double[] list, int iz, int de) {
        int m;
        if (de > iz) {
            m = particion(list, iz, de);
            quicksort(list, iz, m - 2);
            quicksort(list, m + 2, de);
        }
    }

    private static int particion(double[] list, int iz, int de) {
        int i;
        double piv;
        int medio = (iz + de) / 2;
        if (medio % 2 != 0) medio--;

        intercambiar(list, medio, iz);
        piv = list[iz];
        i = iz;

        for (int s = iz + 2; s <= de; s += 2) {
            if (list[s] <= piv) {
                i += 2;
                intercambiar(list, i, s);
            }
        }
        intercambiar(list, iz, i);
        return i;
    }

    private static void intercambiar(double[] list, int a, int b) {
        double temp = list[a];
        list[a] = list[b];
        list[b] = temp;

        temp = list[a + 1];
        list[a + 1] = list[b + 1];
        list[b + 1] = temp;
    }

    // --- ALGORITMO DIVIDE Y VENCERÁS (VERSION SILENCIOSA PARA TIEMPOS) ---

    public static double[] distanciaMinimaDyV(double[] lista, int iz, int de) {
        if ((de - iz) <= 4) {
            double min = Double.POSITIVE_INFINITY;
            double x1 = 0, y1 = 0, x2 = 0, y2 = 0;
            for (int i = iz; i <= de; i += 2) {
                for (int j = i + 2; j <= de; j += 2) {
                    double d = distancia(lista[i], lista[i + 1], lista[j], lista[j + 1]);
                    if (d < min) {
                        min = d;
                        x1 = lista[i]; y1 = lista[i + 1];
                        x2 = lista[j]; y2 = lista[j + 1];
                    }
                }
            }
            return new double[]{min, x1, y1, x2, y2};
        }

        int m = iz + ((de - iz) / 4) * 2;
        double valorMedioX = lista[m];

        double[] rI = distanciaMinimaDyV(lista, iz, m);
        double[] rD = distanciaMinimaDyV(lista, m + 2, de);
        
        double[] mejor = (rI[0] < rD[0]) ? rI : rD;
        double d = mejor[0];

        for (int i = iz; i <= de; i += 2) {
            if (Math.abs(lista[i] - valorMedioX) < d) {
                for (int j = i + 2; j <= de && (lista[j] - lista[i]) < d; j += 2) {
                    double dist = distancia(lista[i], lista[i + 1], lista[j], lista[j + 1]);
                    if (dist < d) {
                        d = dist;
                        mejor = new double[]{d, lista[i], lista[i + 1], lista[j], lista[j + 1]};
                    }
                }
            }
        }
        return mejor;
    }

    private static double[] randomList(int n) {
        double[] list = new double[n * 2];
        for (int i = 0; i < 2 * n; i++) {
            list[i] = r.nextDouble();
        }
        return list;
    }

    public static void main(String args[]) {
        long t1, t2;
        int nVeces = Integer.parseInt(args[0]);

        for (int n = 1024; n <= 1048576; n *= 2) {
            double[] list = randomList(n);
            
            quicksort(list, 0, list.length - 2);

            t1 = System.currentTimeMillis();

            for (int rep = 0; rep < nVeces; rep++) {
                distanciaMinimaDyV(list, 0, list.length - 2);
            }

            t2 = System.currentTimeMillis();

            System.out.println(" n=" + n + "**TIEMPO=" + (t2 - t1) + "**nVeces=" + nVeces);
        }
    }
}