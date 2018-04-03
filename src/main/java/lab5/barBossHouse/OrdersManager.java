package lab5.barBossHouse;

import java.time.LocalDate;
import java.util.Collection;

public interface OrdersManager extends Collection<Order> {

    int getOrdersAmount();

    Order[] getOrders();

    double getTotalCost();

    int getItemAmount(String name);

    int getItemAmount(MenuItem item);

    int getOrdersAtDay(LocalDate date);

    Order[] getOrdersArrAtDay(LocalDate date);

    Order[] getCustomerOrders(Customer customer);

}
