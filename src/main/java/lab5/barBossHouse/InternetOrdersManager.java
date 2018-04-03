package lab5.barBossHouse;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

// implements OrdersManager
public class InternetOrdersManager implements OrdersManager, Deque<Order> {

    private Queue<InternetOrder> queue;

    public InternetOrdersManager() {
        this(null);
    }

    public InternetOrdersManager(InternetOrder[] orderArr) {
        if (orderArr == null) {
            queue = new Queue(Queue.DEFAULT_SIZE);
        } else {
            queue = new Queue(orderArr.length);
            for (int i = 0; i < orderArr.length; i++) {
                queue.add(orderArr[i]);
            }
        }
    }

    @Override
    public int getOrdersAtDay(LocalDate date) {
        int counter = 0;
        for (int i = 0; i < queue.getCap(); i++) {
            if (isDateEquals(date, i)) {
                ++counter;
            }
        }
        return counter;
    }

    @Override
    public Order[] getOrdersArrAtDay(LocalDate date) {
        Order[] arr = new InternetOrder[getOrdersAtDay(date)];
        int counter = 0;
        for (int i = 0; i < queue.getCap(); i++) {
            if (isDateEquals(date, i)) {
                arr[counter++] = queue.getEl(i);
            }
        }
        return arr;
    }

    private boolean isDateEquals(LocalDate date, int i) {
        return queue.getEl(i).getCreationTime().getYear() == date.getYear() &&
                queue.getEl(i).getCreationTime().getMonth() == date.getMonth() &&
                queue.getEl(i).getCreationTime().getDayOfMonth() == date.getDayOfMonth();
    }

    @Override
    public Order[] getCustomerOrders(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("null Customer");
        }
        LabList<Order> custOrders = new LabList();
        for (int i = 0; i < queue.getCap(); i++) {
            if (queue.getEl(i).getCustomer().equals(customer)) {
                custOrders.addEl(queue.getEl(i));
            }
        }
        return custOrders.toArray(new Order[custOrders.getSize()]);
    }

    public boolean addOrd(InternetOrder ord) {
        if (ord == null) {
            return false;
        }
        return queue.add(ord);
    }

    public Order getOrder() {
        return queue.peek();
    }

    public Order getAndRemoveOrd() {
        return queue.poll();
    }

    public int getOrdersAmount() {
        return queue.getSize();
    }


    public InternetOrder[] getOrders() {
        InternetOrder[] arr = new InternetOrder[queue.getSize()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = queue.getEl(i);
        }
        return arr;
    }


    public double getTotalCost() {
        double sumPrice = 0;
        int size = queue.getCap();
        for (int i = 0; i < size; i++) {
            sumPrice += queue.getEl(i).getOrderPrice();
        }
        return sumPrice;
    }

    public int getItemAmount(String name) {
        int counter = 0;
        int size = queue.getCap();
        for (int i = 0; i < size; i++) {
            counter += queue.getEl(i).getItemsAmount(name);
        }
        return counter;
    }

    public int getItemAmount(MenuItem item) {
        int counter = 0;
        int size = queue.getCap();
        for (int i = 0; i < size; i++) {
            counter += queue.getEl(i).getItemsAmount(item);
        }
        return counter;
    }

    // generated methods

    @Override
    public void addFirst(Order menuItems) {

    }

    @Override
    public void addLast(Order menuItems) {

    }

    @Override
    public boolean offerFirst(Order menuItems) {
        return false;
    }

    @Override
    public boolean offerLast(Order menuItems) {
        return false;
    }

    @Override
    public Order removeFirst() {
        return null;
    }

    @Override
    public Order removeLast() {
        return null;
    }

    @Override
    public Order pollFirst() {
        return null;
    }

    @Override
    public Order pollLast() {
        return null;
    }

    @Override
    public Order getFirst() {
        return null;
    }

    @Override
    public Order getLast() {
        return null;
    }

    @Override
    public Order peekFirst() {
        return null;
    }

    @Override
    public Order peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean offer(Order menuItems) {
        return false;
    }

    @Override
    public Order remove() {
        return null;
    }

    @Override
    public Order poll() {
        return null;
    }

    @Override
    public Order element() {
        return null;
    }

    @Override
    public Order peek() {
        return null;
    }

    @Override
    public void push(Order menuItems) {

    }

    @Override
    public Order pop() {
        return null;
    }

    @Override
    public Iterator<Order> descendingIterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Order> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Order menuItems) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
