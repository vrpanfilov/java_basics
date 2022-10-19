package practice.strings;

import java.util.Scanner;

public class FullNameFormatter {

    private static int start;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            start = 0;
            String input = scanner.nextLine();
            if (input.equals("0")) {
                scanner.close();
                break;
            }
            //TODO:напишите ваш код тут, результат вывести в консоль.
            //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО

            String ls = System.lineSeparator();
            String output = "";
            String surname = getItem(input);
            String firstname = getItem(input);
            String patronymic = getItem(input);

            if (surname.isEmpty() || firstname.isEmpty() || patronymic.isEmpty()
                    || hasPostfix(input)) {
                System.out.println("Введенная строка не является ФИО");
            } else {
                System.out.print("Фамилия: " + surname + ls +
                        "Имя: " + firstname + ls +
                        "Отчество: " + patronymic + ls);
            }
        }
    }

    private static String getItem(String input) {
        String result = "";
        int end = input.indexOf(" ", start);
        if (end > 0) {
            result = input.substring(start, end);
        } else {
            if (start < input.length()) {
                end = input.length() - 1;
                result = input.substring(start, input.length());
            } else {
                return "";
            }
        }
        start = end + 1;
        for (int i = 0; i < result.length(); i++) {
            char symb = result.charAt(i);
            if ((symb < 'А' || symb > 'Я') &&
                    symb != 'Ё' &&
                    (symb < 'а' || symb > 'я') &&
                    symb != 'ё' &&
                    symb != '-') {
                return "";
            }
        }
        return result;
    }

    private static boolean hasPostfix(String input) {
        return start < input.length();
    }
}