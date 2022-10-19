import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    Map<Item, Integer> items = new HashMap<>();

    public void addItem(Item item, int count) {
        if (items.containsKey(item)) {
            int val = items.get(item) + count;
            items.put(item, val);
        }
        items.put(item, count);
    }

    public boolean pay(PaymentMethod paymentMethod) {
        int amount = 0;
        for (Map.Entry entry : items.entrySet()) {
            Item item = (Item) entry.getKey();
            int count = (Integer) entry.getValue();
            amount += item.getPrice() * count;
        }
        return paymentMethod.pay(amount);
    }
}
