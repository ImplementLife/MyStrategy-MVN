package game.myStrategy.lib.lists;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ILLinkedList<E> implements Iterable<E> {
    private transient Node<E> first;
    private transient Node<E> last;
    private transient int size;
    private transient Iterator<E> iterator;


    public Node<E> getFirst() {
        return first;
    }
    public Node<E> getLast() {
        return last;
    }

    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    public void addFirst(E e) {
        linkFirst(e);
    }
    public void addLast(E e) {
        linkLast(e);
    }

    public E removeFirst() {
        final Node<E> f = first;
        if (f == null) throw new NoSuchElementException();
        return unlinkFirst(f);
    }
    public E removeLast() {
        final Node<E> l = last;
        if (l == null) throw new NoSuchElementException();
        return unlinkLast(l);
    }

    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    public LinkedList<E> toLinkedList() {
        LinkedList<E> result = new LinkedList<>();
        for (Node<E> x = first; x != null; ) {
            result.add(x.item);
            x = x.next;
        }
        return result;
    }

    public int size() {
        return size;
    }

    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) first = newNode;
        else l.next = newNode;
        size++;
    }
    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null) last = newNode;
        else f.prev = newNode;
        size++;
    }

    private E unlinkFirst(Node<E> f) {
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null) last = null;
        else next.prev = null;
        size--;
        return element;
    }
    private E unlinkLast(Node<E> l) {
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null) first = null;
        else prev.next = null;
        size--;
        return element;
    }

    @Override
    public Iterator<E> iterator() {
        if (iterator == null) {
            iterator = new Iterator<E>() {
                Node<E> current;
                @Override
                public boolean hasNext() {
                    if (current == null) current = first;
                    return current.getNext() != null && current.getNext().getItem() != null;
                }

                @Override
                public E next() {
                    E item = current.getItem();
                    current = current.getNext();
                    return item;
                }
            };
        }
        return iterator;
    }

}
