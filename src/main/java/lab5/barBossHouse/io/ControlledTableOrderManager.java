package lab5.barBossHouse.io;

import lab5.barBossHouse.Order;
import lab5.barBossHouse.NegativeSizeException;
import lab5.barBossHouse.TableOrder;
import lab5.barBossHouse.TableOrdersManager;

import java.util.Collection;

public class ControlledTableOrderManager extends TableOrdersManager{

    protected Source<Order> source;

    public ControlledTableOrderManager () { super(); }

    public ControlledTableOrderManager(int tablesAmount) throws NegativeSizeException {
        super(tablesAmount);
    }

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
    public void add(int index, Order element) {
        ControlledTableOrder cto = new ControlledTableOrder((TableOrder)element);
        source.create(cto);
        super.add(index, cto);
    }

    @Override
    public int removeAllOrders(Order order) {
        source.delete(order);
        return super.removeAllOrders(order);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Order> c) {
        for (Object i : c) { source.create((TableOrder) i); }
        return super.addAll(index, c);
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) {
        for (Object i : c) { source.create((TableOrder) i); }
        return super.addAll(c);
    }

    @Override
    public lab5.barBossHouse.Order set(int index, Order element) {
        ControlledTableOrder cto = new ControlledTableOrder((TableOrder)element);
        source.create(cto);
        return super.set(index, element);
    }

    @Override
    public Order remove(int index) {
        source.delete(super.get(index));
        return super.remove(index);
    }

    @Override
    public boolean add(Order menuItems) {
        ControlledTableOrder cto = new ControlledTableOrder((TableOrder)menuItems);
        source.create(cto);
        return super.add(menuItems);
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof TableOrder)) { throw new ClassCastException(); }
        source.delete((TableOrder)o);
        return super.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object i : c) { source.delete((Order)i); }
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Order[] orders = super.getOrders();
        for (Order i : orders) {
            if (!c.contains(i)) {
                source.delete(i);
            }
        }
        return super.retainAll(c);
    }

    @Override
    public void clear() {
        Order[] orders = super.getOrders();
        for (Order i : orders) {
            source.delete(i);
        }
        super.clear();
    }
}






























