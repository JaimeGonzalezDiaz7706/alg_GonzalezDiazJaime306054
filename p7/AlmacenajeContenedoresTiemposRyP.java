import java.io.IOException;

public class AlmacenajeContenedoresTiemposRyP {
    public static void main(String[] args) throws NumberFormatException, IOException {
        long t1, t2;
        int repeticiones = (args.length > 0) ? Integer.parseInt(args[0]) : 1;
        
        String[] ficheros = {
            "test00.txt",
            "test01.txt",
            "test02.txt",
            "test03.txt",
            "test04.txt",
            "test05.txt",
            "test06.txt",
            "test07.txt",
            "test08.txt",
            "test09.txt"
        };
        
        for(String fichero : ficheros){
            long tiempo=0L;
            for(int i=0;i<repeticiones;i++){
                t1=System.currentTimeMillis();
                AlmacenajeContenedoresRyP.main(fichero, false);
                t2=System.currentTimeMillis();
                tiempo += t2-t1;
            }
           
            System.out.println("fichero=" + fichero + "\tTiempo=" + tiempo + "\tRepeticiones=" + repeticiones);
        }
    }
}
