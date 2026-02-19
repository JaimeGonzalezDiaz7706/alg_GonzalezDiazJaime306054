package p2;

import p2.Insercion;
import p2.Rapido;

public class AlgoritmoOpcional {
	static int[] v;

	public static void combinacionQuickInsert(int[] a, int iz, int de) {
		int cruce = 40;
        
        if(iz<de){
            if((de-iz)<cruce){
                insercion(a, iz, de);
            } else {
                int m = particion(a, iz, de);
                combinacionQuickInsert(a, iz, m-1);
                combinacionQuickInsert(a, m+1, de);
            }   
        }
	}

    public static void insercion(int[] a, int iz, int de) {
		for (int i = iz+1; i <= de; i++) {
			int x = a[i];
			int j = i - 1;
			while (j >= iz && x < a[j]) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = x;
		} // for
	}

    private static void intercambiar(int[] v, int i, int j) {
		int t;
		t = v[i];
		v[i] = v[j];
		v[j] = t;
	}

	private static int particion(int[] v, int iz, int de) {
		int i, pivote;
		intercambiar(v, (iz + de) / 2, iz);
	
		pivote = v[iz];
		i = iz;
		for (int s = iz + 1; s <= de; s++)
			if (v[s] <= pivote) {
				i++;
				intercambiar(v, i, s);
			}
		intercambiar(v, iz, i);
		return i; 
	}

	public static void main(String arg[]) {
		int n = Integer.parseInt(arg[0]);
		v = new int[n];

		Vector.ordenDirecto(v);
		System.out.println("VECTOR A ORDENAR ES");
		Vector.escribe(v);
		combinacionQuickInsert(v, 0, v.length-1);
		System.out.println("VECTOR ORDENADO ES");
		Vector.escribe(v);

		Vector.ordenInverso(v);
		System.out.println("VECTOR A ORDENAR ES");
		Vector.escribe(v);
		combinacionQuickInsert(v, 0, v.length-1);
		System.out.println("VECTOR ORDENADO ES");
		Vector.escribe(v);

		Vector.ordenAleatorio(v);
		System.out.println("VECTOR A ORDENAR ES");
		Vector.escribe(v);
		combinacionQuickInsert(v, 0, v.length-1);
		System.out.println("VECTOR ORDENADO ES");
		Vector.escribe(v);
	} // fin de main

}


