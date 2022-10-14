import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String GET_ERROR = "Wrong command! Available command example: \n" +
            "get Василий Петров";
    private static final String GET_NULL_ERROR = "CustomerStorage do not contain ";
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        while (true) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);
            try {
                if (tokens[0].equals("add")) {
                    executor.addCustomer(tokens[1]);
                } else if (tokens[0].equals("list")) {
                    executor.listCustomers();
                } else if (tokens[0].equals("get")) {
                    try {
                        Customer customer = executor.getCustomer(tokens[1]);
                        System.out.println(customer != null ?
                                customer :
                                GET_NULL_ERROR + "\"" + tokens[1] + "\"");
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println(GET_ERROR);
                    }
                } else if (tokens[0].equals("remove")) {
                    executor.removeCustomer(tokens[1]);
                } else if (tokens[0].equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if (tokens[0].equals("help")) {
                    System.out.println(helpText);
                } else if (tokens[0].equals("q")) {
                    break;
                } else {
                    System.out.println(COMMAND_ERROR);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
