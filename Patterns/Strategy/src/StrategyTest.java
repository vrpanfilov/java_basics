import java.util.Date;

public class StrategyTest {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item("Торт Медовик", 650), 2);
        cart.addItem(new Item("Молоко 3,5%", 56), 3);
        cart.addItem(new Item("Хлеб белый", 28), 4);

        cart.pay(new CreditCard("1234 5678 9012 3456",
                "Vladimir Panfilov", "889",
                new Date(2023, 02, 0)));

        cart.pay(new Cash());

        cart.pay(new YandexMoney("789012345609874589"));
    }
}
