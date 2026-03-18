
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ColoreoGrafo {
    private static String[] colores= {"red", "blue", "green", "yellow", "orange", "purple"/*, "cyan", "magenta", "lime" */};

    public static Map<String, String> realizarVoraz(Map<String, List<String>> grafo){

        Map<String, String> resultado = new HashMap<>();

        for(int i=0; i<grafo.size(); i++){

            //Definimos el id de cada nodo del grafo, la lista de vecinos,
            //creamos un nuevo nodo y creamos la lista de adyacentes 
            String id = String.valueOf(i);
            List<String> vecinos = grafo.get(id);
            Nodo actual = new Nodo(id, vecinos);
            Set<String> coloresProhibidos = new HashSet<>();

            List<String> adyacentes = actual.getAdyacentes();

            //Miramos que colores ya han sido usados por los vecinos
            for(int j=0; j<adyacentes.size();j++){
                String idVecino = String.valueOf(adyacentes.get(j));
                if(resultado.containsKey(idVecino)){
                    coloresProhibidos.add(resultado.get(idVecino));
                }
            }
            
            //Cuando encontremos uno que no esté usado, lo usamos
            for(int k=0; k<colores.length;k++){
                if(!coloresProhibidos.contains(colores[k])){
                    resultado.put(id,colores[k]);
                    break;
                }
            }

        }

        return resultado;
    }

    /*
     * Clase auxiliar que me sirve para organizarme mejor
     */
    public static class Nodo{
        String id;
        List<String> adyacentes;

        public Nodo(String id, List<String> adyacentes){
            this.id=id;
            this.adyacentes=adyacentes;
        }


        public List<String> getAdyacentes(){
            return adyacentes;
        }
    }

}
