// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package p2;

public class RapidoTiempos {
   static int[] v;

   public RapidoTiempos() {
   }

   public static void main(String[] var0) {
      String var5 = var0[0];
      int var6 = Integer.parseInt(var0[1]);

      for(int var7 = 10000; var7 < 100000000; var7 *= 2) {
         v = new int[var7];
         long var8 = 0L;

         for(int var10 = 1; var10 <= var6; ++var10) {
            if (var5.compareTo("ordenado") == 0) {
               Vector.ordenDirecto(v);
            } else if (var5.compareTo("inverso") == 0) {
               Vector.ordenInverso(v);
            } else {
               Vector.ordenAleatorio(v);
            }

            long var1 = System.currentTimeMillis();
            Rapido.rapido(v);
            long var3 = System.currentTimeMillis();
            var8 += var3 - var1;
         }

         System.out.println("n=" + var7 + "\tTiempo=" + var8 + "  \tRepeticiones=" + var6);
      }

   }
}