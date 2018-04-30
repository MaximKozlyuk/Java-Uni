package lab5.barBossHouse;

import lab5.barBossHouse.tests.Tests;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Tests labTest = new Tests();

        InternetOrder ord1 = new InternetOrder(labTest.items, labTest.customer);
        InternetOrder ord2 = new InternetOrder(labTest.items, labTest.customer);
        InternetOrder ord3 = new InternetOrder(labTest.items, labTest.customer);
        InternetOrder ord4 = new InternetOrder(labTest.items, labTest.customer);
        InternetOrder ord5 = new InternetOrder(labTest.items, labTest.customer);
        ord1.setOrdCreationTime(1991, Month.AUGUST,20,12,1);
        ord2.setOrdCreationTime(1991, Month.AUGUST,20,12,2);
        ord3.setOrdCreationTime(1991, Month.AUGUST,20,12,3);
        ord4.setOrdCreationTime(1991, Month.AUGUST,20,12,0);
        ord5.setOrdCreationTime(1991, Month.AUGUST,20,12,0);

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
    }
}