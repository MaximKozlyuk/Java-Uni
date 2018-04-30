package lab5.barBossHouse;

import java.util.Iterator;

public class MyList<E> implements Iterable<E> {

    private static final int DEFAULT_SIZE = 0;

    private int size;

    private Node first;
    private Node last;

    private Node temp;

    public MyList() {
        initDefaultList();
    }

    public MyList(E[] itemArr) {
        initDefaultList();
        if (itemArr != null) {
            last.item = itemArr[0];
            size++;
            for (; size < itemArr.length; size++) {
                last.next = new Node(itemArr[size], null);
                last = last.next;
            }
        }
    }

    private void initDefaultList() {
        size = DEFAULT_SIZE;
        last = new Node(null, null);
        first = new Node(null, last);
    }

    public int getSize() {
        return this.size;
    }

    public void add (int index, E e) {
        if (index < 0 || index > size) { throw new IllegalArgumentException("Wrong index"); }
        // todo if 0 position
        temp = first.next;
        for (int i = 0; i < index-1; i++) { temp = temp.next; }
        temp.next = new Node (e, temp.next);
        size++;
    }

    public void add(E e) {
        if (last.item == null) {
            last.item = e;
        } else {
            last.next = new Node(e, null);
            last = last.next;
        }
        size++;
    }

    public void addAll(E[] arr) {   // todo optimization    /resolved
        if (arr == null) {
            throw new NullPointerException("Cant addLast null array");
        }
        int i = 0;
        if (size == 0) {
            last.item = arr[0];
            i++;
            size++;
        }
        for (; i < arr.length; i++) {
            last.next = new Node(arr[i], null);
            last = last.next;
            size++;
        }
    }

    public boolean remove(int id) {
        if (id > size || id < 0) {
            throw new IllegalArgumentException("id:" + id + " illegal id");
        }
        if (size == 0) { return false; }
        temp = first;
        for (int i = 0; i < id; i++) {
            temp = temp.next;
        }   // в tempLink попадает Node перед удаляемым
        if (id == size - 1) {
            temp.next = null;   // если удаляется последний элемент
        } else {
            temp.next = temp.next.next;
        }
        size--;
        return true;
    }

    public E getEl(int id) {
        if (id > size || id < 0) {
            throw new IllegalArgumentException("Illegal id :" + id);
        }
        Node temp = first;
        for (int i = -1; i < id; i++) {
            temp = temp.next;
        }
        return (E) temp.item;
    }

    public boolean contains(E obj) {
        temp = first.next;
        try {
            for (int i = 0; i < size; i++) {
                if (temp.item.equals(obj)) {
                    return true;
                } else {
                    temp = temp.next;
                }
            }
        } catch (NullPointerException exp) {
            throw exp;
        }
        return false;
    }

    public E set (int index, E item) {
        temp = first.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        E previousItem = (E)temp.item;

        return previousItem;
    }

    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;
        for (Node<E> x = first.next; x != null; x = x.next)
            result[i++] = x.item;
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public String toString() {
        Node tempLink = first.next;
        StringBuilder str = new StringBuilder("List with ");
        str.append(size).append(" elements\n");
        for (int i = 0; i < size; i++) {
            str.append(tempLink.item).append("\n");
            tempLink = tempLink.next;
        }
        return str.toString();
    }

    @Override   // todo test
    public int hashCode () {
        temp = first.next;
        int hash = temp.item.hashCode();
        for (int i = 1; i < size; i++) {
            temp = temp.next;
            hash = hash ^ temp.item.hashCode();
        }
        return hash;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int currentId = 0;

            @Override
            public boolean hasNext() {
                return currentId < size && size != 0;
            }

            @Override
            public E next() {
                return getEl(currentId++);
            }
        };
    }

    private class Node<E> {
        private E item;
        private Node next;

        public Node(E item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}
