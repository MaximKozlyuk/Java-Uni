package lab5.barBossHouse;

import java.time.LocalDate;
import java.time.Month;

public final class Customer implements Cloneable {

    public static final int AGE_OF_MAJORITY = 18;
    public static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.of(1, Month.JANUARY, 1);
    public static final Customer UNDERAGED_UNKNOWN_CUSTOMER = new Customer("", "", DEFAULT_BIRTH_DATE, Address.DEFAULT_ADDRESS);
    public static final Customer MATURE_UNKNOWN_CUSTOMER = new Customer("", "", DEFAULT_BIRTH_DATE, Address.DEFAULT_ADDRESS);
    private final String name;
    private final String surname;
    private final LocalDate birthDate;
    private final Address address;

    public Customer() {
        this("", "", DEFAULT_BIRTH_DATE, Address.DEFAULT_ADDRESS);
    }

    public Customer(LocalDate birthDate) {
        this("", "", birthDate, Address.DEFAULT_ADDRESS);
    }

    public Customer(String name, String surname, LocalDate birthDate, Address address) {
        if (birthDate.getYear() > LocalDate.now().getYear() ||
                (birthDate.getYear() == LocalDate.now().getYear() && birthDate.getDayOfYear() > LocalDate.now().getDayOfYear())) {
            throw new IllegalArgumentException("birth date from future");
        }
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        if (LocalDate.now().getMonth().getValue() > birthDate.getMonth().getValue() |
                (LocalDate.now().getMonth().getValue() == birthDate.getMonth().getValue()) &&
                LocalDate.now().getDayOfYear() > birthDate.getDayOfYear()) {
            return LocalDate.now().getYear() - birthDate.getYear();
        } else {
            return LocalDate.now().getYear() - birthDate.getYear() - 1;
        }
    }

    public Address getAddress() {
        return address.clone();
    }

    @Override
    public String toString() {
        StringBuilder strBuild = new StringBuilder("Customer: ");
        if (!(surname.equals(""))) {
            strBuild.append(surname);
            strBuild.append(" ");
        }
        if (!(name.equals(""))) {
            strBuild.append(name);
            strBuild.append(", ");
        }
        if (!birthDate.equals(DEFAULT_BIRTH_DATE)) {
            strBuild.append(birthDate);
        }
        if (!(address.equals(Address.DEFAULT_ADDRESS))) {
            strBuild.append(", ");
            strBuild.append(address.toString());
        }
        return strBuild.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        Customer objCust = (Customer) obj;
        return (objCust.address.equals(address) &&
                objCust.birthDate.equals(birthDate) &&
                objCust.name.equals(name) &&
                objCust.surname.equals(surname));
    }

    @Override
    public int hashCode() {
        return surname.hashCode() ^
                name.hashCode() ^
                birthDate.hashCode() ^
                address.hashCode();
    }

    @Override
    public Customer clone() {
        Customer custClone = new Customer(name, surname, birthDate, address.clone());
        return custClone;
    }

}
