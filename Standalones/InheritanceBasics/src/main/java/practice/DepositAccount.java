package practice;

import java.time.LocalDate;

public class DepositAccount extends BankAccount {
    public static final int FORBIDDEN_PERIOD = 1;   // Months
    LocalDate lastIncome;

    @Override
    public void take(double amountToTake) {
        if (lastIncome.plusMonths(FORBIDDEN_PERIOD).isAfter(LocalDate.now())) {
            return;
        }
        super.take(amountToTake);
    }

    @Override
    public void put(double amountToPut) {
        super.put(amountToPut);
        lastIncome = LocalDate.now();
    }
}
