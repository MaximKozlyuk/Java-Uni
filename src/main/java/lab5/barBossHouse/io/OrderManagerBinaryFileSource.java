package lab5.barBossHouse.io;

import lab5.barBossHouse.Order;

public class OrderManagerBinaryFileSource extends OrderManagerFileSource {

    /*
    для записи и чтения данных из файла использует байтовые потоки.
    Для записи рекомендуется использовать DataOutputStream,
    а для чтения – DataInputStream, соответсвенно.
    Порядок записи информации о заказе студент определяет самостоятельно.
    Например, можно использовать следующий порядок:
        <Тип заказа>
        <Клиент>
        <Адрес>
        <Число позиций в заказе>
        <Тип имя стоимость позиции> <тип напитка> <%алкоголя> <описание>
        <Тип имя стоимость позиции> <тип напитка> <%алкоголя> <описание>
        <Тип имя стоимость позиции> <тип напитка> <%алкоголя> <описание> ...
    Для получения информации о содержимом каталога можно использовать класс File.
     */

    @Override
    public void load(Order menuItems) {

    }

    @Override
    public void store(Order menuItems) {

    }

    @Override
    public void delete(Order menuItems) {

    }

    @Override
    public void create(Order menuItems) {

    }
}
