package practice;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static TodoList todoList = new TodoList();

    public static void main(String[] args) {
        // TODO: написать консольное приложение для работы со списком дел todoList
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                scanner.close();
                break;
            }

            String command;
            String todo;

            int spacePos = input.indexOf(' ');
            if (spacePos < 0) {
                if (input.length() == 0) {
                    continue;
                }
                command = input;
                todo = "";
            } else {
                command = input.substring(0, spacePos);
                todo = input.substring(spacePos + 1);
            }

            command = command.toUpperCase();
            processCommand(command, todo);
        }
    }

    private static void processCommand(String command, String todo) {
        int spacePos;
        switch (command) {
            case "LIST":
                ArrayList<String> list = todoList.getTodos();
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + " - " + list.get(i));
                }
                break;
            case "ADD":
                todoList.add(todo);
                break;
            case "EDIT":
                spacePos = todo.indexOf(' ');
                if (spacePos < 0) {
                    return;
                }
                String indStr = todo.substring(0, spacePos);
                if (!indStr.matches("\\d+")) {
                    return;
                }
                int index = Integer.parseInt(indStr);
                todo = todo.substring(spacePos + 1);
                todoList.edit(index, todo);
                break;
            case "DELETE":
                if (!todo.matches("\\d+")) {
                    return;
                }
                index = Integer.parseInt(todo);
                todoList.delete(index);
                break;
        }
    }
}
