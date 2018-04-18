package lab5.barBossHouse;

import lab5.barBossHouse.tests.Tests;

import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(String[] args) {

        Tests labTest = new Tests();



        Customer customer = new Customer("Maxim", "Kozlyuk", LocalDate.of(1997, Month.DECEMBER, 23), Address.DEFAULT_ADDRESS);
        System.out.println("MY AGE: " + customer.getAge());

        System.out.println("CUST EQ " + customer.equals(customer));

        TableOrder tableOrd1 = labTest.getRandomTableOrder(3, 20);
        TableOrder tableOrd2 = labTest.getRandomTableOrder(3, 20);
        tableOrd1.add(labTest.items[5]);

        TableOrdersManager tableOrdManager = new TableOrdersManager(15);
        TableOrder[] tableOrders = new TableOrder[15];
        for (int i = 0; i < 7; i++) {
            tableOrders[i] = labTest.getRandomTableOrder(2, 10);
            tableOrdManager.add(tableOrdManager.getFreeTableID(), tableOrders[i]);
        }

        // init InternetOrder array
        InternetOrder[] internetOrders = new InternetOrder[7];
        for (int i = 0; i < internetOrders.length; i++) {
            internetOrders[i] = labTest.getRandomInterOrder(3, 20);
        }
        System.out.println();

        MenuItem steak = new Dish("Steak", "Yesterday it was running in a meadow", 750);
        // list test

        LabList<MenuItem> list = new LabList();
        list.addAll(labTest.items);
        System.out.println("TOSTR LIST\n" + list + "\n");
        MenuItem[] arr = list.toArray(new MenuItem[list.getSize()]);
        System.out.println("ARR lIST\n");
        labTest.outArr(arr);

        // lab 4

        System.out.println("INT ORD TEST\n");
        Queue<MenuItem> q1 = new Queue(labTest.items);
        MenuItem[] itemArr = new MenuItem[q1.getCap()];
        for (int i = 0; i < itemArr.length; i++) {
            itemArr[i] = q1.getEl(i);
        }
        System.out.println(q1);
        int counter = 0;
        while (q1.poll() != null) {
            counter++;
            System.out.println("circle number: " + counter);
            System.out.println("Q1 size: " + q1.getCap());
            System.out.println("Q1 cap: " + q1.getSize() + "\n");
        }
        InternetOrdersManager internetOrdManager = new InternetOrdersManager(internetOrders);
        System.out.println("CUST ORDERS: " + internetOrdManager.getCustomerOrders(labTest.customer).length);
        Order[] intOrdsAtDay = internetOrdManager.getOrdersArrAtDay(LocalDate.now());

        System.out.println(intOrdsAtDay.length);

        /*
        Dish: Salad, 300.0p.
        Drink WINE French Vine, 100.22p. Alcholol: 12%. simple dry red wine
         */
        System.out.println(labTest.items[5]);
        System.out.println(labTest.items[0]);
        System.out.println(labTest.customer);

        // exceptions
        System.out.println(Character.isAlphabetic('2'));

        labTest.outArr(labTest.getRandomTableOrder(10, 12).getSortMenuByPrice());

        //labTest.test_TOM();

        System.out.println("\n\nAdd by id test");
        LabList<Integer> list2 = new LabList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(4,7);
        System.out.println(list2);

        System.out.println("Done!");
    }
}