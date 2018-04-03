package lab2.barBossHouse;

public class OrderManager {

    public Order[] orders;

    public OrderManager(int tablesAmount) {
        this.orders = new Order[tablesAmount];
    }

    public void addOrder(int tableNum, Order ord) {
        this.orders[tableNum] = ord;
    }

    public Order getOrger(int tableNum) {
        return this.orders[tableNum];
    }

    public void addDishToOrd(int tableNum, Dish dish) {
        this.orders[tableNum].addDish(dish);
    }

    public void removeOrder(int tableNum) {
        this.orders[tableNum] = null;
    }

    public int getFreeTableID() {
        for (int i = 0; i < orders.length; ++i) {
            if (orders[i] == null) {
                return i;
            }
        }
        return -1;
    }
    //todo: следующие два метода перепиши на предикат / переписано в lab 5
    public int[] getAllFreeID() {
        int counter = 0;
        for (int i = 0; i < orders.length; ++i) {
            if (orders[i] == null) {
                ++counter;
            }
        }
        if (counter == 0) {
            return null;
        } else {
            int[] ids = new int[counter];
            counter = 0;
            for (int i = 0; i < orders.length; ++i) {
                if (orders[i] == null) {
                    ids[counter] = i;
                    ++counter;
                }
            }
            return ids;
        }
    }

    public int[] getAllBusyID() {
        int counter = 0;
        for (int i = 0; i < orders.length; ++i) {
            if (orders[i] != null) {
                ++counter;
            }
        }
        if (counter == 0) {
            return null;
        } else {
            int[] ids = new int[counter];
            counter = 0;
            for (int i = 0; i < orders.length; ++i) {
                if (orders[i] != null) {
                    ids[counter] = i;
                    ++counter;
                }
            }
            return ids;
        }
    }

    public Order[] getOrders() {
        return this.orders;
    }

    public double getSumOrdersPrice() {
        double sumPrice = 0.0D;
        for (int i = 0; i < orders.length; ++i) {
            if (orders[i] != null) {
                sumPrice += orders[i].getOrderPrice();
            }
        }
        return sumPrice;
    }

    public int getAmountOfDish(String dishTittle) {
        int amount = 0;
        for (int i = 0; i < orders.length; ++i) {
            if (orders[i] != null) {
                Dish[] disArray = orders[i].getDishArray();
                for (int j = 0; j < disArray.length; ++j) {
                    if (disArray[j].getName().equals(dishTittle)) {
                        ++amount;
                    }
                }
            }
        }
        return amount;
    }
    //todo: я не уверен, что ты написал все методы по заданию, перепроверь. Вроде их должно быть больше.
}
