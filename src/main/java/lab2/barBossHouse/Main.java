package lab2.barBossHouse;

public class Main {
    public static void main(String[] args) {

        Dish burger = new Dish("Burger", "Your stomach will suffer", 2.99D);
        Dish cola = new Dish("Co-Co-Cola", "You will die coz of sugar overdose", 0.99D);
        Dish shrimpRoll = new Dish("Shrimp roll", "expensive shaurma", 3.99D);
        Order ord1 = new Order(1);
        ord1.addDish(shrimpRoll);

        for (int i = 0; i < 3; ++i) {
            ord1.addDish(burger);
            ord1.addDish(cola);
            ord1.addDish(shrimpRoll);
        }
        // order test

        System.out.println(ord1.getOrderPrice());
        String[] dishTittles = ord1.getDishNames();
        System.out.println("\nDish names:");
        for (int i = 0; i < dishTittles.length; i++) {
            System.out.println(dishTittles[i]);
        }
        Dish[] sortedOrd = ord1.getSortedOrder();

        System.out.println("\nSorted order:");
        for (int i = 0; i < sortedOrd.length; i++) {
            System.out.println(sortedOrd[i]);
        }

        // order manager test
        System.out.println("\n");
        OrderManager manage = new OrderManager(10);
        manage.addOrder(manage.getFreeTableID(), ord1);
        System.out.println(manage.getAmountOfDish(shrimpRoll.getName()));

    }
}