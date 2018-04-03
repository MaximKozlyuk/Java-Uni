package lab5.barBossHouse.tests;

import lab5.barBossHouse.*;

import java.util.Random;

public class Tests {

    public static MenuItem[] items;
    public final Address address = new Address("Samara", 666, "Lenina", 17, "k", 6);
    public final Customer customer = new Customer("Maxim", "Kozlyuk", Customer.DEFAULT_BIRTH_DATE, Address.DEFAULT_ADDRESS);
    public TableOrdersManager tableOrdersManager;
    public InternetOrdersManager internetOrdersManager;

    public Tests() {
        items = new MenuItem[7];
        items[0] = new Drink("French Vine", "simple dry red wine", 100.22, DrinkTypeEnum.WINE, (byte) 12);
        items[1] = new Drink("Italian Vine", "white wine", 100.21, DrinkTypeEnum.WINE, (byte) 10);
        items[2] = new Drink("Jagermaister", "orange deer", 1200, DrinkTypeEnum.JAGERMAISTER, (byte) 35);
        items[3] = new Drink("Coffee", "arabica", 75, DrinkTypeEnum.COFEE);
        items[4] = new Dish("Steak", "Yesterday it was running in a meadow", 750);
        items[5] = new Dish("Salad", "Fresh vegetables", 300);
        items[6] = new Dish("Borscht", "National dish", 500);

        tableOrdersManager = new TableOrdersManager(10);
        for (int i = 0; i < 5; i++) {
            tableOrdersManager.add(tableOrdersManager.getFreeTableID(), getRandomTableOrder(2, 8));
        }

    }

    public MenuItem[] getRandomMenu(int min, int max) {
        Random rand = new Random();
        MenuItem[] menu = new MenuItem[rand.nextInt(max - min) + min];
        for (int i = 0; i < menu.length; i++) {
            menu[i] = items[rand.nextInt(items.length)];
        }
        return menu;
    }

    public void outArr(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].toString());
        }
    }

    public InternetOrder getRandomInterOrder(int min, int max) {
        return new InternetOrder(this.getRandomMenu(min, max), customer);
    }

    public TableOrder getRandomTableOrder(int min, int max) {
        try {
            return new TableOrder(this.getRandomMenu(min, max), customer);
        } catch (Exception exp) {
            return null;
        }
    }

}
