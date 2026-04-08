import java.io.IOException;

public class NReinasTiempos_UO306054 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        long t1, t2;
        NReinas_UO306054 reinas = new NReinas_UO306054();
        
            long tiempo=0L;
            for(int i=2;i<30;i++){
                t1=System.currentTimeMillis();
                reinas.resolverNReinas(i);
                t2=System.currentTimeMillis();
                tiempo += t2-t1;

                System.out.println("Tamaño=" + i + "\tTiempo=" + tiempo);
            }
        }
    }

