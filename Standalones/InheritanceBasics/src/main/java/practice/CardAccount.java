package practice;

public class CardAccount extends BankAccount {
    @Override
    public void take(double amountToTake) {
        final double COMMISSION = 0.01;
        amountToTake += amountToTake * COMMISSION;
        super.take(amountToTake);
    }
}
