package practice.strings;

import java.util.Scanner;

public class FullNameFormatter {

    private static final String WRONG_FIO = "Введенная строка не является ФИО";
    private static final String ls = System.lineSeparator();
    private static int start;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        outerCycle:
        while (true) {
            start = 0;
            String input = scanner.nextLine();
            if (input.equals("0")) {
                scanner.close();
                break;
            }
            //TODO:напишите ваш код тут, результат вывести в консоль.
            //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
            String[] words = input.split(" ");
            if (words.length != 3) {
                System.out.println(WRONG_FIO);
                continue;
            }
            String[] surnames = words[0].split("-");
            if (surnames.length > 2) {
                System.out.println(WRONG_FIO);
                continue;
            }
            for (String surname : surnames) {
                if (!wordIsName(surname)) {
                    System.out.println(WRONG_FIO);
                    continue outerCycle;
                }
            }
            for (int i = 1; i < 3; i++) {
                if (!wordIsName(words[i])) {
                    System.out.println(WRONG_FIO);
                    continue outerCycle;
                }
            }
            System.out.print("Фамилия: " + words[0] + ls +
                    "Имя: " + words[1] + ls +
                    "Отчество: " + words[2] + ls);
        }
    }

    private static boolean wordIsName(String word) {
        if (word.length() < 2) {
            return false;
        }
        if (!charIsUppercase(word.charAt(0))) {
            return false;
        }
        for (int i = 1; i < word.length(); i++) {
            if (!charIsLowercase(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean charIsUppercase(char ch) {
        return ch >= 'А' && ch <= 'Я' || ch == 'Ё';
    }

    private static boolean charIsLowercase(char ch) {
        return ch >= 'а' && ch <= 'я' || ch == 'ё';
    }
}