package com.gbloch.todospringback.services;

import com.gbloch.todospringback.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gbloch.todospringback.services.HardcodedTodoService.DESCRIPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        assertEquals(DESCRIPTION, todos.get(0).getDescription());
        assertFalse(todos.get(0).isDone());
    }
}
