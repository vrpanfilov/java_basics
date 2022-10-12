package practice.regex;

import java.util.Scanner;

public class PhoneCleanerRegex {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                scanner.close();
                break;
            }
            //TODO:напишите ваш код тут, результат вывести в консоль.
            String regex = "[^0-9]";
            input = input.replaceAll(regex, "");

            if (input.length() == 10) {
                input = '7' + input;
            }
            if (input.startsWith("8") && input.length() == 11) {
                input = input.replace('8', '7');
            }

            regex = "79[\\d]{9}";
            if (input.matches(regex)) {
                System.out.println(input);
            } else {
                System.out.println("Неверный формат номера");
            }
        }
    }

}
