package com.gbloch.todospringback.controllers;

import com.gbloch.todospringback.exceptions.ResourceNotFoundException;
import com.gbloch.todospringback.model.Todo;
import com.gbloch.todospringback.services.TodoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Gaëtan Bloch
 * Created on 02/05/2020
 */
@ExtendWith(MockitoExtension.class)
class TodoControllerTest {
    private static final String USERNAME = "gbloch";
    private static final String DESCRIPTION = "Become an expert at Angular";
    private static final Todo TODO = Todo.builder()
            .id(1L)
            .username(USERNAME)
            .description(DESCRIPTION)
            .targetDate(new Date())
            .isDone(false)
            .build();
    private static final List<Todo> TODOS = Arrays.asList(
            TODO,
            Todo.builder()
                    .id(2L)
                    .username(USERNAME)
                    .description("Become an expert at Node.js")
                    .targetDate(new Date())
                    .isDone(false)
                    .build()
    );

    @Mock
    private TodoService todoService;
    @InjectMocks
    private TodoController todoController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(todoController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllTodos() throws Exception {
        // Given
        given(todoService.findAll()).willReturn(TODOS);

        // When
        mockMvc.perform(get("/api/users/gbloch/todos"))

                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].username", equalTo(USERNAME)))
                .andExpect(jsonPath("$[0].description", equalTo(DESCRIPTION)))
                .andExpect(jsonPath("$[0].targetDate", any(Long.class)))
                .andExpect(jsonPath("$[0].done", equalTo(false)));
    }

    @Test
    void getTodoTest() throws Exception {
        // Given
        given(todoService.findById(1L)).willReturn(TODO);

        // When
        mockMvc.perform(get("/api/users/gbloch/todos/1"))

                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.username", equalTo(USERNAME)))
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)))
                .andExpect(jsonPath("$.targetDate", any(Long.class)))
                .andExpect(jsonPath("$.done", equalTo(false)));
    }

    @Test
    void getTodoNotFoundTest() throws Exception {
        // Given
        given(todoService.findById(1L)).willThrow(ResourceNotFoundException.class);

        // When
        mockMvc.perform(get("/api/users/gbloch/todos/1"))

                // Then
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", equalTo(HttpStatus.NOT_FOUND.value())))
                .andExpect(jsonPath("$.error", equalTo(HttpStatus.NOT_FOUND.getReasonPhrase())))
                .andExpect(jsonPath("$.message", emptyOrNullString()))
                .andExpect(jsonPath("$.trace", Matchers.any(String.class)))
                .andExpect(jsonPath("$.path", equalTo("/api/users/gbloch/todos/1")));
    }

    @Test
    void deleteTodoTest() throws Exception {
        // When
        mockMvc.perform(delete("/api/users/gbloch/todos/1"))

                // Then
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteTodoNotFoundTest() throws Exception {
        // Given
        doThrow(ResourceNotFoundException.class).when(todoService).deleteById(1L);

        // When
        mockMvc.perform(delete("/api/users/gbloch/todos/1"))

                // Then
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", equalTo(HttpStatus.NOT_FOUND.value())))
                .andExpect(jsonPath("$.error", equalTo(HttpStatus.NOT_FOUND.getReasonPhrase())))
                .andExpect(jsonPath("$.message", emptyOrNullString()))
                .andExpect(jsonPath("$.trace", Matchers.any(String.class)))
                .andExpect(jsonPath("$.path", equalTo("/api/users/gbloch/todos/1")));
    }

    @Test
    void updateTodoTest() throws Exception {
        // Given
        given(todoService.update(ArgumentMatchers.any(Todo.class))).willReturn(TODO);

        // When
        mockMvc.perform(put("/api/users/gbloch/todos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ControllerTestUtils.asJsonString(TODO)))

                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.username", equalTo(USERNAME)))
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)))
                .andExpect(jsonPath("$.targetDate", any(Long.class)))
                .andExpect(jsonPath("$.done", equalTo(false)));
    }

    @Test
    void updateTodoNotFoundTest() throws Exception {
        // Given
        given(todoService.update(ArgumentMatchers.any(Todo.class)))
                .willThrow(ResourceNotFoundException.class);

        // When
        mockMvc.perform(put("/api/users/gbloch/todos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ControllerTestUtils.asJsonString(TODO)))

                // Then
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", equalTo(HttpStatus.NOT_FOUND.value())))
                .andExpect(jsonPath("$.error", equalTo(HttpStatus.NOT_FOUND.getReasonPhrase())))
                .andExpect(jsonPath("$.message", emptyOrNullString()))
                .andExpect(jsonPath("$.trace", Matchers.any(String.class)))
                .andExpect(jsonPath("$.path", equalTo("/api/users/gbloch/todos/1")));
    }

    @Test
    void createTodoTest() throws Exception {
        // Given
        given(todoService.create(ArgumentMatchers.any(Todo.class))).willReturn(TODO);

        // When
        mockMvc.perform(post("/api/users/gbloch/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ControllerTestUtils.asJsonString(TODO)))

                // Then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.username", equalTo(USERNAME)))
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)))
                .andExpect(jsonPath("$.targetDate", any(Long.class)))
                .andExpect(jsonPath("$.done", equalTo(false)));
    }
}
