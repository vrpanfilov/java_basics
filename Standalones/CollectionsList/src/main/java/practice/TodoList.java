package practice;

import java.util.ArrayList;

public class TodoList extends ArrayList<String> {

    public boolean add(String todo) {
        // TODO: добавьте переданное дело в конец списка
        super.add(todo);
        todoAdded(todo);
        return true;
    }

    public void add(int index, String todo) {
        // TODO: добавьте дело на указаный индекс,
        //  проверьте возможность добавления
        if (index < size()) {
            super.add(index, todo);
            todoAdded(todo);
        } else {
            super.add(todo);
        }
    }

    private void todoAdded(String todo) {
        System.out.println("Добавлено дело \"" + todo + "\"");
    }

    public void edit(int index, String todo) {
        // TODO: заменить дело на index переданным todo индекс,
        //  проверьте возможность изменения
        if (index >= size()) {
            return;
        }
        String oldTodo = get(index);
        remove(index);
        add(index, todo);
        System.out.println("Дело \"" + oldTodo +
                "\" заменено на " + "\"" + todo + "\"");
    }

    public void delete(int index) {
        // TODO: удалить дело находящееся по переданному индексу,
        //  проверьте возможность удаления дела
        if (index >= size()) {
            return;
        }
        String item = get(index);
        remove(index);
        System.out.println("Дело \"" + item + "\" удалено");
    }

    public ArrayList<String> getTodos() {
        // TODO: вернуть список дел
        return this;
    }

}