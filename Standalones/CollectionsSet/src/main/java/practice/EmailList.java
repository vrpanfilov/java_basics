package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class EmailList {
    private static final String WRONG_EMAIL_ANSWER = "Неверный формат email";
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final TreeSet<String> list = new TreeSet<>();

    public void add(String email) {
        // TODO: валидный формат email добавляется, email это строка, она быть может любой
        // принять решение добавлять аргумент email или нет должен этот метод
        email = email.toLowerCase();
        if(email.matches(EMAIL_PATTERN)) {
            list.add(email);
        } else {
            System.out.println(WRONG_EMAIL_ANSWER);
        }
    }

    public List<String> getSortedEmails() {
        // TODO: возвращается сортированный список электронных адресов в алфавитном порядке
        return new ArrayList<>(list);
    }

}
