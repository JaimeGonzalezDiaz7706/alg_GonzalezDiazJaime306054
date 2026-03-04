package p3p;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuntosTrivial{

    public PuntosTrivial(){}

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

    public static void distanciaMinima(double[] lista){
        double minX1 = 0, minX2 = 0, minY1 = 0, minY2 = 0;
        double dist = 0;
        double min = Double.POSITIVE_INFINITY;
        for(int i=0; i<lista.length; i+=2){
            for(int j=i+2; j<lista.length;j+=2){
                dist = Math.sqrt((lista[j]-lista[i])*(lista[j]-lista[i]) + (lista[j+1]-lista[i+1])*(lista[j+1]-lista[i+1]));
                if(dist<min){
                    min = dist;
                    minX1 = lista[i];
                    minY1 = lista[i+1];
                    minX2 = lista[j];
                    minY2 = lista[j+1];
                }
            }
        }

        System.out.println("PUNTOS MÁS CERCANOS: ["+minX1+", "+minY1+"] ["+
        minX2+", "+minY2+"]");
        System.out.printf("SU DISTANCIA MINIMA="+"%.6f",min);
    }

    public static void main(String args[]){
        distanciaMinima(parseText(args[0]));
    }
}
