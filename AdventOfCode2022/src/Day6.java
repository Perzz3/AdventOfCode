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
        public boolean isElemRep(T x, Node<T> node){
            if(node == null){
                return false;
            }else if(node.elem == x) {
                return true;
            }else{
                return isElemRep(x, node.next);
            }
        }
    }
    private static void iniQueue(Queue<Character> q, char[] c){
        q.clear();
        q.enqueue(c[0]);
        q.enqueue(c[1]);
        q.enqueue(c[2]);
    }

    private static void leerFichero(String n){
        Path fichero = Path.of(n);
        try{
            LinkedQueue<Character> cola = new LinkedQueue<>();
            for(String linea : Files.readAllLines(fichero)){
                char[] caracteres = linea.toCharArray();
                iniQueue(cola, caracteres);
                int i = 3;
                boolean marker = false, rep;
                while(!marker && i < linea.length()){
                    int j = 0;
                    rep = false;
                    cola.enqueue(caracteres[i]);
                    while(j<=i && !rep){
                        rep = cola.isElemRep(caracteres[j], cola.top);
                        j++;
                    }
                    if(!rep){
                        i++;
                        cola.dequeue();
                    }else{
                        marker = true;
                    }
                }
                if( i <= linea.length())indexMarker += i;;
            }
        }catch (IOException e){
            System.err.println("CACHUETE");
        }
    }
    public static void main(String[] args) {
        leerFichero("potato.txt");
    }
}
