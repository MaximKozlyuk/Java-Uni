package lab5.barBossHouse;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

public class InternetOrder implements Order {

    private Customer customer;
    private LabList<MenuItem> menu;

    private LocalDateTime creationTime;

    public InternetOrder() {
        menu = new LabList<MenuItem>();
        customer = new Customer();
        creationTime = LocalDateTime.now();
    }

    public InternetOrder(MenuItem[] menu, Customer customer) {
        this.menu = new LabList<MenuItem>(menu);
        this.customer = customer.clone();
        creationTime = LocalDateTime.now();
    }

    public void setOrdCreationTime(int year, Month month, int day, int hour, int minutes) {
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
        menu.addEl(item);
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
        LabList<String> uniqNames = new LabList<String>();
        String[] menuCopy = new String[menu.getSize()];
        for (int i = 0; i < menuCopy.length; i++) {
            menuCopy[i] = menu.getEl(i).getName();
        }
        for (int i = 0; i < menuCopy.length; i++) {
            for (int j = i; j < menuCopy.length; j++) {
                if (menuCopy[j] != null && !uniqNames.contains(menuCopy[j])) {
                    uniqNames.addEl(menuCopy[j]);
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
        try {
            tbOrdForSort = new TableOrder(sortedMenu, Customer.MATURE_UNKNOWN_CUSTOMER);
        } catch (UnlawfulActionException exp) {
            throw new UnlawfulActionException("birth date from future");
        }
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

    // list methods

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
        return false;
    }

    @Override
    public Iterator<MenuItem> iterator() {
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

    //@Override
    //public boolean add(MenuItem menuItem) {return false;}

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends MenuItem> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends MenuItem> c) {
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

    @Override
    public MenuItem get(int index) {
        return null;
    }

    @Override
    public MenuItem set(int index, MenuItem element) {
        return null;
    }

    @Override
    public void add(int index, MenuItem element) {

    }

    @Override
    public MenuItem remove(int index) {
        return null;
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
    public ListIterator<MenuItem> listIterator() {
        return null;
    }

    @Override
    public ListIterator<MenuItem> listIterator(int index) {
        return null;
    }

    @Override
    public List<MenuItem> subList(int fromIndex, int toIndex) {
        return null;
    }
}
