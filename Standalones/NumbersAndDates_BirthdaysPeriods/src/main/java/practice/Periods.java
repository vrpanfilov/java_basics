package practice;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Periods {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate startDate = LocalDate.parse("25.02.2011", formatter);
        LocalDate endDate = LocalDate.parse("05.03.2011", formatter);
        System.out.println(getPeriodFromBirthday(startDate, endDate));

        startDate = LocalDate.parse("25.02.2012", formatter);
        endDate = LocalDate.parse("05.03.2012", formatter);
        System.out.println(getPeriodFromBirthday(startDate, endDate));
    }
        // реализуйте вывод разницы между датами, используя класс Period
    public static String getPeriodFromBirthday(LocalDate firstDate, LocalDate secondDate) {
        Period period = Period.between(firstDate, secondDate);
        return "years:" + period.getYears() +
                ", months:" + period.getMonths() +
                ", days:" + period.getDays();
    }

}
