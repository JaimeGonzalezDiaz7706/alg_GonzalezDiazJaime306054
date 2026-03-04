package p3p;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class PuntosTrivialTiempos {

    public static Random r = new Random();
    public PuntosTrivialTiempos(){}

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
        double dist = 0;
        double min = Double.POSITIVE_INFINITY;
        for(int i=0; i<lista.length; i+=2){
            for(int j=i+2; j<lista.length;j+=2){
                dist = Math.sqrt((lista[j]-lista[i])*(lista[j]-lista[i]) + (lista[j+1]-lista[i+1])*(lista[j+1]-lista[i+1]));
                if(dist<min){
                    min = dist;
                }
            }
        }

    }

    private static double[] randomList(int n) {
        double[] list = new double[n*2];
        for(int i=0; i<2*n;i++){
            list[i]=r.nextDouble();
        }

        return list;
    }

    public static void main(String args[])
    {
        long t1, t2;

        int nVeces= Integer.parseInt (args[0]);

	    for (int n=1024;n<=120000;n*=2)
	    {
            double[] list = randomList(n);

		    t1 = System.currentTimeMillis ();

            for (int rep = 0; rep < nVeces; rep++) {
                distanciaMinima(list);
            }

            t2 = System.currentTimeMillis ();

            System.out.println (" n="+n+ "**TIEMPO="+(t2-t1)+"**nVeces="+nVeces);
	    } 
	} 

}



