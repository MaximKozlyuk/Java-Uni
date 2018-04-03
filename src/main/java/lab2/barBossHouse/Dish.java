package lab2.barBossHouse;

public class Dish {

    public static final double DEFAULT_PRICE = 0.0D;
    private double price;
    private String name;
    private String description;

    public Dish(String name, String description) {
        this(name, description, DEFAULT_PRICE);
    }

    public Dish(String name, String description, double price) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newTitle) {
        this.name = newTitle;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public String toString() {
        return this.name + "   " + this.description + "   price: " + this.price;
    }
}
