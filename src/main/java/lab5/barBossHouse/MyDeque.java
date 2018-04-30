package lab5.barBossHouse;

import java.util.*;
import java.util.function.Predicate;

public class MyDeque<E> implements Deque<E> {

    public static final int DEFAULT_CAP = 16;
    private final int cap;
    private int size;
    private Node tail;
    private Node head;

    private Node link;

    public MyDeque() {
        this(DEFAULT_CAP);
    }

    public MyDeque(int cap) {
        if (cap < 1) { throw new IllegalArgumentException("Wrong cap: " + cap); }
        this.cap = cap;
        size = 0;
    }

    public MyDeque(Collection<? extends E> c) {
        this(c.size());
        addAll(c);
    }

    public int getCap() {
        return cap;
    }

    public E element (int id) {
        Node link = head;
        for (int i = 0; i <= id; i++) {
            link = link.next;
        }
        return link.item;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("MyDeque with ").append(size).append(" elements\n");
        link = head;
        for (int i = 0; i < size; i++) {
            str.append(link.item).append("\n");
            link = link.next;
        }
        return str.toString();
    }

    private class Node {
        private E item;
        private Node prev;
        private Node next;

        private Node(E item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        private Node () {
            this(null, null, null);
        }

    }

    // implemented methods
    // collection methods

    @Override
    public boolean contains(Object o) {
        if (o == null) { throw new NullPointerException(); }
        Iterator iterator = this.iterator();
        try {
            while (iterator.hasNext()) {
                if (iterator.next().equals(o)) {
                    return true;
                }
            }
        } catch (ClassCastException exp) {
            throw exp;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int currentId = 0;
            Node lasReturned = head;

            @Override
            public void remove() {
                if (size == 0) { throw new NoSuchElementException(); }
                if (currentId == 1) {
                    head = head.next;
                } else {
                    link = head;
                    for (int i = 1; i < currentId-1; i++) {
                        link = link.next;
                    }
                    link.next = link.next.next;
                }
                currentId--;
                size--;
            }

            @Override
            public boolean hasNext() {
                return currentId < size;
            }

            @Override
            public E next() {
                E toReturn = lasReturned.item;
                lasReturned = lasReturned.next;
                currentId++;
                return toReturn;
            }
        };
    }

