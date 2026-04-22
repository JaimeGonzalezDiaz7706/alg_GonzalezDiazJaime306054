import java.util.ArrayList;
import java.util.List;

public class LaberintoTodas {
    private List<List<String>> soluciones;

    int[] dx = {-1,1,0,0};
    int[] dy= {0,0,-1,1};

    public List<List<String>> resolverTableros(int[][] tablero){
        soluciones = new ArrayList<>();
        
        resolver(tablero, 0, 0);

        return soluciones;

    }
    /**
     * Camino disponible:0
     * Camino bloqueado: 1
     * Camino recorrido por el animal: 2
     * 
     * Al imprimir, 0="." 1="H" 2="*"
     */
    private void resolver(int[][] tablero, int fila, int col){
        if(!isValid(tablero,fila,col)) return; //Validación de posición
        
        if (fila == 6 && col == 6) { //Caso base
            tablero[fila][col] = 2;
            soluciones.add(printSolucion(tablero));
            tablero[fila][col] = 0;
            return;
        }

        tablero[fila][col] = 2; //Casilla marcada
        for(int i=0;i<4;i++){
            resolver(tablero,fila+dx[i],col+dy[i]); //Recursividad
        }
        tablero[fila][col] = 0; //Desmarcada
    }


    private List<String> printSolucion(int[][] tablero) {
        List<String> solucion = new ArrayList<>();
        char[][] solucionMatriz = new char[7][7];
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                if(tablero[i][j]==0) solucionMatriz[i][j]='.';
                else if(tablero[i][j]==1) solucionMatriz[i][j]='H';
                else solucionMatriz[i][j]='*';
            }
        }

        for(char[] fila : solucionMatriz){
            solucion.add(new String(fila));
        }

        return solucion;
    }

    private boolean isValid(int[][] tablero, int fila, int col){
        if (fila < 0 || fila >= 7 || col < 0 || col >= 7 
        || tablero[fila][col] == 1 || tablero[fila][col] == 2) return false;
        else return true;
    }
}
