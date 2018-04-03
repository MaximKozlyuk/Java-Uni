package lab5.barBossHouse;

public interface MyCollection<E> {

    int getSize();

    boolean contains(E obj);

    void addEl(E e);

    boolean remove(E e);

}