    @Override
    public Object[] toArray() { // todo both toArray
        Object[] returnArr = new Object[size];
        Iterator iterator = iterator();
        int id = 0;
        while (iterator.hasNext()) {
            returnArr[id++] = iterator.next();
        }
        return returnArr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
//        if (a.length < size)
//            a = (E[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
//        int i = 0;
//        Object[] result = a;
//        link = head;
//        for ( ; i < size; link = link.next)
//            result[i++] = link.item;
//        if (a.length > size)
//            a[size] = null;
//        return a;
        return null;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) { throw new NullPointerException(); }
        Iterator iterator = this.iterator();
        try {
            while (iterator.hasNext()) {
                if (iterator.next().equals(o)) {
                    iterator.remove();
                    return true;
                }
            }
        } catch (ClassCastException exp) {
            throw exp;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) { throw new NullPointerException(); }
        Iterator collIterator = c.iterator();
        while (collIterator.hasNext()) {
            try {
                if (!this.contains(collIterator.next())) {
                    return false;
                }
            } catch (ClassCastException exp) {
                throw exp;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) { throw new NullPointerException(); }
        Iterator collIterator = c.iterator();
        while (collIterator.hasNext()) {
            if (!offer((E) collIterator.next())) { return false; }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Iterator thisIterator = this.iterator();
        return reSmthAll((t) -> c.contains(thisIterator.next()), c, thisIterator);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Iterator thisIterator = this.iterator();
        return reSmthAll((t) -> !c.contains(thisIterator.next()), c, thisIterator);
    }

    private boolean reSmthAll (Predicate predicate, Collection<?> c, Iterator thisIterator) {
        if (c == null) { throw new NullPointerException(); }
        while (thisIterator.hasNext()) {
            if (predicate.test(c)) { thisIterator.remove(); }
        }
        return true;
    }

    @Override
    public void clear() {   // todo check
        while (size != 0) {
            poll();
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Dequeue methods

    @Override
    public void addFirst(E e) {
        addCheck(e);
        Node newFirst = new Node(e, null, null);
        if (head != null) {
            newFirst.next = head;
            head.prev = newFirst;
        }
        head = newFirst;
        if (tail == null) tail = head;
        size++;
    }

    @Override
    public void addLast(E e) {
        addCheck(e);
        Node newLast = new Node(e, null, null);
        if (tail != null) {
            newLast.prev = tail;
            tail.next = newLast;
        }
        tail = newLast;
        if (head == null) head = tail;
        size++;
    }

    private void addCheck (E e) {
        if (size >= cap) { throw new IllegalStateException(); }
        if (e == null) { throw new NullPointerException(); }
    }

    @Override
    public boolean offerFirst(E e) {
        if (size >= cap) { return false; }
        if (e == null) { throw new NullPointerException(); }

        Node newFirst = new Node(e, null, null);
        if (head != null) {
            newFirst.next = head;
            head.prev = newFirst;
        }
        head = newFirst;
        if (tail == null) tail = head;
        size++;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (size >= cap) { return false; }
        if (e == null) { throw new NullPointerException(); }

        Node newLast = new Node(e, null, null);
        if (tail != null) {
            newLast.prev = tail;
            tail.next = newLast;
        }
        tail = newLast;
        if (head == null) head = tail;
        size++;
        return true;
    }

    @Override
    public E removeFirst() {
        if (size < 1) { throw new NoSuchElementException(); }
        return disposeFirst();
    }

    @Override
    public E pollFirst() {
        if (size == 0) { return null; }
        return disposeFirst();
    }

    private E disposeFirst () {
        Node oldFirst = head;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
        return oldFirst.item;
    }

    @Override
    public E removeLast() {
        if (size == 0) { throw new NoSuchElementException(); }
        return disposeLast();
    }

    @Override
    public E pollLast() {
        if (size == 0) { return null; }
        return disposeLast();
    }

    private E disposeLast () {
        Node oldLast = tail;
        tail = oldLast.prev;
        size--;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        return oldLast.item;
    }

    @Override
    public E getFirst() {
        if (size == 0) { throw new NoSuchElementException(); }
        return head.item;
    }

    @Override
    public E getLast() {
        if (size == 0) { throw new NoSuchElementException(); }
        return tail.item;
    }

    @Override
    public E peekFirst() {
        if (size == 0) { return null; }
        return head.item;
    }

    @Override
    public E peekLast() {
        if (size == 0) { return null; }
        return tail.item;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o); // todo ¯\_(ツ)_/¯
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        if (o == null) { throw new NullPointerException(); }
        Iterator i = descendingIterator();
        Object last;
        while (i.hasNext()) {
            last = i.next();
            if (last.equals(o)) {
                i.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean offer(E e) {
        return offerLast(e);
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new Iterator<E>() {
            int currentId = size+1;
            Node lastReturned = tail;

            @Override
            public boolean hasNext() {
                return currentId > 0;
            }

            @Override
            public void remove() {
                if (size == 0) { throw new NoSuchElementException(); }
                if (currentId == 1) {
                    pollFirst();
                    return;
                } else if (currentId == size) {
                    pollLast();
                    return;
                } else {
                    lastReturned.next = lastReturned.next.next;
                    lastReturned.next.prev = lastReturned;
                    size--;
                }
                currentId--;
            }

            @Override
            public E next() {
                E r = lastReturned.item;
                lastReturned = lastReturned.prev;
                currentId--;
                return r;
            }
        };
    }

}













