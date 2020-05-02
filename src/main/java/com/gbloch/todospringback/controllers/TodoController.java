package com.gbloch.todospringback.controllers;

import com.gbloch.todospringback.model.Todo;
import com.gbloch.todospringback.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Gaëtan Bloch
 * Created on 02/05/2020
 */
@RestController
@RequiredArgsConstructor
final class TodoController extends AbstractController {

    private final TodoService todoService;

    @GetMapping("/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username) {
        return todoService.findAll();
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo getTodoById(@PathVariable String username, @PathVariable Long id) {
        return todoService.findById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public Todo deleteTodoById(@PathVariable String username, @PathVariable Long id) {
        return todoService.deleteById(id);
    }
}
