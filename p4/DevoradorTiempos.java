import java.io.FileReader;
import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DevoradorTiempos {

    
    public static void main(String[] args) {
        int repeticiones = (args.length > 0) ? Integer.parseInt(args[0]) : 1;

        int[] tamanos = {8, 16, 32, 64, 100, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536};
        JSONParser parser = new JSONParser();

        for (int n : tamanos) {
            String nombreArchivo = "sols/g" + n + ".json";

            try (FileReader reader = new FileReader(nombreArchivo)) {
                JSONObject jsonObject = (JSONObject) parser.parse(reader);
                @SuppressWarnings("unchecked")
                Map<String, List<String>> grafo = (Map<String, List<String>>) jsonObject.get("grafo");

                long t = 0L;

                for (int i = 1; i <= repeticiones; i++) {
                    long t1 = System.currentTimeMillis();
                    ColoreoGrafo.realizarVoraz(grafo);
                    long t2 = System.currentTimeMillis();
                    t += (t2 - t1);
                }

                System.out.println("n=" + n + "\tTiempo=" + t + "  \tRepeticiones=" + repeticiones);

            } catch (Exception e) {
                System.out.println("n=" + n + "\tError: " + e.getMessage());
            }
        }
    }
}