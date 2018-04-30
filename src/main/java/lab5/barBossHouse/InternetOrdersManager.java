package lab5.barBossHouse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

public class InternetOrdersManager implements OrdersManager, Deque<Order> {

    private MyDeque<Order> orders;
    private Iterator i;

    public InternetOrdersManager() {
        this(null);
    }

    public InternetOrdersManager(InternetOrder[] orderArr) {
        if (orderArr == null) {
            orders = new MyDeque(MyDeque.DEFAULT_CAP);
        } else {
            orders = new MyDeque(orderArr.length);
            for (int i = 0; i < orderArr.length; i++) {
                orders.addLast(orderArr[i]);
            }
        }
    }

    @Override
    public int getOrdersAtDay(LocalDateTime date) {
        int counter = 0;
        i = orders.iterator();
        while (i.hasNext()) {
            if (isDateEquals(date, (InternetOrder)i.next())) {
               counter++;
            }
        }
        return counter;
    }

    @Override
    public Order[] getOrdersArrAtDay(LocalDateTime date) {
        Order[] arr = new InternetOrder[getOrdersAtDay(date)];
        int counter = 0;
        i = orders.iterator();
        InternetOrder currentOrd;
        while (i.hasNext()) {
            currentOrd = (InternetOrder) i.next();
            if (isDateEquals(date, currentOrd)) {
                arr[counter++] = currentOrd;
            }
        }
        return arr;
    }

    private boolean isDateEquals(LocalDateTime date, InternetOrder ord) {
        return date.equals(ord.getCreationTime());
        /*
        return ord.getCreationTime().getYear() == date.getYear() &&
                ord.getCreationTime().getMonth() == date.getMonth() &&
                ord.getCreationTime().getDayOfMonth() == date.getDayOfMonth();
        */
    }

    @Override
    public Order[] getCustomerOrders(Customer customer) {
        if (customer == null) { throw new NullPointerException(); }
        MyList<Order> custOrders = new MyList();
        i = orders.iterator();
        InternetOrder currentOrder;
        while (i.hasNext()) {
            currentOrder = (InternetOrder) i.next();
            if (currentOrder.getCustomer().equals(customer)) {
                custOrders.add(currentOrder);
            }
        }
        return custOrders.toArray(new Order[custOrders.getSize()]);
    }

    public int getOrdersAmount() {
        return orders.size();
    }


    public InternetOrder[] getOrders() {
        InternetOrder[] arr = new InternetOrder[orders.size()];
        i = orders.iterator();
        int id = 0;
        while (i.hasNext()) {
            arr[id++] = (InternetOrder) i.next();
        }
        return arr;
    }

    public double getTotalCost() {
        double sumPrice = 0;
        i = orders.iterator();
        while (i.hasNext()) {
            sumPrice += ((InternetOrder)i.next()).getOrderPrice();
        }
        return sumPrice;
    }

    public int getItemAmount(String name) {
        if (name == null) { throw new NullPointerException(); }
        int items = 0;
        i = orders.iterator();
        while (i.hasNext()) {
            items += ((InternetOrder)i.next()).getItemsAmount(name);
        }
        return items;
    }

    public int getItemAmount(MenuItem item) {
        int items = 0;
        if (item == null) { throw new NullPointerException(); }
        i = orders.iterator();
        while (i.hasNext()) {
            items += ((InternetOrder)i.next()).getItemsAmount(item);
        }
        return items;
    }

    // implemented methods

    @Override
    public void addFirst(Order order) {
        orders.addFirst((InternetOrder) order);
    }

    @Override
    public void addLast(Order order) {
        orders.addLast((InternetOrder) order);
    }

    @Override
    public boolean offerFirst(Order order) {
        return orders.offerFirst((InternetOrder) order);
    }

    @Override
    public boolean offerLast(Order order) {
        return orders.offerLast((InternetOrder) order);
    }

    @Override
    public InternetOrder removeFirst() {
        return (InternetOrder)orders.removeFirst();
    }

    @Override
    public InternetOrder removeLast() {
        return (InternetOrder)orders.removeLast();
    }

    @Override
    public InternetOrder pollFirst() {
        return (InternetOrder)orders.pollFirst();
    }

    @Override
    public InternetOrder pollLast() {
        return (InternetOrder)orders.pollLast();
    }

    @Override
    public InternetOrder getFirst() {
        return (InternetOrder)orders.getFirst();
    }

    @Override
    public InternetOrder getLast() {
        return (InternetOrder)orders.getLast();
    }

    @Override
    public InternetOrder peekFirst() {
        return (InternetOrder)orders.peekFirst();
    }

    @Override
    public InternetOrder peekLast() {
        return (InternetOrder)orders.peekLast();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return orders.removeFirstOccurrence(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return orders.removeLastOccurrence(o);
    }

    @Override
    public boolean offer(Order order) {
        return orders.offer((InternetOrder) order);
    }

    @Override
    public InternetOrder remove() {
        return (InternetOrder)orders.remove();
    }

    @Override
    public InternetOrder poll() {
        return (InternetOrder)orders.poll();
    }

    @Override
    public InternetOrder element() {
        return (InternetOrder)orders.element();
    }

    @Override
    public InternetOrder peek() {
        return (InternetOrder)orders.peek();
    }

    @Override
    public void push(Order order) {
        orders.push(order);
    }

    @Override
    public InternetOrder pop() {
        return (InternetOrder) orders.pop();
    }

    @Override
    public Iterator<Order> descendingIterator() {
        return orders.descendingIterator();
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
            public InternetOrder next() {
                return (InternetOrder) orders.element(currentId++);
            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) { // todo this govno
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
    public boolean add(Order order) {
        return orders.add(order);
    }

    @Override
    public boolean remove(Object o) {
        return orders.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return orders.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) {
        return orders.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return orders.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return orders.retainAll(c);
    }

    @Override
    public void clear() {
        while (!orders.isEmpty()) {
            orders.poll();
        }
    }
}
