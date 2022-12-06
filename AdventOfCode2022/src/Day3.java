import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class Day3 {
    private static ArrayList<Character> comp1;
    private static ArrayList<Character> comp2;
    private static ArrayList<Character> comp3;

    private static int priorityTotal;

    private static void rellenarMochilas(char[] linea, int num){
        for(int i = 0; i < linea.length; i++){
            if(num == 0) {
                comp1.add(linea[i]);
            }else if(num == 1){
                comp2.add(linea[i]);
            }else{
                comp3.add(linea[i]);
            }
        }
    }

    private static void rellenarCompartimentos(char[] linea, int l){

        for(int i = 0; i < linea.length; i++){
            if(i < linea.length/2){
                comp1.add(linea[i]);
            }else{
                comp2.add(linea[i]);
            }
        }
    }

    private static void checkCandidatosV2(){
        boolean found = false;
        int i = 0;

        while (!found && i < comp1.size()) {
            int j = 0;
            while (j < comp2.size() && !found) {
                //System.out.println(comp1.get(i) + " "+ comp2.get(j));
                if( comp1.get(i) == comp2.get(j)){
                    int k = 0;
                    while(!found && k < comp3.size()){
                        found = comp1.get(i) == comp3.get(k);
                        k++;
                    }
                }
                j++;
            }
            if (!found) i++;
        }
        if (comp1.get(i) <= 'Z' && found) {
            priorityTotal += 26 + comp1.get(i) - '@';
            System.out.println(comp1.get(i) + " " +(comp1.get(i) + 26 + - '@'));

        } else if(found){
            priorityTotal += comp1.get(i) - '`';
            System.out.println( comp1.get(i) + " " + (comp1.get(i) - '`'));

        }
    }

    private static void checkCandidatos() {
        boolean found = false;
        int i = 0;

        while (!found && i < comp1.size()) {
            int j = 0;
            while (j < comp1.size() && !found) {
                //System.out.println(comp1.get(i) + " "+ comp2.get(j));
                found = comp1.get(i) == comp2.get(j);
                j++;
            }
            if (!found) i++;
        }
        if (comp1.get(i) <= 'Z' && found) {
            priorityTotal += 26 + comp1.get(i) - '@';
            System.out.println(comp1.get(i) + " " +(comp1.get(i) + 26 + - '@'));

        } else if(found){
            priorityTotal += comp1.get(i) - '`';
            System.out.println( comp1.get(i) + " " + (comp1.get(i) - '`'));

        }
    }

    private static void leerFichero(String n){
        Path fichero = Path.of(n);
        try{
            int team = 0;
            for(String linea : Files.readAllLines(fichero)){
                int l = linea.length()/2;
                //comp2 = new ArrayList<>(l);
                //comp1 = new ArrayList<>(l);
                if(team == 3) team = 0;
                if(team == 0) {
                    comp1 = new ArrayList<>(linea.length());
                    comp2 = new ArrayList<>(linea.length());
                    comp3 = new ArrayList<>(linea.length());
                }
                //rellenarCompartimentos(linea.toCharArray(), l);
                rellenarMochilas(linea.toCharArray(), team);
                team++;
                if(team == 3) {
                    checkCandidatosV2();
                }
            }

        }catch (IOException e){
            System.err.println("WATAFAK");
        }
    }

    public static void main(String[] args) {
        leerFichero("day3.txt");
        System.out.println(priorityTotal);
    }
}
