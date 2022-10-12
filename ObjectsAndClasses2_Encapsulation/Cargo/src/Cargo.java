public class Cargo {
    private final Dimensions dimensions;
    private final int weight;
    private final String deliveryAddress;
    private final boolean isTurnedOver;
    private final String registrationNumber;
    private final boolean isFragile;

    public String toString() {
        return "dimensions: " +
                dimensions.toString() +
                "weight: " + weight + "\n" +
                "deliveryAddress: " + deliveryAddress + "\n" +
                "isTurnedOver: " + isTurnedOver + "\n" +
                "registrationNumber: " + registrationNumber + "\n" +
                "isFragile: " + isFragile;
    }

    public Cargo(Dimensions dimensions,
                 int weight,
                 String deliveryAddress,
                 boolean isTurnedOver,
                 String registrationNumber,
                 boolean isFragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.isTurnedOver = isTurnedOver;
        this.registrationNumber = registrationNumber;
        this.isFragile = isFragile;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public Cargo setDimentions(Dimensions dimensions) {
        return new Cargo(
                dimensions,
                weight,
                deliveryAddress,
                isTurnedOver,
                registrationNumber,
                isFragile);
    }

    public int getWeight() {
        return weight;
    }

    public Cargo setWeight(int weight) {
        return new Cargo(
                dimensions,
                weight,
                deliveryAddress,
                isTurnedOver,
                registrationNumber,
                isFragile);
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public Cargo setDeliveryAddress(String deliveryAddress) {
        return new Cargo(
                dimensions,
                weight,
                deliveryAddress,
                isTurnedOver,
                registrationNumber,
                isFragile);
    }

    public boolean isTurnedOver() {
        return isTurnedOver;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public boolean isFragile() {
        return isFragile;
    }
}
