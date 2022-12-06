
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class Day5 {
    private final static int N_STACKS = 9;

    private static ArrayList<Node<String>> NODES = new ArrayList<>();
    private static ArrayList<LinkedStack<Character>> STACKS = new ArrayList<>(N_STACKS);

    static private class Node<T> {
        T elem;
        Node<T> next;
        public Node(T x, Node<T> node) {
            elem = x;
            next = node;
        }

        public T getElem() {
            return elem;
        }
    }
    private interface Stack<T>{
        void pushBack(T x);
        void push(T x);
        Node<T> onTop();
        T pop();
        Stack<T> popStack(int n);
        void pushStack(Stack<T> stack);
    }

    private static class LinkedStack<T> implements Stack<T>{
        private Node<T> top;

        //Some constructors btw
        public LinkedStack(){
            top = null;
        }
        public LinkedStack(Node<T> node){
            top = node;
        }

        //Puts the element on top
        @Override
        public void push(T x) {
            if(top != null)
                top = new Node<T>(x, new Node<>(top.elem, top.next));
            else
                top = new Node<>(x, null);
        }

        //Inserts the element at the Stack's end
        @Override
        public void pushBack(T x) {
            if(top != null)
                top = new Node<T>(top.elem, pushBackREC(top.next, x));
            else
                top = new Node<>(x, null);
        }
        private Node<T> pushBackREC(Node<T> node, T x){
            if((node == null)){
                return new Node<T>(x, null);
            }else{
                return new Node<T>(node.elem ,pushBackREC(node.next, x));
            }
        }

        //Returns the first node
        @Override
        public Node<T> onTop() {
            if(top != null)
                return top;
            else
                return null;
        }

        //Returns and deletes the first element from the Stack
        @Override
        public T pop() {
            T res = null;
            if(top != null) {
                res = top.elem;
                top = top.next;
            }else{
                top = null;
            }
            return res;
        }

        //Return the n firsts elements from the Stack as a new Stack
        @Override
        public LinkedStack<T> popStack(int n) {
            Node<T> res = null;
            if(top != null){
                if (top.next != null) {
                    res = new Node<T>(top.elem, popStackRec(n - 1, top.next));
                }else{
                    res = new Node<T>(top.elem, null);
                    top = null;
                }
            }
            return new LinkedStack<>(res);
        }
        private Node<T> popStackRec(int n, Node<T> node){
            if(n > 0 && node != null){
                return new Node<T>(node.elem, popStackRec(n-1, node.next));
            }else{
                top = node;
                return null;
            }
        }

        //Inserts the Stack
        @Override
        public void pushStack(Stack<T> stack) {
            if(stack.onTop() != null){
                top = new Node<T>(stack.onTop().getElem(), pushStackRec(stack.onTop().next));
            }
        }
        private Node<T> pushStackRec(Node<T> node){
            if(node != null){
                return new Node<T>(node.elem, pushStackRec(node.next));
            }else{
                return top;
            }
        }

        @Override
        public String toString() {
            String res = "STACK = { ";
            return REC(top, res);
        }
        private String REC(Node<T> node, String s){
            if(node != null){
                String res = s + node.elem + ", ";
                return REC(node.next, res);
            }else{
                return s;
            }
        }
    }

    private static void leerFichero(String n){
        Path fichero = Path.of(n);
        try{
            boolean leerDatos = true; //variable de control para parsear la entrada

            for(String linea : Files.readAllLines(fichero)){
                if(leerDatos){

                    if(linea.charAt(1) == '1') {
                        leerDatos = false;
                    }else {

                        for (int i = 0; i < linea.length(); i++) {
                            if (linea.charAt(i) != ' ' && linea.charAt(i) != '[' && linea.charAt(i) != ']') {
                                STACKS.get(i / 4).pushBack(linea.charAt(i));
                                //System.out.println(STACKS.get(i/4));
                            }
                        }
                    }
                }
                else if(!linea.equals("")){                                                       //  0       1       2     3    4    5
                    String[] instruction = linea.split(" ");                                // move (n veces) from (stack) to (stack)
                    LinkedStack<Character> aux = STACKS.get(Integer.parseInt(instruction[3])-1).popStack(Integer.parseInt(instruction[1]));
                    STACKS.get(Integer.parseInt(instruction[5])-1).pushStack(aux);
                   /*
                            ↑
                          Part 2

                          Part 1
                            ↓
                    */

                    /*int counterPOP = 0;
                    while(counterPOP < Integer.parseInt(instruction[1])){
                        Character aux = STACKS.get(Integer.parseInt(instruction[3]) - 1).pop();
                        STACKS.get(Integer.parseInt(instruction[5]) - 1).push(aux);
                        counterPOP++;
                    }*/
                }
            }
        }catch(IOException | NumberFormatException e){
            System.err.println("RAJOY");
        }
    }
    private static void initializeLists(){
        for(int i = 0; i < N_STACKS; i++){
            STACKS.add(new LinkedStack<Character>());
        }
    }

    public static void main(String[] args) {
        initializeLists();
        leerFichero("day5.txt");
        StringBuilder result = new StringBuilder();

        for(LinkedStack<Character> stack : STACKS){
            assert stack.onTop() != null;
            result.append(stack.onTop().getElem());
        }

        System.out.println(result);
        /*

        ------ PRUEBA PILA -------

        LinkedStack<Character> prueba = new LinkedStack<>();
        Character c;
        prueba.push('A');
        prueba.pushBack('C');
        System.out.println(prueba.top.next.elem);
        prueba.pushBack('P');
        System.out.println(prueba.onTop());
        c = prueba.pop();
        System.out.println(c + "->" +prueba.onTop());
        c = prueba.pop();
        System.out.println(c + "->" +prueba.onTop());
*/
    }
}