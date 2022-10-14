import lombok.Data;

import java.util.concurrent.atomic.AtomicBoolean;

@Data
public class Account {
    private String accNumber;
    private long money;
    private Bank bank;

    private AtomicBoolean isBlocked = new AtomicBoolean(false);
}
