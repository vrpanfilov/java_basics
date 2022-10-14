import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Main {
    public static void main(String[] args) {
        new Test().test();
    }
}

@Data
class Test {
    private Bank bank;
    private Map<String, Account> accounts;
    private final int ACCOUNT_COUNT = 500;
    private final String ACCOUNT_PREFIX = "4080181070915";

    public void test() {
        bank = new Bank();
        Random random = new Random();
        generateAccounts();

        System.out.println("Общий баланс: " + bank.getSumAllAccounts());
        ExecutorService executor = Executors.newFixedThreadPool(10);
        // Формируем 30 раз (немного более, чем 1 раз в секунду) по 20 переводов
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 20; j++) {
                int index = random.nextInt(ACCOUNT_COUNT);
                String fromAccountNum = ACCOUNT_PREFIX + String.format("%07d", index);
                Client client = new Client(this, fromAccountNum);
                executor.execute(client);
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        System.out.println("Общий баланс: " + bank.getSumAllAccounts());
        System.out.println("count: " + Client.getCount().get());
    }

    private void generateAccounts() {
        accounts = new HashMap<>();
        bank.setAccounts(accounts);
        for (int i = 0; i < ACCOUNT_COUNT; i++) {
            Account account = new Account();
            account.setAccNumber(ACCOUNT_PREFIX + String.format("%07d", i));
            Random random = new Random();
            account.setMoney(100000 + random.nextInt(900000));
            accounts.put(account.getAccNumber(), account);
        }
    }
}

class Client implements Runnable {
    private Test test;
    private String fromAccountNum;
    private Random random = new Random();
    private static AtomicInteger count = new AtomicInteger(0);

    public static AtomicInteger getCount() {
        return count;
    }

    public Client(Test test, String fromAccountNum) {
        this.test = test;
        this.fromAccountNum = fromAccountNum;
    }

    @Override
    public void run() {
        count.incrementAndGet();
        int index = random.nextInt(test.getACCOUNT_COUNT());
        String toAccountNum = test.getACCOUNT_PREFIX() + String.format("%07d", index);

        int amount;
        if (random.nextInt(1000) < 50) {
            // Входит в 5%. Перевод > 50000
            amount = 50000 + random.nextInt(5) * 10000;
        } else {
            // Не входит. Перевод небольшой
            amount = 5000 + random.nextInt(9) * 5000;
        }
        test.getBank().transfer(fromAccountNum, toAccountNum, amount);
//        System.out.println(fromAccountNum + " " + toAccountNum);
    }
}
