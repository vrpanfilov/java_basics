import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите целое положительное число:");

        int value = new Scanner(System.in).nextInt();
        if (value < 1) {
            System.out.println("Число должно быть целым положительным");
            return;
        }

        for (int i = 1; i <= value; i = i + 1) {
            for (int j = 1; j <= value; j = j + 1) {
                if (i * j == value) {
                    System.out.println(i + "*" + j);
                    break;
                }
            }
        }
    }
}
