package lab5.barBossHouse;

import lab5.barBossHouse.tests.Tests;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Tests labTest = new Tests();

        Integer d1 = 1;
        Integer d2 = 2;
        Integer d3 = 3;
        Integer d4 = 4;
        Integer d5 = 5;
        Integer d10 = 10;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(d1);
        list.add(d2);
        list.add(d4);
        //list.add(d10);

        MyDeque<Integer> dec = new MyDeque<>(10);
        dec.addLast(d1);
        dec.addLast(d1);
        dec.addLast(d2);
        dec.addLast(d3);
        dec.addLast(d4);
        dec.addLast(d4);



        System.out.println(dec + "\n");

        System.out.println(dec);

        System.out.println("end");
        System.out.println(
                labTest.internetOrdersManager.size()
        );
    }
}