import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day6 {

    private static int indexMarker;
    static private class Node<T> {
        T elem;
        Node<T> next;
        public Node(T x,Node<T> node) {
            elem = x;
            next = node;
        }

        public T getElem() {
            return elem;
        }
    }
    private interface Queue<T>{
        void enqueue(T x);
        void dequeue();
        T onTop();
        void clear();
    }
    private static class LinkedQueue<T> implements Queue<T> {
        private Node<T> top;

        @Override
        public void enqueue(T x) {
            if(top != null){
                top = new Node<T>(top.elem, enqueueREC(x, top.next));
            } else {
                top = new Node<T>(x, null);
            }
        }
        private Node<T> enqueueREC(T x, Node<T> node){
            if(node == null){
                return new Node<T>(x, null);
            }else{
                return new Node<>(node.elem, enqueueREC(x, node.next));
            }
        }

        @Override
        public void dequeue() {
            top = top.next;
        }

        @Override
        public T onTop() {
            return top.elem;
        }

        @Override
        public void clear() {
            top = null;
        }
        public int contElem(T x, Node<T> node){
            if(node == null){
                return 0;
            }else if(node.elem == x) {
                return 1 + contElem(x, node.next);
            }else{
                return contElem(x, node.next);
            }
        }

        public String toString() {
            String res = "STACK = { ";
            return REC(top, res) + "}";
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
    private static void iniQueue(Queue<Character> q, char[] c){
        q.clear();
        q.enqueue(c[0]);
        q.enqueue(c[1]);
        q.enqueue(c[2]);
    }
    private static void iniQueuePART2(Queue<Character> q, char[] c){
        q.clear();
        for(int i = 0; i< 13; i++){
            q.enqueue(c[i]);
        }
    }

    private static void leerFichero(String n){
        Path fichero = Path.of(n);
        try{
            LinkedQueue<Character> cola = new LinkedQueue<>();
            for(String linea : Files.readAllLines(fichero)){
                char[] caracteres = linea.toCharArray();

                //iniQueue(cola,caracteres);            <----- PART 1
                //int i = 3;                            <----- PART 1

                iniQueuePART2(cola, caracteres);
                int i = 13;

                boolean marker = false, rep;
                while(!marker && i < linea.length()){
                    System.out.println(i);
                    int j = 0;
                    rep = false;
                    cola.enqueue(caracteres[i]);
                   // System.out.println(cola.toString());
                    while(j<=i && !rep){
                        rep = cola.contElem(caracteres[j], cola.top) > 1;
                        j++;
                    }
                   // System.out.println(rep + "↑");
                    if(rep){
                        i++;
                        cola.dequeue();
                    }else{
                        marker = true;
                    }
                }
                if( i <= linea.length()) indexMarker += i+1;;
            }
        }catch (IOException e){
            System.err.println("CACHUETE");
        }
    }
    public static void main(String[] args) {
        leerFichero("day6.txt");
        System.out.println(indexMarker);
    }

}
