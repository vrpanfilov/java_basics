package practice.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitText {
    public static String splitTextIntoWords(String text) {
        //TODO реализуйте метод

        String regex = "[\\’A-Za-z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        String res = "";
        if (text.isEmpty()) {
            return res;
        }
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String word = text.substring(start, end);
            if (end == text.length() - 1) {
                res = res.concat(word);
            } else {
                res = res.concat(word + System.lineSeparator());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String text = "Implement a method that takes as an argument a string " +
                "containing English text (at least 100 words). The method should " +
                "divide the text into words and return a string consisting " +
                "of the received words, each word on a separate line.";
        System.out.print(splitTextIntoWords(text));
    }
}