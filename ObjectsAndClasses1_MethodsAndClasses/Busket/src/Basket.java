public class Basket {
    private int count = 0;      // Кол-во товаров в корзине
    private String items = "";
    private int totalCost = 0;
    private int limit;
    private double totalWeight = 0;

    private static int totalProductCount = 0;
    private static int totalProductCost = 0;

    private static int basketCount = 0;

    public Basket() {
        increaseCount(1);
        basketCount = basketCount + 1;
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalCost = totalPrice;
    }

    public int getCount() {
        return count;
    }

    public void increaseCount(int count) {
        this.count = this.count + count;
    }

    public void add(String name, int price) {
        add(name, price, 1);
    }

    public void add(String name, int price, int count) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalCost + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " +
                count + " шт. - " + price;
        totalCost = totalCost + count * price;
        increaseTotalValues(price, count);
    }

    public void add(String name, int price, int count, double weight) {
        add(name, price, count);
        totalWeight = totalWeight + weight;
    }

    public void clear() {
        items = "";
        totalProductCount = totalProductCount - count;
        totalProductCost = totalProductCost - totalCost;
        totalCost = 0;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public double getTotalWeight() { return totalWeight; }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }

    private static void increaseTotalValues(int price, int count) {
        totalProductCount = totalProductCount + count;
        totalProductCost = totalProductCost + count * price;
    }

    public static int getAverageProductPrice() {
        return totalProductCost / totalProductCount;
    }

    public static int getAverageBasketCost() {
        return  totalProductCost / basketCount;
    }

    public static int getBasketCount() {
        return basketCount;
    }}
