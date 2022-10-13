package practice;

public class LegalPerson extends Client {
    private final double COMMISSION = 0.01;

    @Override
    public void take(double amountToTake) {
        amountToTake += amountToTake * COMMISSION;
        super.take(amountToTake);
    }

    @Override
    public String info() {
        return "Пополнение счёта: без комиссии\n" +
                "Снятие средств: комиссия 1%\n" +
                "Баланс: " + String.format("%1.2f\n", getAmount());
    }
}
