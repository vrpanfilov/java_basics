package main.controller;

import main.model.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class DefaultController {
    @RequestMapping("/")
    public String index(Model model) {
        List<Todo> todos = TodoController.getInstance().list();
        todos.sort(Comparator.comparing(Todo::getId));

        model.addAttribute("todos", todos)
                .addAttribute("todosCount", todos.size());

        return "index";
    }
}
