public class Main {

    public static void main(String[] args) {
        System.out.println("Elevator");
        Country russia = new Country("Россия");
        russia.setCapital("Москва");
        russia.setPopulation(145478097);
        russia.setSquare(17098246);
        russia.setHasAccessToSea(true);
        System.out.println("Страна: " + russia.getName());
        System.out.println("Столица: " + russia.getCapital());
        System.out.println("Население: " + russia.getPopulation());
        System.out.println("Площадь: " + russia.getSquare());
        System.out.println("Выход к морю: " +
                (russia.isHasAccessToSea() ? "есть" : " нет"));
        System.out.println();
    }
}
