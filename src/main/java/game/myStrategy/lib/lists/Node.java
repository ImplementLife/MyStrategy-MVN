package game.myStrategy.lib.lists;

public class Node<E> {
    E item;
    protected Node<E> next; //also, as After
    protected Node<E> prev; //also, as Before

    public Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        setBefore(prev);
        setAfter(next);
    }

    public Node<E> setBefore(E element) {
        return setBefore(new Node<E>(null, element, this));
    }
    public Node<E> setAfter(E element) {
        return setAfter(new Node<E>(this, element, null));
    }

    public <N extends Node<E>> N setBefore(N node) {
        prev = node;
        if (node != null) node.next = this;
        return node;
    }
    public <N extends Node<E>> N setAfter(N node) {
        next = node;
        if (node != null) node.prev = this;
        return node;
    }

    public <N extends Node<E>> N replace(N node) {
        if (node != null) {
            node.setBefore(prev);
            node.setAfter(next);
        }
        //TODO: bad if node null?
        if (next != null) next.setBefore(node);
        if (prev != null) prev.setAfter(node);

        next = null;
        prev = null;
        return node;
    }
    //public <N extends Node<E>> void insertBetween(N node1, N node2) {} TODO ?

    public E getItem() {
        return item;
    }
    public void setItem(E item) {
        this.item = item;
    }

    public Node<E> getNext() {
        return next;
    }
    public Node<E> getPrev() {
        return prev;
    }

}
