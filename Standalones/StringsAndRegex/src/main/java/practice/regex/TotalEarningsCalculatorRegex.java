package practice.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TotalEarningsCalculatorRegex {
    public static int calculateSalarySum(String text) {
        //TODO: реализуйте метод

        String regex = "[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int sum = 0;
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String earningStr = text.substring(start, end);
            int earning = Integer.parseInt(earningStr);
            sum += earning;
        }
        return sum;
    }

    public static void main(String[] args) {
        String s = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        int sum = calculateSalarySum(s);
        System.out.println(sum);
    }

}