package lab5.barBossHouse;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

public class TableOrdersManager implements OrdersManager, List<Order> {

    private Order[] orders;

    private Iterator<TableOrder> iterator;

    public TableOrdersManager(int tablesAmount) throws NegativeSizeException {
        if (tablesAmount < 0) {
            throw new NegativeSizeException("Tables amount < 0");
        }
        this.orders = new Order[tablesAmount];
    }

    @Override
    public void add(int index, Order element) {
        if (orders[index] != null) {
            throw new AlreadyAddedException("Table " + index + " is busy");
        }
        orders[index] = element;
    }

    public Order getOrder(int tableNum) {
        return this.orders[tableNum];
    }

    @Override
    public int getOrdersAtDay(LocalDate date) {
        int counter = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                continue;
            }
            if (orders[i].getCreationTime().getYear() == date.getYear() &&
                    orders[i].getCreationTime().getMonth() == date.getMonth() &&
                    orders[i].getCreationTime().getDayOfMonth() == date.getDayOfMonth()) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public Order[] getOrdersArrAtDay(LocalDate date) {
        Order[] arr = new TableOrder[getOrdersAtDay(date)];
        int counter = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                continue;
            }
            if (orders[i].getCreationTime().getYear() == date.getYear() &&
                    orders[i].getCreationTime().getMonth() == date.getMonth() &&
                    orders[i].getCreationTime().getDayOfMonth() == date.getDayOfMonth()) {
                arr[counter++] = orders[i];
            }
        }
        return arr;
    }


    @Override
    public Order[] getCustomerOrders(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("null Customer");
        }
        LabList<Order> custOrders = new LabList();
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getCustomer().equals(customer)) {
                custOrders.addEl(orders[i]);
            }
        }
        return custOrders.toArray(new Order[custOrders.getSize()]);
    }

    public void addItemToOrd(int tableNum, MenuItem item) throws UnlawfulActionException {
        if (tableNum < 0) {
            throw new IllegalArgumentException("Illegal table number: " + tableNum);
        }
        if (item == null) {
            throw new NullPointerException("MenuItem is null");
        }
        // try {
        this.orders[tableNum].add(item);
        // } catch (UnlawfulActionException exp) { // todo exp
        throw new UnlawfulActionException("under aged person cant buy alcohol");
        // }
    }

    public int getOrdersAmount() {
        int counter = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                ++counter;
            }
        }
        return counter;
    }

    public int getItemAmount(MenuItem item) {
        int counter = 0;
        MenuItem[] buf;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                buf = orders[i].getItemsArr();
                for (int j = 0; j < buf.length - 1; j++) {
                    if (buf[j].equals(item)) {
                        ++counter;
                    }
                }
            }
        }
        return counter;
    }

    public int removeAllOrders(Order order) {
        int counter = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].equals(order)) {
                ++counter;
            }
        }
        if (counter == 0) {
            return -1;
        } else return counter;
    }

    public int getFreeTableID() {
        for (int i = 0; i < orders.length; ++i) {
            if (orders[i] == null) {
                return i;
            }
        }
        throw new NoFreeTableException("no free table in order manager");
    }


    private int[] getAllId (Predicate predicate) {
        int counter = 0;
        for (int i = 0; i < orders.length; ++i) {
            if (orders[i] == null) {
                ++counter;
            }
        }
        if (counter == 0) {
            throw new NoFreeTableException("no free table in order manager");
        } else {
            int[] ids = new int[counter];
            counter = 0;
            for (int i = 0; i < orders.length; ++i) {
                if (predicate.test(orders[i])) {
                    ids[counter] = i;
                    ++counter;
                }
            }
            return ids;
        }
    }

    // todo test just in case
    public int[] getAllFreeId () {
        return getAllId((t) -> t == null);
    }

    public int[] getAllBusyID() {
        return getAllId((t) -> t != null);
    }

    public Order[] getOrders() {
        Order[] ordersCopy = new Order[orders.length];
        System.arraycopy(orders, 0, ordersCopy, 0, ordersCopy.length);
        return ordersCopy;
    }

    public double getTotalCost() {
        double sumPrice = 0.0D;
        for (int i = 0; i < this.orders.length; ++i) {
            if (this.orders[i] != null) {
                sumPrice += this.orders[i].getOrderPrice();
            }
        }
        return sumPrice;
    }

    public int getItemAmount(String itemTittle) {
        int amount = 0;
        for (int i = 0; i < this.orders.length; ++i) {
            if (this.orders[i] != null) {
                MenuItem[] itemsArr = this.orders[i].getItemsArr();
                for (int j = 0; j < itemsArr.length; ++j) {
                    if (itemsArr[j].getName().equals(itemTittle)) {
                        ++amount;
                    }
                }
            }
        }
        return amount;
    }


    // generated methods

    @Override
    public boolean addAll(int index, Collection<? extends Order> c) {   // todo understand logic
        if (index < 0 || index >= orders.length) {
            throw new IndexOutOfBoundsException("Illegal table number: " + index);
        }
        if (c == null) {
            throw new NullPointerException("Collection is null");
        }
        while (c.iterator().hasNext()) {
            try {
                orders[getFreeTableID()] = c.iterator().next();
            } catch (NoFreeTableException e) {
                throw new NoFreeTableException("not enough space to add all orders");
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) {
        return false;
    }

    @Override
    public Order get(int index) {
        if (orders[index] == null) {
            return null;
        }
        return orders[index];   // todo clone?
    }

    @Override
    public Order set(int index, Order element) {
        if (index < 0 || index >= orders.length) {
            throw new IndexOutOfBoundsException("Illegal table number: " + index);
        }
        Order oldOrd = orders[index];
        orders[index] = element;
        return oldOrd;
    }

    @Override
    public Order remove(int index) {
        if (index < 0 || index >= orders.length) {
            throw new IndexOutOfBoundsException("Illegal table number: " + index);
        }
        Order removedOrd = orders[index];
        orders[index] = null;
        return removedOrd;
    }

    @Override
    public boolean add(Order menuItems) {
        if (!(menuItems instanceof TableOrder)) {
            return false;
        }
        try {
            orders[getFreeTableID()] = menuItems;
            return true;
        } catch (NoFreeTableException e) {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Order> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Order> listIterator(int index) {
        return null;
    }

    @Override
    public List<Order> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public int size() {
        return getAllBusyID().length;
    }

    @Override
    public boolean isEmpty() {
        try {
            return getFreeTableID() > -1;
        } catch (NoFreeTableException e) {
            return true;
        }
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
    public boolean containsAll(Collection<?> c) {
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
