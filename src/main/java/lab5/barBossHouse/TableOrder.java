package lab5.barBossHouse;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.function.Predicate;

public class TableOrder implements Order {

    private static final int DEFAULT_CAP = 16;
    private MenuItem[] items;
    private int size;   // todo do nice logic + rename /resolved
    private Customer customer;
    private LocalDateTime creationTime;

    public TableOrder() {
        this(DEFAULT_CAP, new Customer());
    }

    public TableOrder(int amountOfItems, Customer customer) // throws NegativeSizeException  // todo exp?
    {
        if (amountOfItems < 0) {
            throw new NegativeSizeException("array size < 0");
        }
        this.size = 0;
        this.items = new MenuItem[amountOfItems];
        this.customer = customer.clone();
        creationTime = LocalDateTime.now();
    }

    // todo might be added alc drinks, check arr and customer / resolved
    public TableOrder(MenuItem[] items, Customer customer) // throws UnlawfulActionException // todo exp
    {
        if (customer.getAge() < Customer.AGE_OF_MAJORITY) {
            for (MenuItem i : items) {
                if (i instanceof Drink && ((Drink) i).getAlcPct() > 0) {
                    // throw new UnlawfulActionException("under aged person cant buy alcohol");     // todo exp
                }
            }
        }
        this.items = new MenuItem[items.length];
        System.arraycopy(items, 0, this.items, 0, items.length);
        this.size = items.length;
        this.customer = customer.clone();
        creationTime = LocalDateTime.now();
    }

