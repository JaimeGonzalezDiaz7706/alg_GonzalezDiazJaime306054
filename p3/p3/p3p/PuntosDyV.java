package p3p;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuntosDyV{

    public PuntosDyV(){}

    public static double[] parseText(String fichero){
        double[] datos = null;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            int indice = 0;
            int numeroPuntos= Integer.parseInt(br.readLine().trim());

            datos = new double[numeroPuntos*2];

            while((linea=br.readLine())!=null && indice < datos.length){
                linea = linea.trim();

                String[] tokens = linea.split(",");
                datos[indice++] = Double.parseDouble(tokens[0].trim());
                datos[indice++] = Double.parseDouble(tokens[1].trim());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return datos;
    }

    public static double distancia(double x1, double y1, double x2, double y2){
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static void quicksort(double[] list, int iz, int de){
        int m;
        if(de>iz){
            m = particion(list, iz, de);
            quicksort(list, iz, m-2);
            quicksort(list, m+2, de);
        }

    }
    private static int particion(double[] list, int iz, int de) {
        int i;
        double piv;

        int medio = (iz+de)/2;
        if(medio%2 !=0){
            medio--;
        }

        intercambiar(list, medio, iz);
        piv = list[iz];
        i = iz;

        for(int s = iz+2; s <= de; s+=2){
            if(list[s] <= piv){
                i+=2;
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

        temp = list[a+1];
        list[a+1]=list[b+1];
        list[b+1]=temp;
    }

    public static double[] distanciaMinima(double[] lista, int iz, int de){
        if ((de - iz) <= 4) {
            double min = Double.POSITIVE_INFINITY;
            double x1 = 0, y1 = 0, x2 = 0, y2 = 0;
            for (int i = iz; i <= de; i += 2) {
                for (int j = i + 2; j <= de; j += 2) {
                    double d = distancia(lista[i], lista[i+1], lista[j], lista[j+1]);
                    if (d < min) {
                        min = d;
                        x1 = lista[i]; 
                        y1 = lista[i+1];
                        x2 = lista[j]; 
                        y2 = lista[j+1];
                    }
                }
            }
            return new double[]{min, x1, y1, x2, y2};
        }
        int m = iz + ((de - iz) / 4) * 2; 
        double medio = lista[m];

        double[] rI = distanciaMinima(lista, iz, m);
        double[] rD = distanciaMinima(lista, m + 2, de);
        double[] min;
        
        if(rI[0]<rD[0]){
            min = rI;
        } else{
            min = rD;
        }

        double d = min[0];

        for (int i = iz; i <= de; i += 2) {
            if (Math.abs(lista[i] - medio) < d) {
                for (int j = i + 2; j <= de && (lista[j] - lista[i]) < d; j += 2) {
                    double dist = distancia(lista[i], lista[i+1], lista[j], lista[j+1]);
                    if (dist < d) {
                        d = dist;
                        min = new double[]{d, lista[i], lista[i+1], lista[j], lista[j+1]};
                    }
                }
            }
        }
        return min;

    }

    public static void main(String args[]){
        double[] lista = parseText(args[0]);

        quicksort(lista, 0, lista.length-2);
        
        double[] res = distanciaMinima(lista, 0, lista.length-2);

        System.out.println("PUNTOS MÁS CERCANOS: [" + res[1] + ", " + res[2] + "] [" + res[3] + ", " + res[4] + "]");
        System.out.printf("SU DISTANCIA MINIMA=%.6f\n", res[0]);
    }
}

