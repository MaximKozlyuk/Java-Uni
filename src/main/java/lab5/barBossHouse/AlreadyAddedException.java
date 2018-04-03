package lab5.barBossHouse;

public class AlreadyAddedException extends RuntimeException {

    public AlreadyAddedException() {
        super();
    }

    public AlreadyAddedException(String message) {
        super(message);
    }

}
