package lab5.barBossHouse.io;

import lab5.barBossHouse.Customer;
import lab5.barBossHouse.InternetOrder;
import lab5.barBossHouse.MenuItem;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;

public class ControlledInternetOrder extends InternetOrder {

    protected boolean isChanged = false;

    public ControlledInternetOrder () { super(); }

    public ControlledInternetOrder(MenuItem[] menu, Customer customer) {
        super(menu, customer);
    }

    public ControlledInternetOrder (InternetOrder order) {
        super.setCustomer(order.getCustomer().clone());
        super.addAll(order.subList(0,order.size()));
        super.setCreationTime(
                LocalDateTime.now().getYear(),
                LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth(),
                LocalDateTime.now().getHour(),
                LocalDateTime.now().getMinute()
        );
    }

    protected boolean isChanged () { return isChanged; }

    @Override
    public void setCreationTime(int year, Month month, int day, int hour, int minutes) {
        super.setCreationTime(year, month, day, hour, minutes);
    }

    @Override
    public void setCustomer(Customer customer) {
        isChanged = true;
        super.setCustomer(customer);
    }

    @Override
    public boolean add(MenuItem item) {
        isChanged = true;
        return super.add(item);
    }

    @Override
    public boolean removeItem(String name) {
        isChanged = true;
        return super.removeItem(name);
    }

    @Override
    public boolean removeItem(MenuItem item) {
        isChanged = true;
        return super.removeItem(item);
    }

    @Override
    public int removeAllByName(String name) {
        isChanged = true;
        return super.removeAllByName(name);
    }

    @Override
    public int removeAllByObj(MenuItem item) {
        isChanged = true;
        return super.removeAllByObj(item);
    }

    @Override
    public boolean remove(Object o) {
        isChanged = true;
        return super.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends MenuItem> c) {
        isChanged = true;
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends MenuItem> c) {
        isChanged = true;
        return super.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        isChanged = true;
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        isChanged = true;
        return super.retainAll(c);
    }

    @Override
    public void clear() {
        isChanged = true;
        super.clear();
    }

    @Override
    public MenuItem set(int index, MenuItem element) {
        isChanged = true;
        return super.set(index, element);
    }

    @Override
    public void add(int index, MenuItem element) {
        isChanged = true;
        super.add(index, element);
    }

    @Override
    public MenuItem remove(int index) {
        isChanged = true;
        return super.remove(index);
    }
}
