public class Main {
    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Молоко", 40);
        basket.add("Творог", 84, 4);
        basket.print("basket1");
        System.out.println();

        basket = new Basket();
        basket.add("Тетрадь", 90, 3);
        basket.add("Карандаш", 23, 2);
        basket.add("Ластик", 18);
        basket.print("basket2");
        System.out.println();

        System.out.println("BasketCount: " + Basket.getBasketCount());
        System.out.println("AverageProductPrice: " + Basket.getAverageProductPrice());
        System.out.println("AverageBasketCost: " + Basket.getAverageBasketCost());
    }
}
