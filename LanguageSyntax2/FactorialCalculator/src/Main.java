import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите целое число от 0 до 12:");
        int value = new Scanner(System.in).nextInt();
        if (value < 0 || value > 12) {
            System.out.println("Число должно быть от 0 до 12");
        } else {
            int factorial = 1;
            for (int i = 1; i <= value; i = i + 1) {
                factorial = factorial * i;
            }
            System.out.println(value + "! = " + factorial);
        }
    }
}
