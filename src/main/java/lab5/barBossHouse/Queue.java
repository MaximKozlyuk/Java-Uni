package lab5.barBossHouse;

public class Queue<E> {

    // implements MyCollection<E> ???

    public static final int DEFAULT_SIZE = 5;
    private final int cap;
    private Node tail;
    private int size;
    private Node head;

    private Node link;

    public Queue() {
        this(DEFAULT_SIZE);
    }

    public Queue(int cap) {
        if (cap < 1) {
            throw new IllegalArgumentException("Wrong cap: " + cap);
        }
        tail = new Node(null, null);
        head = new Node(null, tail);
        this.cap = cap;
        size = 0;
    }

    public Queue(E[] arr) {
        this(arr.length);
        for (int i = 0; i < arr.length; i++) {
            add(arr[i]);
        }
    }

    public int getCap() {
        return cap;
    }

    public int getSize() {
        return size;
    }

    public E getEl(int id) {
        Node link = head;
        for (int i = 0; i <= id; i++) {
            link = link.next;
        }
        return link.item;
    }

    // Retrieves, but does not remove, the head of this queue
    public E peek() {
        return head.next.item;
    }

    public boolean add(E e) {
        if (size == cap) {
            return false;
        }
        if (size == 0) {
            tail.item = e;
        } else {
            tail.next = new Node(e, null);
            tail = tail.next;
        }
        ++size;
        return true;
    }

    // Retrieves and removes the head of this queue
    public E poll() {
        if (size == 0) {
            return null;
        }
        E savedLink = head.next.item;
        head.next = head.next.next;
        size--;
        return savedLink;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Queue with ").append(size).append(" elements");
        link = head;
        for (int i = 0; i <= size; i++) {
            str.append(link.item).append("\n");
            link = link.next;
        }
        return str.toString();
    }

    private class Node {
        private E item;
        private Node next;

        public Node(E item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

}