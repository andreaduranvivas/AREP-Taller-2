package arep.clase.genericsLive;

public class Node<E> {

    private Node<E> next = null;
    private E value = null;

    public Node(E value){
        this.value = value;
    }

    public E getValue(){
        return value;
    }

    public Node<E> getNext(){
        return next;
    }

    public void setNext(Node<E> n){
        next = n;
    }
}
