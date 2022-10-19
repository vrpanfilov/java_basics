public class Cash implements PaymentMethod {
    @Override
    public boolean pay(int amount) {
        System.out.println("Will be payed later by cash");
        return false;
    }
}
