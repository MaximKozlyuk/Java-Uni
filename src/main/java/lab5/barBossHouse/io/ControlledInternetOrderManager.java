package lab5.barBossHouse.io;

import lab5.barBossHouse.Order;
import lab5.barBossHouse.InternetOrder;
import lab5.barBossHouse.InternetOrdersManager;

public class ControlledInternetOrderManager extends InternetOrdersManager {

    protected Source<Order> source;

    public ControlledInternetOrderManager() { super(); }

    public ControlledInternetOrderManager(InternetOrder[] orderArr) { super(orderArr); }

    public Source<Order> getSource() { return source; }

    public void setSource(Source<Order> source) { this.source = source; }

    public void store () {
        Order[] orders = super.getOrders();
        for (Order i : orders) {
            if (((ControlledTableOrder)i).isChanged) {
                source.store(i);
            }
        }
    }

    public void load(Order order) {
        Order[] orders = super.getOrders();
        for (Order i : orders) { source.load(i); }
    }

    @Override
    public void addFirst(Order order) {
        addNew(order);
        super.addFirst(order);
    }

    @Override
    public void addLast(Order order) {
        addNew(order);
        super.addLast(order);
    }

    @Override
    public boolean offerFirst(Order order) {
        if (super.offerFirst(order)) {
            addNew(order);
            return true;
        }
        return false;
    }

    @Override
    public boolean offerLast(Order order) {
        if (super.offerLast(order)) {
            addNew(order);
        }
        return false;
    }

    @Override
    public InternetOrder removeFirst() {
        InternetOrder ord = super.removeFirst();
        source.delete(ord);
        return ord;
    }

    @Override
    public InternetOrder removeLast() {
        InternetOrder ord = super.removeLast();
        source.delete(ord);
        return ord;
    }

    @Override
    public InternetOrder pollFirst() {
        InternetOrder ord = super.pollFirst();
        if (ord != null) { source.delete(ord); }
        return ord;
    }

    @Override
    public InternetOrder pollLast() {
        InternetOrder ord = super.pollLast();
        if (ord != null) { source.delete(ord); }
        return ord;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        source.delete((Order)o);
        return super.removeFirstOccurrence(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        source.delete((Order)o);
        return super.removeLastOccurrence(o);
    }

    @Override
    public boolean offer(Order order) { return offerLast(order); }

    @Override
    public InternetOrder remove() { return removeFirst(); }

    @Override
    public InternetOrder poll() { return pollFirst(); }

    @Override
    public InternetOrder element() { return getFirst(); }

    @Override
    public void push(Order order) { addFirst(order); }

    @Override
    public InternetOrder pop() { return removeFirst(); }

    private void addNew (Order order) {
        ControlledInternetOrder cio = new ControlledInternetOrder((InternetOrder)order);
        source.create(cio);
    }

}


























