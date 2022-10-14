import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws AddException {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;
        final String ADD_COMMAND = "add Василий Петров " +
                "vasily.petrov@gmail.com +79215637722";
        final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
                "\tlist\n\tcount\n\tremove Василий Петров";
        final String ADD_EXAMPLE = "Available command examples: \n" +
                COMMAND_EXAMPLES;
        final String LENGTH_EX = "Wrong number of parameters. " + ADD_EXAMPLE;
        final String NAME_EX = "Wrong name. " + ADD_EXAMPLE;
        final String SURNAME_EX = "Wrong surname. " + ADD_EXAMPLE;
        final String EMAIL_EX = "Wrong email address. " + ADD_EXAMPLE;
        final String PHONE_EX = "Wrong phone number. " + ADD_EXAMPLE;

        final String NAME_REGEX = "^[А-ЯЁ][а-яё]+$";
        final String SURNAME_REGEX = "^[А-ЯЁ][а-яё]+(-[А-ЯЁ][а-яё]+)?$";
        // From site https://www.freeformatter.com/java-regex-tester.html
        final String EMAIL_REGEX = "^[-a-z0-9~!$%^&*_=+}{\\'?]+" +
                "(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*" +
                "(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|" +
                "info|int|mil|museum|name|net|org|pro|travel|mobi|" +
                "[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}" +
                "\\.[0-9]{1,3}))(:[0-9]{1,5})?$";
        final String PHONE_REGEX = "^\\+79[\\d]{9}$";

//        add Василий Петров vasily.petrov@gmail.com +79215637722

        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new AddException(LENGTH_EX);
        }
        if (!components[INDEX_NAME].matches(NAME_REGEX)) {
            throw new AddException(NAME_EX);
        }
        if (!components[INDEX_SURNAME].matches(SURNAME_REGEX)) {
            throw new AddException(SURNAME_EX);
        }
        if (!components[INDEX_EMAIL].toLowerCase().matches(EMAIL_REGEX)) {
            throw new AddException(EMAIL_EX);
        }
        if (!components[INDEX_PHONE].matches(PHONE_REGEX)) {
            throw new AddException(PHONE_EX);
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) throws RemoveException {
        final String WRONG_NAME_EX = "The Storage does not contain the customer ";
        if (!storage.containsKey(name)) {
            throw new RemoveException(WRONG_NAME_EX + "\"" + name + "\"");
        }
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}

class AddException extends Exception {
    public AddException(String message) {
        super("AddException: " + message);
    }
}

class RemoveException extends Exception {
    public RemoveException(String message) {
        super("RemoveException: " + message);
    }
}