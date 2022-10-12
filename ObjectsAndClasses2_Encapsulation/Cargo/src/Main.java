public class Main {

    public static void main(String[] args) {
        Cargo cargo = new Cargo(
                new Dimensions(60, 30, 40),
                12,
                "На деревню дедушке",
                false,
                "A924",
                true
        );
        System.out.println("Начальный груз:");
        System.out.println(cargo + "\n");

        Cargo cargo1 = cargo.setWeight(14);
        cargo1 = cargo1.setDeliveryAddress("Новый адрес");
        cargo1 = cargo1.setDimentions(
                new Dimensions(
                        70,
                        cargo1.getDimensions().getWidth(),
                        cargo1.getDimensions().getHeight()));
        System.out.println("Начальный груз:");
        System.out.println(cargo + "\n");
        System.out.println("Модифицированный груз:");
        System.out.println(cargo1);
    }
}
