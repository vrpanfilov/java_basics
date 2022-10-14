import lombok.Data;

import java.util.Map;
import java.util.Random;

@Data
public class Bank {
    final int AMOUNT_TO_CHECK = 50_000;

    private Map<String, Account> accounts;
    private final Random random = new Random();
    private Boolean res = new Boolean(true);

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        Account sourAccount = accounts.get(fromAccountNum);
        Account destAccount = accounts.get(toAccountNum);
        if (sourAccount.getIsBlocked().get() || destAccount.getIsBlocked().get()) {
            return;
        }
        if (amount > AMOUNT_TO_CHECK) {
            System.out.println("isFraud(" + fromAccountNum + ", " +
                    toAccountNum + ", " + amount + ")");
            try {
                if (isFraud(fromAccountNum, toAccountNum, amount)) {
                    sourAccount.getIsBlocked().set(true);
                    destAccount.getIsBlocked().set(true);
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        synchronized (res) {
            if (sourAccount.getMoney() < amount) {
                return;
            }
            sourAccount.setMoney(sourAccount.getMoney() - amount);
            destAccount.setMoney(destAccount.getMoney() + amount);
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        synchronized (res) {
            return accounts.get(accountNum).getMoney();
        }
    }

    public long getSumAllAccounts() {
        long sum = 0;
        synchronized (res) {
            for (Map.Entry<String, Account> entry : accounts.entrySet()) {
                sum += entry.getValue().getMoney();
            }
        }
        return sum;
    }
}
