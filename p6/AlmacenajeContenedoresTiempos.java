import java.io.IOException;

public class AlmacenajeContenedoresTiempos {
    public static void main(String[] args) throws NumberFormatException, IOException {
        long t1, t2;
        int repeticiones = (args.length > 0) ? Integer.parseInt(args[0]) : 1;
        
        String[] ficheros = {
            "CasosPrueba/test00.txt",
            "CasosPrueba/test01.txt",
            "CasosPrueba/test02.txt",
            "CasosPrueba/test03.txt",
            "CasosPrueba/test04.txt",
            "CasosPrueba/test05.txt",
            "CasosPrueba/test06.txt",
            "CasosPrueba/test07.txt",
            "CasosPrueba/test08.txt",
            "CasosPrueba/test09.txt"
        };
        
        for(String fichero : ficheros){
            long tiempo=0L;
            for(int i=0;i<repeticiones;i++){
                t1=System.currentTimeMillis();
                AlmacenajeContenedores.main(fichero, false);
                t2=System.currentTimeMillis();
                tiempo += t2-t1;
            }
           
            System.out.println("fichero=" + fichero + "\tTiempo=" + tiempo + "\tRepeticiones=" + repeticiones);
        }
    }
}
