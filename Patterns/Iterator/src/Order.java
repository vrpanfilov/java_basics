public class Order {
    private DeliveryType deliveryType;
    private int price;

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Order(int price, DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price: " + price + ", delivery by: " +
                deliveryType.toString();
    }
}
