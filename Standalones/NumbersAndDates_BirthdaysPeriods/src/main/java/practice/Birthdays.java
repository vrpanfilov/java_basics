package practice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Birthdays {

    public static void main(String[] args) {

        int day = 31;
        int month = 12;
        int year = 2021;

        System.out.print(collectBirthdays(year, month, day));

    }

    public static String collectBirthdays(int year, int month, int day) {

        //TODO реализуйте метод для построения строки в следующем виде
        //0 - 31.12.1990 - Mon
        //1 - 31.12.1991 - Tue

        LocalDate birthday = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();
        int birthdayNumb = 0;
        String result = "";
        while (birthday.isBefore(today) || birthday.isEqual(today)) {
            int dayOfWeek = birthday.getDayOfWeek().getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            result = result + birthdayNumb + " - " +
                    formatter.format(birthday) + " - " +
                    getDayOfWeek(dayOfWeek) + System.lineSeparator();
            birthday = birthday.plusYears(1);
            birthdayNumb++;
        }
        return result;
    }

    private static String getDayOfWeek(int day) {
        switch (day) {
            case 1:
                return "Mon";
            case 2:
                return "Tue";
            case 3:
                return "Wed";
            case 4:
                return "Thu";
            case 5:
                return "Fri";
            case 6:
                return "Sat";
            case 7:
                return "Sun";
        }
        return "";
    }
}
