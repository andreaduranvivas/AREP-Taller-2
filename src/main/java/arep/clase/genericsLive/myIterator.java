package arep.clase.genericsLive;

import java.util.Iterator;

public class myIterator <E> implements Iterator<E> {

    private Node<E> next = null;

    public myIterator(Node<E> n){
        next = n;
    };

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public E next() {
        Node<E> currentNext = next;
        next = currentNext.getNext();
        return currentNext.getValue();
    }

    public void remove() {
    }
}
