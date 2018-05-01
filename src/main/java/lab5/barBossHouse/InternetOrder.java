package lab5.barBossHouse;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

public class InternetOrder implements Order, Iterable<MenuItem> {

    private Customer customer;
    private MyList<MenuItem> menu;
    private LocalDateTime creationTime;

    public InternetOrder() {
        menu = new MyList<MenuItem>();
        customer = new Customer();
        creationTime = LocalDateTime.now();
    }

    public InternetOrder(MenuItem[] menu, Customer customer) {
        this.menu = new MyList<MenuItem>(menu);
        this.customer = customer.clone();
        creationTime = LocalDateTime.now();
    }

    public void setCreationTime(int year, Month month, int day, int hour, int minutes) {
        creationTime = LocalDateTime.of(year, month, day, hour, minutes);
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
    public boolean add(MenuItem item) /* throws UnlawfulActionException */ {  // todo exp
        if (item instanceof Drink && ((Drink) item).isAlcoholable()) {
            if (customer.getAge() < Customer.AGE_OF_MAJORITY ||
                    customer == Customer.UNDERAGED_UNKNOWN_CUSTOMER ||
                    LocalDateTime.now().getHour() >= 22) {
                //throw new UnlawfulActionException("underaged person cant buy alchogol");
            }
        }
        menu.add(item);
        return true;
    }

    private boolean remove(Predicate<MenuItem> predicate) {
        for (int i = 0; i < menu.getSize(); i++) {
            if (predicate.test(menu.getEl(i))) {
                menu.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(String name) {       // todo with predicate /resolved
        return remove((t) -> t.getName().equals(name));
    }

    @Override
    public boolean removeItem(MenuItem item) {
        return remove((t) -> t.equals(item));
    }

    private int removeAllBy(Predicate<MenuItem> predicate) {
        int counter = 0;
        for (int i = 0; i < menu.getSize(); i++) {
            if (predicate.test(menu.getEl(i))) {
                ++counter;
                menu.remove(i);
            }
        }
        return counter;
    }

    @Override
    public int removeAllByName(String name) {  // todo also func interface /resolved
        return removeAllBy((t) -> t.getName().equals(name));
    }

    @Override
    public int removeAllByObj(MenuItem item) {
        return removeAllBy((t) -> t.equals(item));
    }

    @Override
    public int getAmountOfItems() {
        return menu.getSize();
    }

    @Override
    public MenuItem[] getItemsArr() {
        MenuItem[] arrToReturn = new MenuItem[menu.getSize() + 1];
        for (int i = 0; i < menu.getSize(); i++) {
            arrToReturn[i] = menu.getEl(i);
        }
        return arrToReturn;
    }

    @Override
    public double getOrderPrice() {
        double price = 0;
        for (int i = 0; i < menu.getSize(); i++) {
            price += menu.getEl(i).getPrice();
        }
        return price;
    }

    private int getItemsQuantity(Predicate<MenuItem> predicate) {
        int counter = 0;
        for (int i = 0; i < menu.getSize(); i++) {
            if (predicate.test(menu.getEl(i))) {
                ++counter;
            }
        }
        return counter;
    }

    @Override
    public int getItemsAmount(String name) {   // todo func interface  /resolved
        return getItemsQuantity((t) -> t.getName().equals(name));
    }

    @Override
    public int getItemsAmount(MenuItem item) {
        return getItemsQuantity((t) -> t.equals(item));
    }

    @Override
    public String[] getNoCopiesNames() { // возвращаем массив названийц блюд без повторов
        MyList<String> uniqNames = new MyList<String>();
        String[] menuCopy = new String[menu.getSize()];
        for (int i = 0; i < menuCopy.length; i++) {
            menuCopy[i] = menu.getEl(i).getName();
        }
        for (int i = 0; i < menuCopy.length; i++) {
            for (int j = i; j < menuCopy.length; j++) {
                if (menuCopy[j] != null && !uniqNames.contains(menuCopy[j])) {
                    uniqNames.add(menuCopy[j]);
                    menuCopy[j] = null;
                }
            }
        }
        String[] arrToRet = new String[uniqNames.getSize()];
        for (int i = 0; i < arrToRet.length; i++) {
            arrToRet[i] = uniqNames.getEl(i);
        }
        return arrToRet;
    }

    @Override
    public MenuItem[] getSortMenuByPrice() throws Exception {
        MenuItem[] sortedMenu = new MenuItem[menu.getSize()];
        for (int i = 0; i < sortedMenu.length; i++) {
            sortedMenu[i] = menu.getEl(i);
        }
        // todo what todo ?
        TableOrder tbOrdForSort;
      //  try {
            tbOrdForSort = new TableOrder(sortedMenu, Customer.MATURE_UNKNOWN_CUSTOMER);
      //  } catch (UnlawfulActionException exp) {
      //      throw new UnlawfulActionException("birth date from future");
     //   }
        return tbOrdForSort.getSortMenuByPrice();
    }

    @Override
    public String toString() {
        StringBuilder strBuild = new StringBuilder(customer.toString());
        strBuild.append("\n");
        strBuild.append(menu.getSize());
        strBuild.append("\n");
        for (int i = 0; i < menu.getSize(); i++) {
            strBuild.append(menu.getEl(i).toString());
            strBuild.append("\n");
        }
        return strBuild.toString();
    }

    @Override
    public int hashCode() {
        return customer.hashCode() ^ menu.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof InternetOrder)) {
            return false;
        }
        InternetOrder objOrd = (InternetOrder) obj;
        if (!creationTime.equals(objOrd.creationTime)) {
            return false;
        }
        if (!customer.equals(objOrd.customer) || menu.getSize() != (objOrd).menu.getSize()) {
            return false;
        }
        for (int i = 0; i < menu.getSize(); i++) {
            if (getItemsAmount(this.menu.getEl(i)) != objOrd.getItemsAmount(this.menu.getEl(i))) {
                return false;
            }
        }
        return true;
    }

    // list methods, lab 5

    @Override
    public int size() {
        return menu.getSize();
    }

    @Override
    public boolean isEmpty() {
        return menu.getSize() == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof InternetOrder)) { throw new ClassCastException(); }
        Iterator it = this.iterator();
        while (it.hasNext()) {
            if (it.next().equals(o)) { return true; }
        }
        return false;
    }

