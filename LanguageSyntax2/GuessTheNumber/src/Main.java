import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int value = new Random().nextInt(100);
        int attempt = -1;
        while (attempt != value) {
            attempt = new Scanner(System.in).nextInt();
            if (value == attempt) {
                System.out.println("Вы угадали!");
            } else if (value > attempt) {
                System.out.println("Загаданное число больше");
            } else {
                System.out.println("Загаданное число меньше");
            }
        }
    }
}
