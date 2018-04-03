package lab5.barBossHouse;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public interface Order extends List<MenuItem> {

    boolean add(MenuItem item);

    boolean removeItem(MenuItem item);

    boolean removeItem(String itemName);

    int removeAllByName(String name);

    int removeAllByObj(MenuItem item);

    int getAmountOfItems();

    MenuItem[] getItemsArr();

    double getOrderPrice();

    int getItemsAmount(String name);

    int getItemsAmount(MenuItem item);

    String[] getNoCopiesNames();

    MenuItem[] getSortMenuByPrice() throws Exception;

    Customer getCustomer();

    void setCustomer(Customer customer);

    void setOrdCreationTime(int year, Month month, int day, int hour, int minutes);

    LocalDateTime getCreationTime();

    String toString();

    int hashCode();

    boolean equals(Object obj);

    // Order clone ();

}
