package lab5.barBossHouse;

public final class Address implements Cloneable {

    public static final Address DEFAULT_ADDRESS = new Address();
    private static final int DEFAULT_POSTCODE = -1;
    private static final int DEFAULT_BUILDINGNUM = -1;
    private static final int DEFAULT_APARTNUM = -1;
    private final String city;
    private final String street;
    private final int postcode;
    private final int buildingNum;
    private final String buildingLetter;
    private final int apartmentNum;

    public Address() {
        this("", DEFAULT_POSTCODE, "", DEFAULT_BUILDINGNUM, "", DEFAULT_APARTNUM);
    }

    public Address(String street, int buildingNum, String buildingLetter, int apartNum) {
        this("Самара", -1, street, buildingNum, buildingLetter, apartNum);
    }

    public Address(String city, int postcode, String street, int buildingNum, String buildingLetter, int apartNum) {
        if (buildingNum < DEFAULT_BUILDINGNUM || apartNum < DEFAULT_APARTNUM) {
            throw new IllegalArgumentException("Number < 0");
        }
        for (int i = 0; i < city.length(); i++) {
            if (!Character.isAlphabetic(city.charAt(i))) {
                throw new IllegalArgumentException("Character in city is not alphabetic");
            }
        }
        for (int i = 0; i < street.length(); i++) {
            if (!Character.isAlphabetic(street.charAt(i))) {
                throw new IllegalArgumentException("Character in street is not alphabetic");
            }
        }
        for (int i = 0; i < buildingLetter.length(); i++) {
            if (!Character.isAlphabetic(buildingLetter.charAt(i))) {
                throw new IllegalArgumentException("Character in building letter is not alphabetic");
            }
        }
        this.city = city;
        this.street = street;
        this.buildingNum = buildingNum;
        this.buildingLetter = buildingLetter;
        this.apartmentNum = apartNum;
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public int getPostcode() {
        return postcode;
    }

    public String getStreet() {
        return street;
    }

    public int getBuildingNum() {
        return buildingNum;
    }

    public String getBuildingLetter() {
        return buildingLetter;
    }

    public int getApartmentNumber() {
        return apartmentNum;
    }

    @Override
    public String toString() {  // todo with String.format  /resolved
        String str = String.format("Address: %s %d, %s %d%s-%d", city, postcode, street, buildingNum, buildingLetter, apartmentNum);
        /*
        StringBuilder strBuild = new StringBuilder("");
        strBuild.append("Address: ");
        strBuild.append(this.city);
        strBuild.append(" ");
        if (this.postcode != -1) {
            strBuild.append(this.postcode);
        }
        strBuild.append(", ");
        strBuild.append(this.street);
        if (this.buildingNum != -1) {
            strBuild.append(" ");
            strBuild.append(this.buildingNum);
        }
        strBuild.append(this.buildingLetter);
        strBuild.append("-");
        strBuild.append(this.apartmentNum);
        */
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Address))
            return false;
        Address adr = (Address) obj;
        return adr.postcode == postcode && adr.city.equals(city) && adr.street.equals(street) &&
                adr.apartmentNum == apartmentNum && adr.buildingLetter.equals(buildingLetter) &&
                adr.buildingNum == buildingNum;
    }

    @Override
    public int hashCode() { // todo no Integer.hashcode/ resolved
        return buildingNum ^
                apartmentNum ^
                buildingLetter.hashCode() ^
                city.hashCode() ^
                postcode ^
                street.hashCode();
    }

    @Override
    public Address clone() {
        Address addressClone = new Address(city, postcode, street, buildingNum, buildingLetter, apartmentNum);
        return addressClone;
    }

}

