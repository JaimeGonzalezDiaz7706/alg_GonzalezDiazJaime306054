package p5;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ferry {

    private List<Step> path; // variable para guardar el camino seleccionado

    /**
     * Un ferry tiene dos carriles paralelos (babor y estribor)
     * Vehiculos llegan en cola ORDENADA
     * Los vehiculos deben cargarse en orden de llegada
     * Al considerar el vehiculo, este debe colocarse completamente
     * en el carril de babor o en el de estribor
     *
     * Si el vehiculo i no cabe en ninguno de los dos carriles
     * el proceso de carga termina, no se puede saltar al siguiente.
     *
     * ESTRUCTURA 0/1
     *
     * NUMERO MAXIMO DE VEHICULOS K sin superar L en ningun carril
     */

    private int l;                      // longitud de los carriles del barco
    private List<Integer> vehiculos;    // lista de vehiculos
    private boolean[][] dp;             // dp[i][j]: posible cargar i vehiculos con j metros en babor
    private int[] sumaLongVehiculos;    // suma acumulada de las longitudes de los vehiculos

    public Ferry(int l, List<Integer> vehiculos) {
        this.l = l;
        this.vehiculos = vehiculos;
        this.dp = new boolean[vehiculos.size() + 1][l + 1];
        this.sumaLongVehiculos = new int[vehiculos.size() + 1];

        this.sumaLongVehiculos[0] = 0;
        for (int i = 1; i < sumaLongVehiculos.length; i++) {
            this.sumaLongVehiculos[i] = sumaLongVehiculos[i - 1] + vehiculos.get(i - 1);
        }

        this.path = new ArrayList<Step>();
    }

    public void parserTxt(String fichero) {
        vehiculos = new ArrayList<Integer>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fichero));
            this.l = Integer.valueOf(br.readLine());
            for (String s : br.readLine().split(" ")) {
                this.vehiculos.add(Integer.valueOf(s));
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {

        dp[0][0] = true;

        for (int i = 1; i <= vehiculos.size(); i++) {
            int contadorTrue = 0;
            for (int p = 0; p <= l; p++) {

                if (dp[i - 1][p]) {

                    // BABOR
                    if (p + vehiculos.get(i - 1) <= l) {
                        dp[i][p + vehiculos.get(i - 1)] = true;
                        contadorTrue++;
                    }
                    // ESTRIBOR
                    if (sumaLongVehiculos[i] - p <= l) {
                        dp[i][p] = true;
                        contadorTrue++;
                    }
                }
            }
            // CONDICION DE PARADA
            if (contadorTrue == 0) {
                break;
            }
        }
    }

    public int getMaximumNumberOfVehicles() {
        for (int i = vehiculos.size(); i >= 0; i--) {
            for (int j = 0; j <= l; j++) {
                if (dp[i][j] && (sumaLongVehiculos[i] - j) <= l) {
                    return i;
                }
            }
        }
        return 0;
    }

    public void printSolutionTable() {
        System.out.printf("\nTable with calculations:\n");

        System.out.printf("%4s", "V/L");
        for (int j = 0; j <= l; j++) {
            System.out.printf("%4d", j);
        }
        System.out.printf("\n");

        for (int i = 0; i <= vehiculos.size(); i++) {
            System.out.printf("%4d", i);
            for (int j = 0; j <= l; j++) {
                System.out.printf("%4s", dp[i][j] ? "T" : "F");
            }
            System.out.printf("\n");
        }
    }

    public void printPossibleAssignation() {
        path.clear();
        System.out.printf("\nPossible assignation:\n");

        int k = getMaximumNumberOfVehicles();
        if (k == 0) {
            System.out.println("No vehicles can be loaded.");
            return;
        }

        int startJ = -1;
        for (int j = 0; j <= l; j++) {
            if (dp[k][j] && (sumaLongVehiculos[k] - j) <= l) {
                startJ = j;
                break;
            }
        }

        if (startJ == -1) {
            System.out.println("No valid assignation found.");
            return;
        }

        processAssignation(k, startJ);
    }

    
    private void processAssignation(int i, int j) {
        if (i == 0 && j == 0) {
            printPath();
            return;
        }
        
        if (j >= 0 && j <= l && dp[i - 1][j]) {
            path.addFirst(new Step(i - 1, j, i, j, i, "estribor"));
            processAssignation(i - 1, j);
            return;
        }

        int prevJ = j - vehiculos.get(i - 1);
        if (prevJ >= 0 && dp[i - 1][prevJ]) {
            path.addFirst(new Step(i - 1, prevJ, i, j, i, "babor"));
            processAssignation(i - 1, prevJ);
            return;
        }
    }

    private void printPath() {
        int portLength = 0;
        int starboardLength = 0;
        for (var step : path) {
            if (step.movement().equals("babor")) {
                portLength += vehiculos.get(step.vehicle() - 1);
                System.out.printf("Vehiculo %d (longitud %d) a babor.\n",
                        step.vehicle(), vehiculos.get(step.vehicle() - 1));
            } else {
                starboardLength += vehiculos.get(step.vehicle() - 1);
                System.out.printf("Vehiculo %d (longitud %d) a estribor.\n",
                        step.vehicle(), vehiculos.get(step.vehicle() - 1));
            }
        }
        System.out.printf("Ocupacion final: Babor %dm / Estribor %dm (valido <= %d).\n",
                portLength, starboardLength, l);
    }

    public void printData() {
        System.out.printf("Length of parallel lanes for starboard and port on the ferry: %d\n", l);
        System.out.printf("The vehicles have the following lengths:\n");
        for (int i = 0; i < vehiculos.size(); i++) {
            System.out.printf("\tVehicle %d: %d\n", i + 1, vehiculos.get(i));
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Ferry <input_file>");
            return;
        }
        Ferry ferry = new Ferry(0, new ArrayList<>());
        ferry.parserTxt(args[0]);

        ferry.dp = new boolean[ferry.vehiculos.size() + 1][ferry.l + 1];
        ferry.sumaLongVehiculos = new int[ferry.vehiculos.size() + 1];
        ferry.sumaLongVehiculos[0] = 0;
        for (int i = 1; i < ferry.sumaLongVehiculos.length; i++) {
            ferry.sumaLongVehiculos[i] = ferry.sumaLongVehiculos[i - 1] + ferry.vehiculos.get(i - 1);
        }
        ferry.path = new ArrayList<>();

        ferry.printData();
        ferry.run();
        ferry.printSolutionTable();
        int k = ferry.getMaximumNumberOfVehicles();
        System.out.printf("\nHan llegado un total de %d vehiculos (%d viajaran).\n",
                ferry.vehiculos.size(), k);
        ferry.printPossibleAssignation();
    }
}

record Step(int previousI, int previousL,
            int currentI, int currentL,
            int vehicle, String movement) {}