package lab5.barBossHouse;

public abstract class MenuItem implements Comparable<MenuItem> {

    private static final double DEFAULT_PRICE = 0.0D;
    private final double price;
    private final String title;
    private final String description;

    protected MenuItem(String title, String description) {
        this(title, description, DEFAULT_PRICE);
    }

    protected MenuItem(String title, String description, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("negative price");
        }
        this.price = price;
        this.title = title;
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        String str = String.format("%s, %.2fр.", title, price);
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            return false;
        }
        if (obj instanceof MenuItem) {
            return false;
        }
        MenuItem temp = (MenuItem) obj;
        return Double.compare(price, temp.price) == 0 && temp.title.equals(title);
    }

    @Override
    public int hashCode() {
        return title.hashCode() ^
                Double.hashCode(price) ^
                description.hashCode();
    }

    @Override
    public int compareTo(MenuItem o) {
        if (o.getPrice() > price) {
            return -1;
        } else {
            if (o.getPrice() < price) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
