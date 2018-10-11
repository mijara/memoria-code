package org.linkeddatafragments.datasource.hdt.cache;

public class DoublyLinkedList<K, V> {
    private int listSize;

    private Node<K, V> head;
    private Node<K, V> tail;

    public synchronized void prepend(Node<K, V> node) {
        node.next = head;

        if (head != null) {
            head.previous = node;
        }

        head = node;

        if (tail == null) {
            tail = node;
        }

        listSize++;
    }

    public synchronized void remove(Node<K, V> node) {
        if (node.previous != null) {
            node.previous.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.previous = node.previous;
        } else {
            tail = node.previous;
        }

        node.previous = null;
        node.next = null;

        listSize--;
    }

    public int size() {
        return listSize;
    }

    public Node<K, V> getHead() {
        return head;
    }

    public Node<K, V> getTail() {
        return tail;
    }

    public void debugList() {
        Node<K, V> node = head;

        while (node != null) {
            System.out.print(node.value);
            System.out.print(" | ");
            node = node.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        DoublyLinkedList<String, Integer> dll = new DoublyLinkedList<>();

        for (int i = 0; i < 10000; i++) {
            dll.prepend(new Node<>("myValue", 1, -1));

            if (i % 100 == 0) {
                dll.remove(dll.getTail());
            }
        }
    }
}
