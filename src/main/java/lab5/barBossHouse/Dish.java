package lab5.barBossHouse;

public final class Dish extends MenuItem {

    public Dish(String title, String description) {
        super(title, description);
    }

    public Dish(String title, String description, double price) {
        super(title, description, price);
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    public String getTitle() {
        return super.getName();
    }

    public String getDescriprion() {
        return super.getDescription();
    }

    @Override
    public String toString() {
        return "Dish: " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof Dish;
    }

    @Override
    public int hashCode() {
        return super.getName().hashCode() ^
                Double.valueOf(super.getPrice()).hashCode() ^
                super.getDescription().hashCode();
    }

}
