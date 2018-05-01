package lab5.barBossHouse.io;

import lab5.barBossHouse.Order;

public  class OrderManagerSerializedFileSource extends OrderManagerFileSource {

    /*
    для записи и чтения используется механизм
    сериализации. Для этого все классы должны быть подготовлены к сериализации.
    Имя файла, содержащего информацию о заказе определяется из объекта заказа – время в миллисекундах.
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
