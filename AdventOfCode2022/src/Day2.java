import java.awt.image.LookupTable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Day2 {
    /*
    A/X -> ROCK -> 1
    B/Y -> PAPER -> 2
    C/Z -> SCISSORS -> 3
    +
    Lost -> 0
    Draw -> 3
    Win -> 6
     */
    private static int score = 0;
    private static Map<String, Map<String, Integer>> casos = new HashMap<>();

    private static void rellenarMapa(){
        Map<String, Integer> mapAux = new HashMap<>();
        mapAux.put("X", 3);
        mapAux.put("Y", 1 + 3);
        mapAux.put("Z", 2 + 6);
        casos.put("A", Map.copyOf(mapAux));
        mapAux.clear();
        mapAux.put("X", 1);
        mapAux.put("Y", 2 + 3);
        mapAux.put("Z", 3 + 6);
        casos.put("B", Map.copyOf(mapAux));
        mapAux.clear();
        mapAux.put("X", 2);
        mapAux.put("Y", 3 + 3);
        mapAux.put("Z", 1 + 6);
        casos.put("C", Map.copyOf(mapAux));
    }

    private static int compareRPS(String op, String me){
        if(op.equals("C") && me.equals("A")){
            return -1;
        }else if(op.equals("A") && me.equals("C")){
            return 1;
        }else{
           return op.compareTo(me);
        }
    }
    private static void leerFichero(String nombre){
        Path fichero = Path.of(nombre);
        rellenarMapa();
        try{
            for(String linea : Files.readAllLines(fichero)){
                String[] opciones = linea.split(" ");
              //  System.out.println(opciones[0] + " vs " + opciones[1] + " --> " + casos.get(opciones[0]).get(opciones[1]));
                score += casos.get(opciones[0]).get(opciones[1]);


            }
        }catch(IOException e){
            System.err.println("macaco");
        }
    }

    public static void main(String[] args) {
        leerFichero("day2.txt");
        //leerFichero("potato.txt");
        System.out.println(score);

    }
}
