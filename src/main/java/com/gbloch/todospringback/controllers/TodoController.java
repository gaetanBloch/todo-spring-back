package com.gbloch.todospringback.controllers;

import com.gbloch.todospringback.model.Todo;
import com.gbloch.todospringback.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        return todoService.findAllByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable Long id) {
        return todoService.findById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable String username, @PathVariable Long id) {
        todoService.deleteById(id);
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username,
                           @PathVariable Long id,
                           @RequestBody Todo todo) {
        todo.setId(id);
        todo.setUsername(username);
        return todoService.update(todo);
    }

    @PostMapping("/users/{username}/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo) {
        todo.setUsername(username);
        return todoService.create(todo);
    }
}
