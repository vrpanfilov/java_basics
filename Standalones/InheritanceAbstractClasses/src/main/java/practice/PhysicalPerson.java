package practice;

public class PhysicalPerson extends Client {

    @Override
    public String info() {
        return "Пополнение счёта: без комиссии\n" +
                "Снятие средств: без комиссии\n" +
                "Баланс: " + String.format("%1.2f\n", getAmount());
    }
}
