package practice;

public abstract class Client {
    private double amount;

    public double getAmount() {
        return amount;
    }

    public void put(double amountToPut) {
        if (amountToPut > 0) {
            amount += amountToPut;
        }
    }

    public void take(double amountToTake) {
        if (amountToTake <= getAmount()) {
            amount -= amountToTake;
        }
    }

    public abstract String info();
}
