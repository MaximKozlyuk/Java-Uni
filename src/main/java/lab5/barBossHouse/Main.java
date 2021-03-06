package lab5.barBossHouse;

import lab5.barBossHouse.io.ControlledTableOrder;
import lab5.barBossHouse.io.OrderManagerTextFileSource;
import lab5.barBossHouse.tests.Tests;

import java.time.LocalDateTime;
import java.time.Month;

public class Main {

    public static void main(String[] args) {

        System.out.println(System.nanoTime());

        Tests labTest = new Tests();

        ControlledTableOrder cto = new ControlledTableOrder(labTest.tableOrder);

        InternetOrder ord1 = new InternetOrder(labTest.items, labTest.customer);
        InternetOrder ord2 = new InternetOrder(labTest.items, labTest.customer);
        InternetOrder ord3 = new InternetOrder(labTest.items, labTest.customer);
        InternetOrder ord4 = new InternetOrder(labTest.items, labTest.customer);
        InternetOrder ord5 = new InternetOrder(labTest.items, labTest.customer);
        ord1.setCreationTime(1991, Month.AUGUST,20,12,1,30,1);
        ord2.setCreationTime(1991, Month.AUGUST,20,12,1,30,2);
        ord3.setCreationTime(1991, Month.AUGUST,20,12,3,0,0);
        ord4.setCreationTime(1991, Month.AUGUST,20,12,0,0,0);
        ord5.setCreationTime(1991, Month.AUGUST,20,12,0,0,0);

        InternetOrdersManager manager = new InternetOrdersManager();

        manager.addLast(ord1);
        manager.addLast(ord2);
        manager.addLast(ord3);
        manager.addLast(ord4);
        manager.addLast(ord5);

        System.out.println(
                manager.getOrdersAtDay(LocalDateTime.of(
                        1991,Month.AUGUST,20,12,0
                    )
                )
        );


        OrderManagerTextFileSource omtfs = new OrderManagerTextFileSource();
        //omtfs.create(ord3);
        //omtfs.create(ord2);
        omtfs.delete(ord2);
        omtfs.delete(ord3);
    }
}