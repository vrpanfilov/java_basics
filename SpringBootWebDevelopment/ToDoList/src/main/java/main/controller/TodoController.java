package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;
    private static TodoController instance;

    public TodoController() {
        instance = this;
    }

    public static TodoController getInstance() {
        return instance;
    }

    @GetMapping("/todos/")
    public List<Todo> list() {
        Iterable<Todo> todoIterable = todoRepository.findAll();
        List<Todo> todos = new ArrayList<>();
        for (Todo todo : todoIterable) {
            todos.add(todo);
        }
        return todos;
    }

    @PostMapping("/todos/")
    public int add(Todo todo) {
        Todo newTodo = todoRepository.save(todo);
        return newTodo.getId();
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            return new ResponseEntity(optionalTodo.get(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity put(@PathVariable int id, Todo todo) {
        todo.setId(id);
        Todo newTodo = todoRepository.save(todo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        todoRepository.delete(optionalTodo.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/todos/")
    public ResponseEntity deleteAll() {
        todoRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
