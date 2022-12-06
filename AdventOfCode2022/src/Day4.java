import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day4 {

    private static int[] assignment1;
    private static int[] assignment2;
    private static int counter;

    private static void toINT(String[] ss, int n){
        for(int i = 0; i < ss.length; i++){
            if(n == 1) assignment1[i] = Integer.parseInt(ss[i]);
            else assignment2[i] = Integer.parseInt(ss[i]);
        }
    }

    private static void checkPairs(){
        System.out.println(Arrays.toString(assignment1) + " " + Arrays.toString(assignment2));
        if( assignment1[0] >= assignment2[0] && assignment2[1] >= assignment1[0] || assignment1[1] >= assignment2[0] && assignment2[1] >= assignment1[1]){
            System.out.println("ok");
            counter++;
        }
        else if(assignment2[0] >= assignment1[0] && assignment1[1] >= assignment2[0] || assignment2[1] >= assignment1[0] && assignment1[1] >= assignment2[1]){
            System.out.println("ok");
            counter++;
        }
        else{
            System.out.println("nope");
        }
    }

    private static void leerFichero(String nombre){
        Path fichero = Path.of(nombre);
        try{
            for(String linea : Files.readAllLines(fichero)){
                String[] assignments = linea.split(",");
                String[] aux = assignments[0].split("-");
                assignment1 = new int[aux.length];
                assignment2 = new int[aux.length];
                toINT(Arrays.copyOf(aux, aux.length), 1);
                aux = assignments[1].split("-");
                toINT(Arrays.copyOf(aux, aux.length), 2);
                checkPairs();
            }
        }catch(IOException e){
            System.err.println("MAMAHUEVO");
        }
    }

    public static void main(String[] args) {
        leerFichero("day4.txt");
        System.out.println(counter);
    }
}