    @Override
    public Iterator<MenuItem> iterator() {
        return new Iterator<MenuItem>() {

            private int currentId = 0;

            @Override
            public boolean hasNext() {
                return currentId < menu.getSize() && menu.getSize() != 0;
            }

            @Override
            public MenuItem next() {
                return menu.getEl(currentId++);
            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return menu.toArray(a);
    }

    @Override
    public boolean remove(Object o) { // todo test  / is cast necessary?
       if ((!(o instanceof MenuItem)) ||  o == null)  { return false; }
       for (int i = 0; i < menu.getSize(); i++) {
           if (menu.getEl(i).equals(o)) {
               menu.remove(i);
               return true;
           }
       }
       return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (menu.getSize() == 0 ) { return false; }
        if (c == null) { throw new NullPointerException("specified collection in null"); }
        if (c == menu) { return true; }
        try {
            Iterator cIt = c.iterator();
            while (cIt.hasNext()) {
                if (!(menu.contains((MenuItem)cIt.next()))) { return false; }
            }
        } catch (ClassCastException exp) {
            throw exp;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends MenuItem> c) {
        if (c == null) { throw new NullPointerException("specified collection in null"); }
        Iterator cIt = c.iterator();
        try {
            while (cIt.hasNext()) {
                this.add((MenuItem) cIt.next());
            }
        } catch (ClassCastException exp) {
            throw exp;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends MenuItem> c) {
        if (index > menu.getSize() || index < 0) { throw new IndexOutOfBoundsException("Wrong index"); }
        if (c == null) { throw new NullPointerException("specified collection in null"); }
        Iterator cIt = c.iterator();
        while (cIt.hasNext()) {
            menu.add(index++, (MenuItem)cIt.next());
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) { throw new NullPointerException("specified collection in null"); }
        boolean isChanged = false;
        Iterator cIt = c.iterator();
        Object currentObj;
        while (cIt.hasNext()) {
            currentObj = cIt.next();
            for (int i = 0; i < menu.getSize(); i++) {
                if (currentObj.equals(menu.getEl(i))) {
                    menu.remove(i);
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) { throw new NullPointerException("specified collection in null"); }
        boolean isChanged = false;
        for (int i = 0; i < menu.getSize(); i++) {
            if (!c.contains(menu.getEl(i))) {
                menu.remove(i);
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public void clear() {
        for (int i = 0; i < menu.getSize(); i++) {
            menu.remove(i);
        }
        customer = null;
        creationTime = null;
    }

    @Override
    public MenuItem get(int index) {
        if (index < 0 || index > menu.getSize()) { throw  new IllegalArgumentException("too big or negative"); }
        return menu.getEl(index);
    }

    @Override
    public MenuItem set(int index, MenuItem element) {
        if (index < 0 || index > menu.getSize()) { throw  new IllegalArgumentException("too big or negative"); }
        return menu.set(index, element);
    }

    // todo addLast and shifts to right all elements
    @Override
    public void add(int index, MenuItem element) {
        if (index < 0 || index > menu.getSize()) { throw  new IllegalArgumentException("too big or negative"); }
        //menu.addLast();
    }

    @Override
    public MenuItem remove(int index) {
        if (index < 0 || index > menu.getSize()) { throw  new IllegalArgumentException("too big or negative"); }
        MenuItem removedItem = menu.getEl(index);
        menu.remove(index);
        return removedItem;
}

    @Override
    public int indexOf(Object o) {
        if (!(o instanceof InternetOrder)) { return -1; }
        for (int i = 0; i < menu.getSize(); i++) {
            if (menu.getEl(i).equals(o)) { return i; }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (!(o instanceof InternetOrder)) { return -1; }
        int lastId = -1;
        for (int i = 0; i < menu.getSize(); i++) {
            if (menu.getEl(i).equals(o)) { lastId = i; }
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
                return currentId < menu.getSize() && menu.getSize() != 0;
            }

            @Override
            public MenuItem next() {
                lastReturned = menu.getEl(currentId++);
                return lastReturned;
            }       // todo id++ or ++id ???

            @Override
            public boolean hasPrevious() {
                return currentId > 0;
            }

            @Override
            public MenuItem previous() {
                lastReturned = menu.getEl(currentId--);
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
                for (int i = 0; i < menu.getSize(); i++) {
                    if (lastReturned.equals(menu.getEl(i))) {
                        menu.remove(i);
                    }
                }
            }

            @Override
            public void set(MenuItem menuItem) {
                for (int i = 0; i < menu.getSize(); i++) {
                    if (lastReturned.equals(menu.getEl(i))) {
                        menu.set(i,menuItem);
                    }
                }
            }

            @Override
            public void add(MenuItem menuItem) {
                menu.add(menuItem);
            }
        };
    }

    @Override
    public ListIterator<MenuItem> listIterator(int index) {
        ListIterator iterator = listIterator();
        for (int i = 0; i < index; i++) { iterator.next(); }
        return iterator;
    }

    // можно конечно в своем листе заимплементить list и возращать его но чет лень
    @Override
    public List<MenuItem> subList(int fromIndex, int toIndex) {
        if (fromIndex == toIndex) { return new ArrayList<>(); }
        if (fromIndex < 0 || toIndex < 0 || toIndex > menu.getSize()) { throw new IndexOutOfBoundsException(); }
        LinkedList<MenuItem> sublist = new LinkedList<>();
        for (; fromIndex < toIndex; fromIndex++) {
            sublist.add(menu.getEl(fromIndex));
        }
        return sublist;
    }
}
