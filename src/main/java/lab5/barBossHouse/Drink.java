package lab5.barBossHouse;

public final class Drink extends MenuItem implements Alcoholable {

    private static final byte DEFAULT_PCT = 0;
    private final DrinkTypeEnum drinkType;
    private final byte alcPct;

    public Drink(String title, DrinkTypeEnum type) {
        this(title, "", 0, type, DEFAULT_PCT);
    }

    public Drink(String title, String description, double price, DrinkTypeEnum type) {
        this(title, description, price, type, DEFAULT_PCT);
    }

    public Drink(String title, String description, double price, DrinkTypeEnum type, byte alcPct) {
        super(title, description, price);
        if (alcPct < 0 || alcPct > 100) {
            throw new IllegalArgumentException("impossible percent of alcohol");
        }
        this.alcPct = alcPct;
        this.drinkType = type;
    }

    @Override
    public boolean isAlcoholable() {
        return this.alcPct > 0;
    }

    @Override
    public int getAlcPct() {
        return this.alcPct;
    }

    public DrinkTypeEnum getDrinkType() {
        return this.drinkType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Drink)) {
            return false;
        }
        Drink drink = (Drink) obj;
        return (drink.alcPct == alcPct) &&
                (drink.drinkType == drinkType) &&
                (drink.getName().equals(this.getName())) &&
                (drink.getPrice() == this.getPrice());
    }

    @Override
    public int hashCode() {
        return this.drinkType.hashCode() ^
                alcPct ^ Double.hashCode(this.getPrice()) ^
                this.getName().hashCode() ^
                this.getDescription().hashCode();
    }

    // “Drink: <type> <name>, <cost>р. Alcholol: <alcoholVol>%. <description>”
    @Override
    public String toString() {     // todo with String.format /resolved
        /*
        StringBuilder str = new StringBuilder("Drink ").append(drinkType);
        str.append(" ").append(super.toString()).append(" Alcholol: ");
        str.append(alcPct).append("%. ").append(super.getDescription());
        */
        String str = String.format("Drink %s %s Alcholol: %d%s", drinkType.toString(), super.toString(),
                alcPct, "%. " + super.getDescription());
        return str;
    }

}
