package practice.strings;

public class TotalEarningsCalculator {

    public static void main(String[] args) {

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        //TODO: напишите ваш код, результат вывести в консоль

        int fromIndex = text.length() - 1;
        int sum = 0;
        int end = text.lastIndexOf(" руб", fromIndex);
        while (end > 0) {
            int start = text.lastIndexOf(" ", end - 1);
            String rubles = text.substring(start, end).trim();
            sum += Integer.parseInt(rubles);
            fromIndex = start - 1;
            end = text.lastIndexOf(" руб", fromIndex);
        }
        System.out.println(sum);
    }

}