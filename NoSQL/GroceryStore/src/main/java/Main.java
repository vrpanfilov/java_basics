import java.util.Scanner;

public class Main {
    private static final String ADD_STORE = "ДОБАВИТЬ_МАГАЗИН <Название_магазина>";
    private static final String ADD_PRODUCT = "ДОБАВИТЬ_ТОВАР <Название_товара> <Цена>";
    private static final String EXPOSE_PRODUCT = "ВЫСТАВИТЬ_ТОВАР <Название_товара> <Название_магазина>";
    private static final String PRODUCT_STAT = "СТАТИСТИКА_ТОВАРОВ";
    private static final String EXIT = "ВЫЙТИ";
    private static final String HELP_TEXT = "Допустимые команды:" +
            "\n\t" + ADD_STORE + "\n\t" + ADD_PRODUCT + "\n\t" + EXPOSE_PRODUCT +
            "\n\t" + PRODUCT_STAT + "\n\t" + EXIT;
    private static final String START_PROMT = "Введите команду. " + HELP_TEXT;
    private static final String COMMAND_ERROR = "Команда неверна! " + HELP_TEXT;
    private static final String MISSED_PARAMETERS = "Не хватает параметров! " + HELP_TEXT;
    private static final String WRONG_PRICE = "Неверный формат введённого числа!";

    public static void main(String[] args) {
        System.out.println(START_PROMT);

        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();

//        storage.showProductStatistics();

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+");
            try {
                String tok = tokens[0].toUpperCase();
                if (tok.equals("ВЫЙТИ") || tok.equals("В") || tok.equals("D")) {
                    break;
                } else if (tok.equals("ДОБАВИТЬ_МАГАЗИН") || tok.equals("ДМ")) {
                    storage.addStore(tokens[1]);
                } else if (tok.equals("ДОБАВИТЬ_ТОВАР") || tok.equals("ДТ")) {
                    storage.addProduct(tokens[1], tokens[2]);
                } else if (tok.equals("ВЫСТАВИТЬ_ТОВАР") || tok.equals("ВТ")) {
                    storage.exposeProduct(tokens[1], tokens[2]);
                } else if (tok.equals("СТАТИСТИКА_ТОВАРОВ") || tok.equals("СТ")) {
                    storage.showProductStatistics();
                }else if (tok.equals("УМ")) {
                    storage.dropStore(tokens.length == 1 ? null : tokens[1]);
                } else if (tok.equals("УТ")) {
                    storage.dropProduct(tokens.length == 1 ? null : tokens[1]);
                } else if (tok.equals("ММ")) {
                    storage.listStores();
                } else if (tok.equals("ТТ")) {
                    storage.listProducts();
                } else {
                    System.out.println(COMMAND_ERROR);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(MISSED_PARAMETERS);
            } catch (NumberFormatException e) {
                System.out.println(WRONG_PRICE);
            } catch (StorageException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
