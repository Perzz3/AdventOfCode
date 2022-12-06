
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day1 {
    private static int[] top;

    private final static int N_TOP = 3;
    private static int asdf;

    private static void compararTOP(int suma){
        asdf = compararTOPREC(suma, N_TOP - 1);
    }

    private static int compararTOPREC(int suma, int indice){
        int val = 0;
        if (suma > top[indice]) {
            val = top[indice];
            if(indice == 0){
                top[indice] = suma;
            }else{
                top[indice] = compararTOPREC(suma, indice - 1);
            }
        }else{
            return suma;
        }
        return val;
    }
    private static void leerFichero(String fichero){
        int suma = 0 ;
        top = new int[3];
        Path f = Path.of(fichero);
        try {
            for (String s : Files.readAllLines(f)){
                if(!s.equals("")){
                    int val = Integer.parseInt(s);
                    suma += val;
                }else{
                    compararTOP(suma);
                    suma = 0;
                }
            }
        }catch (IOException e){
            System.err.println("fichero mal");
        }
    }

    public static void main(String[] args) {
        String fichero = "day1.txt";
        leerFichero(fichero);
        System.out.println(top[0] + top[1] + top[2]);
    }
}
