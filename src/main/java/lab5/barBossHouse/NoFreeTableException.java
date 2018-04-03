package lab5.barBossHouse;

public class NoFreeTableException extends RuntimeException {

    public NoFreeTableException(String message) {
        super(message);
    }

    public NoFreeTableException() {
        super();
    }

}
