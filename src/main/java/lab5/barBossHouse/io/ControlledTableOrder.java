package lab5.barBossHouse.io;

import lab5.barBossHouse.Customer;
import lab5.barBossHouse.MenuItem;
import lab5.barBossHouse.TableOrder;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;

public class ControlledTableOrder extends TableOrder {

    protected boolean isChanged = false;

    public ControlledTableOrder () { super(); }

    public ControlledTableOrder (int amountOfItems, Customer customer) { super(amountOfItems, customer); }

    public ControlledTableOrder (MenuItem[] items, Customer customer) {
        super(items, customer);
    }

    // todo хз как достать все поля из ссылки Order (как указано в задании), поэтому TableOrder
    public ControlledTableOrder (TableOrder order) {
        super.setCustomer(order.getCustomer().clone());
        super.addAll(order.subList(0,order.size()));
        super.setCreationTime(
                LocalDateTime.now().getYear(),
                LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth(),
                LocalDateTime.now().getHour(),
                LocalDateTime.now().getMinute(),
                LocalDateTime.now().getSecond(),
                LocalDateTime.now().getNano()
        );
    }

    protected boolean isChanged () { return isChanged; }

    @Override
    public void setCreationTime(int year, Month month, int day, int hour, int minutes, int seconds, int nanoseconds) {
        super.setCreationTime(year, month, day, hour, minutes, seconds, nanoseconds);
        isChanged = true;
    }

    @Override
    public void setCustomer(Customer customer) {
        super.setCustomer(customer);
        isChanged = true;
    }

    @Override
    public boolean add(MenuItem menuItem) {
        isChanged = true;
        return super.add(menuItem);
    }

    @Override
    public boolean removeItem(String itemTitle) {
        isChanged = true;
        return super.removeItem(itemTitle);
    }

    @Override
    public boolean removeItem(MenuItem item) {
        isChanged = true;
        return super.removeItem(item);
    }

    @Override
    public int removeAllByName(String itemName) {
        isChanged = true;
        return super.removeAllByName(itemName);
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
