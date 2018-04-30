package lab5.barBossHouse;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class InternetOrdersManager implements OrdersManager, Deque<Order> {

    private MyDeque<InternetOrder> orders;

    public InternetOrdersManager() {
        this(null);
    }

    public InternetOrdersManager(InternetOrder[] orderArr) {
        if (orderArr == null) {
            orders = new MyDeque(MyDeque.DEFAULT_CAP);
        } else {
            orders = new MyDeque(orderArr.length);
            for (int i = 0; i < orderArr.length; i++) {
                //orders.addLast(orderArr[i]);
            }
        }
    }

    @Override
    public int getOrdersAtDay(LocalDate date) {
        int counter = 0;
        for (int i = 0; i < orders.getCap(); i++) {
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
        for (int i = 0; i < orders.getCap(); i++) {
            if (isDateEquals(date, i)) {
                arr[counter++] = orders.element(i);
            }
        }
        return arr;
    }

    private boolean isDateEquals(LocalDate date, int i) {
        return orders.element(i).getCreationTime().getYear() == date.getYear() &&
                orders.element(i).getCreationTime().getMonth() == date.getMonth() &&
                orders.element(i).getCreationTime().getDayOfMonth() == date.getDayOfMonth();
    }

    @Override
    public Order[] getCustomerOrders(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("null Customer");
        }
        MyList<Order> custOrders = new MyList();
        for (int i = 0; i < orders.getCap(); i++) {
            if (orders.element(i).getCustomer().equals(customer)) {
                custOrders.add(orders.element(i));
            }
        }
        return custOrders.toArray(new Order[custOrders.getSize()]);
    }

    public int getOrdersAmount() {
        return orders.size();
    }


    public InternetOrder[] getOrders() {
        InternetOrder[] arr = new InternetOrder[orders.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = orders.element(i);
        }
        return arr;
    }

    public double getTotalCost() {
        double sumPrice = 0;
        int size = orders.getCap();
        for (int i = 0; i < size; i++) {
            sumPrice += orders.element(i).getOrderPrice();
        }
        return sumPrice;
    }

    public int getItemAmount(String name) {
        int counter = 0;
        int size = orders.getCap();
        for (int i = 0; i < size; i++) {
            counter += orders.element(i).getItemsAmount(name);
        }
        return counter;
    }

    public int getItemAmount(MenuItem item) {
        int counter = 0;
        int size = orders.getCap();
        for (int i = 0; i < size; i++) {
            counter += orders.element(i).getItemsAmount(item);
        }
        return counter;
    }

    // generated methods

    @Override
    public void addFirst(Order menuItems) {
        if (menuItems == null) { throw new NullPointerException(); }
        if (!(menuItems instanceof InternetOrder)) { throw new ClassCastException(); }

    }

    @Override
    public void addLast(Order menuItems) {
        if (menuItems == null) { throw new NullPointerException(); }
        if (!(menuItems instanceof InternetOrder)) { throw new ClassCastException(); }
        //if (!orders.addLast((InternetOrder)menuItems)) {
        //    throw new IllegalStateException();
        //}
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
        return orders.size();
    }

    @Override
    public boolean isEmpty() {
        return orders.size() > 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) { throw new NullPointerException(); }
        if (!(o instanceof InternetOrder)) { throw new ClassCastException(); }
        return orders.contains((InternetOrder)o);
    }

    @Override
    public Iterator<Order> iterator() {
        return new Iterator<Order>() {
            int currentId = 0;

            @Override
            public boolean hasNext() {
                return currentId < orders.size();
            }

            @Override
            public Order next() {
                return orders.element(currentId++);
            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < orders.size())
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), orders.size());
        //int i = 0;
        Object[] result = a;

        for (int i = 0; i < orders.size(); i++) {
          result[i++] = orders.element(i);
        }

        //for (InternetOrder e : orders)
        //    result[i++] = e;
        if (a.length > orders.size())
            a[orders.size()] = null;
        return a;
    }

    @Override
    public boolean add(Order menuItems) {
        return true;
       // return addLast(menuItems); // todo ???
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
        while (orders.size() > 0) {
            orders.poll();
        }
    }
}
