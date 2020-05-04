package com.gbloch.todospringback.services;

import com.gbloch.todospringback.TodoRepository;
import com.gbloch.todospringback.exceptions.ResourceNotFoundException;
import com.gbloch.todospringback.model.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.gbloch.todospringback.TestUtils.TODO;
import static com.gbloch.todospringback.TestUtils.TODOS;
import static com.gbloch.todospringback.services.HardcodedTodoService.DESCRIPTION;
import static com.gbloch.todospringback.services.HardcodedTodoService.USERNAME;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * @author Gaëtan Bloch
 * Created on 04/05/2020
 */
@ExtendWith(MockitoExtension.class)
class JpaTodoServiceTest {
    @Mock
    private TodoRepository todoRepository;
    @InjectMocks
    private JpaTodoService todoService;

    @Test
    void findAllTest() {
        // Given
        given(todoRepository.findAll()).willReturn(TODOS);

        // When
        List<Todo> todos = todoService.findAll();

        // Then
        assertEquals(2, todos.size());
        assertEquals(1L, todos.get(0).getId());
        assertEquals(USERNAME, todos.get(0).getUsername());
        assertEquals(DESCRIPTION, todos.get(0).getDescription());
        assertFalse(todos.get(0).isDone());
    }

    @Test
    void findByIdTest() {
        // Given
        given(todoRepository.findById(1L)).willReturn(Optional.of(TODO));

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
        // Given
        given(todoRepository.findById(1L)).willReturn(Optional.empty());

        // When Then Throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> todoService.deleteById(1L));
    }

    @Test
    void deleteByIdTest() {
        // Given
        given(todoRepository.findById(1L)).willReturn(Optional.of(TODO));

        // When
        todoService.deleteById(1L);

        // Then
        then(todoRepository).should().deleteById(1L);
    }

    @Test
    void deleteByIdNotFoundTest() {
        // Given
        given(todoRepository.findById(1L)).willReturn(Optional.empty());

        // When Then Throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> todoService.deleteById(1L));
    }

    @Test
    void updateTest() {
        // Given
        given(todoRepository.findById(1L)).willReturn(Optional.of(TODO));
        given(todoRepository.save(TODO)).willReturn(TODO);

        // When
        Todo todo = todoService.update(TODO);

        // Then
        assertEquals(1L, todo.getId());
        assertEquals(USERNAME, todo.getUsername());
        assertEquals(DESCRIPTION, todo.getDescription());
        assertFalse(todo.isDone());
    }

    @Test
    void updateNotFoundTest() {
        // Given
        given(todoRepository.findById(1L)).willReturn(Optional.empty());

        // When Then Throws ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> todoService.update(TODO));
    }

    @Test
    void createTest() {
        // Given
        given(todoRepository.save(TODO)).willReturn(TODO);

        // When
        Todo todo = todoService.create(TODO);

        // Then
        assertEquals(1L, todo.getId());
        assertEquals(USERNAME, todo.getUsername());
        assertEquals(DESCRIPTION, todo.getDescription());
        assertFalse(todo.isDone());
    }
}