    public void setCreationTime(int year, Month month, int day, int hour, int minutes, int seconds, int nanoseconds) {
        creationTime = LocalDateTime.of(year, month, day, hour, minutes, seconds, nanoseconds);
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public Customer getCustomer() {
        return customer.clone();
    }

    @Override
    public void setCustomer(Customer customer) {
        this.customer = customer.clone();
    }

    @Override
    public boolean add(MenuItem menuItem) {
        if (menuItem instanceof Drink && ((Drink) menuItem).isAlcoholable()) {
            if (customer.getAge() < Customer.AGE_OF_MAJORITY ||
                    customer == Customer.UNDERAGED_UNKNOWN_CUSTOMER ||
                    LocalDateTime.now().getHour() >= 22) {
                //throw new UnlawfulActionException("under aged person cant buy alcohol");
                // todo шо тут делать хз
            }
        }
        if (size == items.length) {
            incMenuSize();
        }
        items[size] = menuItem;
        ++size;
        return true;
    }

    private boolean remove(Predicate<MenuItem> predicate) {
        int i = 0;
        for (; i < size + 1; ++i) {
            if (predicate.test(items[i])) {
                items[i] = null;
                removeHole(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(String itemTitle) {   // todo try to understand / ±resolved :)
        return remove((t) -> t.getName().equals(itemTitle));
    }

    @Override
    public boolean removeItem(MenuItem item) {
        return remove((t) -> t.equals(item));
    }

    @Override
    public int removeAllByName(String itemName) {
        int deleted = 0;
        int i = 0;
        while (i < size) {
            if (items[i].getName().equals(itemName)) {
                items[i] = null;
                removeHole(i);
                ++deleted;
            } else {
                ++i;
            }
        }
        return deleted;
    }

    @Override
    public int removeAllByObj(MenuItem item) {
        int deleted = 0, counter = 0;
        int i = 0;
        while (i < size) {
            if (items[i].equals(item)) {
                items[i] = null;
                removeHole(i);
                ++deleted;
            } else {
                ++i;
            }
        }
        for (i = 0; i < size; ) {
            if (items[i + counter] == null)
                counter++;
            else {
                items[i] = items[i + counter];
                i++;
            }
        }
        return deleted;
    }

    private void incMenuSize() {
        MenuItem[] itemsBuff = this.items;
        this.items = new MenuItem[(this.size + 1) * 2];
        System.arraycopy(itemsBuff, 0, this.items, 0, itemsBuff.length);
    }

    private void removeHole(int removedEl) {
        int i;
        --size;
        for (i = removedEl; items[i + 1] != null && i < items.length - 1; ++i) {
            items[i] = items[i + 1];
        }
        this.items[i] = null;
    }

    @Override
    public int getAmountOfItems() {
        return this.size;
    }

    @Override
    public MenuItem[] getItemsArr() {
        MenuItem[] arrToReturn = new MenuItem[this.size];
        System.arraycopy(items, 0, arrToReturn, 0, size);
        return arrToReturn;
    }

    @Override
    public double getOrderPrice() {
        double price = 0.0D;
        for (int i = 0; i < size; i++) {
            price += items[i].getPrice();
        }
        return price;
    }

    private int getItemsQuantity(Predicate<MenuItem> predicate) {
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (predicate.test(items[i])) {
                ++counter;
            }
        }
        return counter;
    }

    @Override
    public int getItemsAmount(String name) {
        return getItemsQuantity((t) -> t.getName().equals(name));
    }

    @Override
    public int getItemsAmount(MenuItem item) {
        return getItemsQuantity((t) -> t.equals(item));
    }

    @Override
    public String[] getNoCopiesNames() {
        String[] uniqNames = new String[size];
        int addCounter = 0;
        for (int i = 0; i < size; i++) {
            if (!isItemInArr(uniqNames, items[i].getName())) {
                uniqNames[addCounter] = this.items[i].getName();
                ++addCounter;
            }
        }
        if (addCounter != uniqNames.length) {
            String[] arrToReturn = new String[addCounter];
            System.arraycopy(uniqNames, 0, arrToReturn, 0, addCounter);
            return arrToReturn;
        }
        return uniqNames;
    }

    private boolean isItemInArr(String[] arr, String name) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public MenuItem[] getSortMenuByPrice() {
        MenuItem[] sortedItems = new MenuItem[items.length];
        System.arraycopy(items, 0, sortedItems, 0, items.length);
        Comparator<MenuItem> comparator = new Comparator<MenuItem>() {
            @Override
            public int compare(MenuItem o1, MenuItem o2) {
                return o2.compareTo(o1);
            }
        };
        // MenuItem::compareTo
        Arrays.sort(sortedItems, comparator);
        return sortedItems;
    }

    @Override
    public String toString() {
        StringBuilder strBuild = new StringBuilder("TableOrder: ");
        strBuild.append(size);
        for (int i = 0; i < size; i++) {
            strBuild.append("\n");
            strBuild.append(items[i].toString());
        }
        return strBuild.toString();
    }

    @Override
    public boolean equals(Object obj) {    // todo with getAmountOfItems   / resolved
        if (obj == null || !(obj instanceof TableOrder)) {
            return false;
        }
        TableOrder objTableOrd = (TableOrder) obj;
        if (!creationTime.equals(objTableOrd.creationTime)) {
            return false;
        }
        if (!customer.equals(objTableOrd.customer) || size != objTableOrd.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (getItemsAmount(this.items[i]) != objTableOrd.getItemsAmount(this.items[i])) {
                return false;
            }
        }
        return true;
    }

    // list methods

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof TableOrder)) { throw new ClassCastException(); }
        if (o == null) { throw new NullPointerException(); }
        for (MenuItem e : items) {
            if (e.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<MenuItem> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] objArr = new Object[items.length];
        System.arraycopy(items, 0, objArr, 0, items.length);
        return objArr;
    }

    @Override
    public <T> T[] toArray(T[] a) {     // todo test
        if (a.length < items.length)
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), items.length);
        int i = 0;
        Object[] result = a;
        for (MenuItem e : items)
            result[i++] = e;
        if (a.length > items.length)
            a[items.length] = null;
        return a;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof MenuItem)) { throw new ClassCastException(); }
        if (o == null) { throw new NullPointerException(); }
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) { remove(i); }
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) { throw new NullPointerException("specified collection in null"); }
        try {
            Iterator iterator = c.iterator();
            while (iterator.hasNext()) {
                if (!this.contains(iterator.next())) { return false; }
            }
        } catch (ClassCastException exp) {
            throw exp;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends MenuItem> c) {
        if (c == null) { throw new NullPointerException(); }
        Iterator iterator = c.iterator();
        while (items.length - size < c.size()) { incMenuSize(); }
        while (iterator.hasNext()) {
            add((MenuItem)iterator.next());
        }
        return true;
    }

    // todo test
    @Override
    public boolean addAll(int index, Collection<? extends MenuItem> c) {
        if (c == null) { throw new NullPointerException(); }
        if (index < 0 || index > size) { throw new IndexOutOfBoundsException(); }
        while (items.length-size < c.size()) { incMenuSize(); }
        System.arraycopy(items,index,items,index+c.size(),c.size());
        System.arraycopy(c.toArray(), 0,items,index,c.size());
        size += c.size();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) { throw new NullPointerException(); }
        boolean isRemovedAll = true;
        Iterator iterator = c.iterator();
        while (iterator.hasNext()) {
            if (!remove(iterator.next())) {
                isRemovedAll = false;
            }
        }
        return isRemovedAll;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) { throw new NullPointerException(); }
        boolean isChanged = false;
        try {
            for (int i = 0; i < size; i++) {
                if (!c.contains(items[i])) {
                    remove(i);
                    isChanged = true;
                }
            }
        } catch (ClassCastException exp) {
            throw exp;
        }
        return isChanged;
    }

    @Override
    public void clear() {
        for (int i = 0; i < items.length; i++) { items[i] = null; }
        customer = null;
        creationTime = null;
    }

    @Override
    public MenuItem get(int index) {
        return items[index];
    }

    @Override
    public MenuItem set(int index, MenuItem element) {
        if (index < 0 || index >= items.length) { throw new IndexOutOfBoundsException(); }
        MenuItem oldItem = items[index];
        items[index] = element;
        return oldItem;
    }

    @Override
    public void add(int index, MenuItem element) {
        if (index < 0 || index > size) { throw new IndexOutOfBoundsException(); }
        if (element == null) { throw new NullPointerException(); }
        if (items.length - size < 1) { incMenuSize(); }
        System.arraycopy(items,index,items,index+1,(items.length-index)-items.length-size);
        items[index] = element;
    }

    @Override
    public MenuItem remove(int index) {
        if (index < 0 || index > size) { throw new IndexOutOfBoundsException(); }
        MenuItem deletedItem = items[index];
        items[index] = null;
        removeHole(index);
        return deletedItem;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) { throw new NullPointerException(); }
        if (!(o instanceof MenuItem)) { throw new ClassCastException(); }
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) { throw new NullPointerException(); }
        if (!(o instanceof MenuItem)) { throw new ClassCastException(); }
        int lastId = -1;
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                lastId = i;
            }
        }
        return lastId;
    }

    @Override
    public ListIterator<MenuItem> listIterator() {
        return new ListIterator<MenuItem>() {
            int currentId = 0;
            MenuItem lastReturned;

            @Override
            public boolean hasNext() {
                return currentId < size && size != 0;
            }

            @Override
            public MenuItem next() {
                lastReturned = items[currentId++];
                return lastReturned;
            }       // todo id++ or ++id ???

            @Override
            public boolean hasPrevious() {
                return currentId > 0;
            }

            @Override
            public MenuItem previous() {
                lastReturned = items[currentId--];
                return lastReturned;
            }

            @Override
            public int nextIndex() {
                return currentId + 1;
            }

            @Override
            public int previousIndex() {
                return currentId - 1;
            }

            @Override
            public void remove() {
                items[currentId] = null;
                removeHole(currentId);
            }

            @Override
            public void set(MenuItem menuItem) {
                items[currentId] = menuItem;
            }

            @Override
            public void add(MenuItem menuItem) {
                if (items.length - size < 1) { incMenuSize(); }
                System.arraycopy(items,currentId,items,currentId+1,(items.length-currentId)-items.length-size);
                items[currentId] = menuItem;
            }
        };
    }

    @Override
    public ListIterator<MenuItem> listIterator(int index) {
        if (index < 0) { throw new IndexOutOfBoundsException(); }
        ListIterator iterator = listIterator();
        for (int i = 0; i < index; i++) { iterator.next(); }
        return iterator;
    }

    @Override
    public List<MenuItem> subList(int fromIndex, int toIndex) {
        if (fromIndex == toIndex) { return new ArrayList<>(); }
        if (fromIndex < 0 || toIndex < 0 || toIndex > size) { throw new IndexOutOfBoundsException(); }
        LinkedList<MenuItem> sublist = new LinkedList<>();
        for (; fromIndex < toIndex; fromIndex++) {
            sublist.add(items[fromIndex]);
        }
        return sublist;
    }
}
