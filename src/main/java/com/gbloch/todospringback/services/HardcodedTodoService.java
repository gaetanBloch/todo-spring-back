package com.gbloch.todospringback.services;

import com.gbloch.todospringback.exceptions.ResourceNotFoundException;
import com.gbloch.todospringback.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Gaëtan Bloch
 * Created on 02/05/2020
 */
@Service
final class HardcodedTodoService implements TodoService {
    static final String USERNAME = "gbloch";
    static final String DESCRIPTION = "Become an expert at Angular";
    private static final List<Todo> TODOS = new ArrayList<>(4);

    static {
        resetTodos();
    }

    @Override
    public List<Todo> findAll() {
        return TODOS;
    }

    @Override
    public Todo findById(Long id) {
        return TODOS.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Todo not found for id = " + id);
                });
    }

    @Override
    public void deleteById(Long id) {
        TODOS.remove(findById(id));
    }

    @Override
    public Todo update(Todo todo) {
        TODOS.remove(findById(todo.getId()));
        TODOS.add(todo);
        return todo;
    }

    // For Tests
    static void resetTodos() {
        Long idCounter = 0L;
        TODOS.clear();
        TODOS.add(Todo.builder().id(++idCounter)
                .username(USERNAME)
                .description(DESCRIPTION)
                .targetDate(new Date())
                .isDone(false)
                .build());
        TODOS.add(Todo.builder()
                .id(++idCounter)
                .username(USERNAME)
                .description("Become an expert at Node.js")
                .targetDate(new Date())
                .isDone(false)
                .build());
        TODOS.add(Todo.builder()
                .id(++idCounter)
                .username(USERNAME)
                .description("Learn Reactjs")
                .targetDate(new Date())
                .isDone(false)
                .build());
        TODOS.add(Todo.builder()
                .id(++idCounter)
                .username(USERNAME)
                .description("Learn Ansible")
                .targetDate(new Date())
                .isDone(false)
                .build());
    }
}
