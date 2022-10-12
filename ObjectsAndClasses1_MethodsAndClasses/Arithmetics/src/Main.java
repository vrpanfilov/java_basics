public class Main {
    public static void main(String[] args) {
        Arithmetics arithmetics = new Arithmetics(7, 3);
        System.out.println("Исходные числа: " + 7 + " и " + 3);
        System.out.println("Сумма: " + arithmetics.sum());
        System.out.println("Произведение: " + arithmetics.mult());
        System.out.println("Максимум: " + arithmetics.max());
        System.out.println("Минимум: " + arithmetics.min());
    }
}
