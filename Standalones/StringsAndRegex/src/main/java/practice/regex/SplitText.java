package practice.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitText {
    public static String splitTextIntoWords(String text) {
        //TODO реализуйте метод
        text = text.replaceAll("\\d", "");
        text = text.replaceAll("\\s*(\\s|,|!|\\.|;|-)\\s*", " ");
        String[] words = text.split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            builder.append(words[i]).append(System.lineSeparator());
        }
        return builder.append(words[words.length - 1]).toString();
    }

    public static void main(String[] args) {
        String text = "Implement a method that takes as an argument a string " +
                "containing English text (at least 100 words). The method should " +
                "divide the text into words and return a string consisting " +
                "of the received words, each word on a separate line.";
        System.out.print(splitTextIntoWords(text));
    }
}