package practice;

public class IndividualBusinessman extends Client {
    private final double BIG_COMMISSION = 0.01;
    private final double LITTLE_COMMISSION = 0.005;

    @Override
    public void put(double amountToPut) {
        double commission = amountToPut < 1000 ?
                BIG_COMMISSION :
                LITTLE_COMMISSION;
        super.put(amountToPut - amountToPut * commission);
    }

    @Override
    public String info() {
        return "Пополнение счёта:\n" +
                "\tкомиссия 1% при внесении менее 1000 руб.\n" +
                "\tкомиссия 0.5% при внесении от 1000 руб.\n" +
                "Снятие средств: без комиссии\n" +
                "Баланс: " + String.format("%1.2f\n", getAmount());
    }
}
