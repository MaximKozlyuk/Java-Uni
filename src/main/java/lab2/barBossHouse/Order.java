package lab2.barBossHouse;

import java.util.Arrays;

public class Order {
    //todo: если константа не используется нигде. кроме этого класса, то она должна быть приватным. И средаоб этом подсказывает) /resolved
    private static final int DEFAULT_CAP = 16;
    private Dish[] dishes;
    private int size;

    public Order() {
        this(DEFAULT_CAP);
    }

    public Order(int amountOfDishes) {
        this.size = amountOfDishes;
        this.dishes = new Dish[amountOfDishes];
    }

    public Order(Dish[] dishes) {
        this.dishes = dishes;
    }

    //todo: именование параметра дерьмо /resolved
    public boolean addDish(Dish item) {
        if (size == dishes.length) {
            mAkE_ArRay_GreAtEr_AgAiN();
        }
        dishes[size] = item;
        ++size;
        return true;
    }

    //todo: где декремент поля?
    public boolean removeDish(String dishName) {
        for (int i = 0; i < size; ++i) {
            if (dishes[i].getName().equals(dishName)) {
                dishes[i] = null;
                //todo: именование метода дерьмо)
                annihilateSpaces(i);
                return true;
            }
        }
        return false;
    }

    //todo:  опять не следишь за декрементом  / внутри анагилирования пространства декремент
    public int removeAllDishes(String dishName) {
        int returnCounter = 0;
        for (int i = 0; i < size; ++i) {
            if (dishes[i].getName().equals(dishName)) {
                dishes[i] = null;
                annihilateSpaces(i);
                ++returnCounter;
            }
        }
        return returnCounter;
    }
    //todo: именование править / жду предложений названия пизже этого (в лаб 5 назвал removeHole)
    private void mAkE_ArRay_GreAtEr_AgAiN() {
        size *= 2; //todo: не не, поле должно хранить количество дишей а не длину массива, иначе нахер оно не сдалось
        Dish[] dishesBuff = dishes; //todo: ненужную работу выполняешь, делай сразу новый большой массив и копируй в него всё
        dishes = new Dish[size];
        System.arraycopy(dishesBuff, 0, dishes, 0, dishesBuff.length);
    }
    //todo: именование править, имя параметра править
    private void annihilateSpaces(int removedEl) {
        int i;
        --size;
        for (i = removedEl; dishes[i + 1] != null && i < dishes.length - 1; ++i) {
            dishes[i] = dishes[i + 1];
        }
        this.dishes[i] = null;
    }

    public int getAmountOfAllDishes() {
        int counter = 0;
        for (int i = 0; i < dishes.length; i++) { //todo: не ходи до конца массива, тебе достаточно ходить до последнего диша
            if (dishes[i] != null) { //todo: тогда не придется делать эту проверку
                ++counter;
            }
        }
        return counter;
    }

    public Dish[] getDishArray() {
        Dish[] arr = new Dish[size];
        System.arraycopy(dishes, 0, arr, 0, size);
        return arr;
    }

    public double getOrderPrice() {
        double price = 0.0D;
        for (int i = 0; i < size; i++) { //todo: уже был коммент, исправь этот косяк везде
            price += dishes[i].getPrice();
        }
        return price;
    }

    public int getDishAmount(String dishName) {
        int counter = 0;
        for (int i = 0; i < size; i++) {//todo:
            if (dishes[i].getName().equals(dishName)) {//todo:
                ++counter;
            }
        }
        return counter;
    }

    public String[] getDishNames() {
        //todo:  имена переменных прям дно
        String[] uniqNames = new String[dishes.length];
        int counter = 0;
        for (int i = 0; i < size; i++) { //todo:
            if (dishes[i] != null && !isContains(uniqNames, dishes[i].getName())) {
                uniqNames[counter] = dishes[i].getName();
                ++counter;
            }
        }
        if (counter != uniqNames.length) {
            String[] result = new String[counter];
            System.arraycopy(uniqNames, 0, result, 0, counter);
            return result;
        }
        return uniqNames;
    }
    //todo: переименовать
    private boolean isContains(String[] arr, String name) {
        for (int i = 0; i < size; i++) {//todo:
            if (arr[i].equals(name)) {//todo:
                return true;
            }
        }
        return false;
    }

    // когда-то давно ты просил мердж сортировку
    public  Dish[] getSortedOrder () {
        Dish[] sortedMenu = new Dish[size];
        System.arraycopy(dishes,0,sortedMenu,0,size);
        return this.mergeSort(sortedMenu);
    }

    private static Dish[] mergeSort(Dish[] array1) {
        Dish[] buffer1 = Arrays.copyOf(array1, array1.length);
        Dish[] buffer2 = new Dish[array1.length];
        Dish[] result = mergesortInner(buffer1, buffer2, 0, array1.length);
        return result;
    }

    private static Dish[] mergesortInner(Dish[] buff1, Dish[] buff2, int startId, int endId) {
        if (startId >= endId - 1) {
            return buff1;
        }
        int middle = startId + (endId - startId) / 2;
        Dish[] sorted1 = mergesortInner(buff1, buff2, startId, middle);
        Dish[] sorted2 = mergesortInner(buff1, buff2, middle, endId);
        int index1 = startId;
        int index2 = middle;
        int destIndex = startId;
        Dish[] result = sorted1 == buff1 ? buff2 : buff1;
        while (index1 < middle && index2 < endId) {
            result[destIndex++] = sorted1[index1].getPrice() > sorted2[index2].getPrice() ? sorted1[index1++] : sorted2[index2++];
        }
        while (index1 < middle) {
            result[destIndex++] = sorted1[index1++];
        }
        while (index2 < endId) {
            result[destIndex++] = sorted2[index2++];
        }
        return result;
    }
}
