import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day6 {
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
    }

    private static void leerFichero(String n){
        Path fichero = Path.of(n);
        try{
            for(String linea : Files.readAllLines(fichero)){

            }
        }catch (IOException e){
            System.err.println("CACHUETE");
        }
    }
    public static void main(String[] args) {

    }
}
