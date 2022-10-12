package practice.regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullNameFormatterRegex {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                scanner.close();
                break;
            }
            //TODO:напишите ваш код тут, результат вывести в консоль.
            //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
            String ls = System.lineSeparator();

            String surname;
            String firstname;
            String patronymic;

            String regex = "^[А-ЯЁ][а-яё]{2,}([-][А-ЯЁ][а-яё]{2,})?\\s[А-ЯЁ][а-яё]{2,}\\s[А-ЯЁ][а-яё]{2,}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String[] words = input.split(" ");
                System.out.print("Фамилия: " + words[0] + ls +
                        "Имя: " + words[1] + ls +
                        "Отчество: " + words[2] + ls);
            } else {
                System.out.print("Введенная строка не является ФИО" + ls);
            }
        }
    }

}