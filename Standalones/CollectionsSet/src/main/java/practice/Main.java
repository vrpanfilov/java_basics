package practice;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static EmailList emailList = new EmailList();
    
    /* TODO:
        Пример вывода списка Email, после ввода команды LIST в консоль:
        test@test.com
        hello@mail.ru
        - каждый адрес с новой строки
        - список должен быть отсортирован по алфавиту
        - email в разных регистрах считается одинаковыми
           hello@skillbox.ru == HeLLO@SKILLbox.RU
        - вывод на печать должен быть в нижнем регистре
           hello@skillbox.ru
        Пример вывода сообщения об ошибке при неверном формате Email:
        "Неверный формат email"
    */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            //TODO: write code here
            processInput(input);
        }
    }

    private static void processInput(String input) {
        String command;
        String email;

        int spacePos = input.indexOf(' ');
        if (spacePos > 0) {
            command = input.substring(0, spacePos);
            email = input.substring(spacePos + 1);
        } else {
            command = input;
            email = "";
        }

        switch (command) {
            case "ADD":
                processAddCommand(email);
                break;
            case "LIST":
                processListCommand();
                break;
        }
    }

    private static void processAddCommand(String email) {
        emailList.add(email);
    }

    private static void processListCommand() {
        List<String> list = emailList.getSortedEmails();
        for(String item : list) {
            System.out.println(item);
        }
    }
}
