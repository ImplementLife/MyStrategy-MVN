package game.myStrategy.lib.lists;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testSetBefore() {
        Node<Integer> node1 = new Node<>(null, 2, null);
        Node<Integer> node2 = node1.setBefore(1);

        Assertions.assertEquals(node2, node1.getPrev());
        Assertions.assertEquals(node1, node2.getNext());
        Assertions.assertEquals(1, node2.getItem());
    }

    @Test
    void testSetAfter() {
        Node<Integer> node1 = new Node<>(null, 1, null);
        Node<Integer> node2 = node1.setAfter(2);

        Assertions.assertEquals(node2, node1.getNext());
        Assertions.assertEquals(node1, node2.getPrev());
        Assertions.assertEquals(2, node2.getItem());
    }

    @Test
    public void testReplace() {
        // create three nodes
        Node<Integer> node1 = new Node<>(null, 1, null);
        Node<Integer> node2 = new Node<>(node1, 2, null);
        Node<Integer> node3 = new Node<>(node2, 3, null);

        // replace node2 with a new node
        Node<Integer> newNode = new Node<>(null, 4, null);
        node2.replace(newNode);

        // check that the new node is linked correctly
        assertEquals(newNode, node1.getNext());
        assertEquals(newNode, node3.getPrev());
        assertEquals(node1, newNode.getPrev());
        assertEquals(node3, newNode.getNext());
        assertEquals(4, newNode.getItem().intValue());
    }

    @Test
    public void testReplaceOnBoundary() {
        // create two nodes
        Node<Integer> node1 = new Node<>(null, 1, null);
        Node<Integer> node2 = new Node<>(node1, 2, null);

        // replace node1 with a new node
        Node<Integer> newNode = new Node<>(null, 3, null);
        node1.replace(newNode);

        // check that the new node is linked correctly
        assertEquals(newNode, node2.getPrev());
        assertEquals(node2, newNode.getNext());
        assertEquals(3, newNode.getItem().intValue());
    }

    @Test
    public void testReplaceWithNull() {
        // create two nodes
        Node<Integer> node1 = new Node<>(null, 1, null);
        Node<Integer> node2 = new Node<>(node1, 2, null);

        // replace node1 with null
        node1.replace(null);

        // check that node2 is linked to null
        assertNull(node1.getNext());
        assertNull(node2.getPrev());
    }
}