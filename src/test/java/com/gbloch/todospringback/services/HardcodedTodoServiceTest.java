package com.gbloch.todospringback.services;

import com.gbloch.todospringback.exceptions.ResourceNotFoundException;
import com.gbloch.todospringback.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gbloch.todospringback.services.HardcodedTodoService.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gaëtan Bloch
 * Created on 02/05/2020
 */
class HardcodedTodoServiceTest {
    private HardcodedTodoService todoService;

    @BeforeEach
    void setUp() {
        todoService = new HardcodedTodoService();
    }

    @Test
    void findAllTest() {
        // When
        List<Todo> todos = todoService.findAll();

        // Then
        assertEquals(4, todos.size());
        assertEquals(1L, todos.get(0).getId());
        assertEquals(USERNAME, todos.get(0).getUsername());
        assertEquals(DESCRIPTION, todos.get(0).getDescription());
        assertFalse(todos.get(0).isDone());
    }

    @Test
    void findAllByUsernameTest() {
        // When
        List<Todo> todos = todoService.findAllByUsername(USERNAME);

        // Then
        assertEquals(4, todos.size());
        assertEquals(1L, todos.get(0).getId());
        assertEquals(USERNAME, todos.get(0).getUsername());
        assertEquals(DESCRIPTION, todos.get(0).getDescription());
        assertFalse(todos.get(0).isDone());
    }

    @Test
    void findByIdTest() {
        // When
        Todo todo = todoService.findById(1L);

        // Then
        assertEquals(1L, todo.getId());
        assertEquals(USERNAME, todo.getUsername());
        assertEquals(DESCRIPTION, todo.getDescription());
        assertFalse(todo.isDone());
    }

    @Test
    void findByIdNotFoundTest() {
        assertThrows(ResourceNotFoundException.class, () -> todoService.findById(5L));
    }

    @Test
    void deleteByIdTest() {
        try {
            // When
            todoService.deleteById(1L);

            // Then
            assertEquals(3, todoService.findAll().size());
        } finally {
            HardcodedTodoService.resetTodos();
        }
    }

    @Test
    void deleteByIdNotFoundTest() {
        assertThrows(ResourceNotFoundException.class, () -> todoService.deleteById(5L));
    }

    @Test
    void updateTest() {
        final String newUsername = "username";
        final String newDescription = "description";

        try {
            // Given
            Todo todo = todoService.findById(1L);
            todo.setUsername(newUsername);
            todo.setDescription(newDescription);

            // When
            todoService.update(todo);

            // Then
            assertEquals(4, todoService.findAll().size());
            assertEquals(1L, todo.getId());
            assertEquals(newUsername, todo.getUsername());
            assertEquals(newDescription, todo.getDescription());
            assertFalse(todo.isDone());
        } finally {
            HardcodedTodoService.resetTodos();
        }
    }

    @Test
    void updateNotFoundTest() {
        assertThrows(ResourceNotFoundException.class, () ->
                todoService.update(Todo.builder().id(5L).build())
        );
    }

    @Test
    void createTest() {
        try {
            // Given
            Todo todo = createTodo();

            // When
            todoService.create(todo);

            // Then
            assertEquals(5, todoService.findAll().size());
            assertEquals(6L, todo.getId());
            assertEquals(USERNAME, todo.getUsername());
            assertEquals(DESCRIPTION, todo.getDescription());
            assertFalse(todo.isDone());
        } finally {
            HardcodedTodoService.resetTodos();
        }
    }
}
