package com.gbloch.todospringback.services;

import com.gbloch.todospringback.exceptions.ResourceNotFoundException;
import com.gbloch.todospringback.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gbloch.todospringback.services.HardcodedTodoService.DESCRIPTION;
import static com.gbloch.todospringback.services.HardcodedTodoService.USERNAME;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gaëtan Bloch
 * Created on 02/05/2020
 */
class HardcodedTodoServiceTest {
    private TodoService todoService;

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
}
