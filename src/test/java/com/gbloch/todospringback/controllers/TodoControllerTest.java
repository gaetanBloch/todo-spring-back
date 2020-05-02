package com.gbloch.todospringback.controllers;

import com.gbloch.todospringback.model.Todo;
import com.gbloch.todospringback.services.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Mock
    private TodoService todoService;
    @InjectMocks
    private TodoController todoController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
    }

    @Test
    void getAllTodos() throws Exception {
        // Given
        final List<Todo> todos = Arrays.asList(
                Todo.builder()
                        .id(0L)
                        .username(USERNAME)
                        .description(DESCRIPTION)
                        .targetDate(new Date())
                        .isDone(false)
                        .build(),
                Todo.builder()
                        .id(1L)
                        .username(USERNAME)
                        .description("Become an expert at Node.js")
                        .targetDate(new Date())
                        .isDone(false)
                        .build()
        );
        given(todoService.findAll()).willReturn(todos);

        // When
        mockMvc.perform(get("/api/users/gbloch/todos"))

                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", equalTo(0)))
                .andExpect(jsonPath("$[0].username", equalTo(USERNAME)))
                .andExpect(jsonPath("$[0].description", equalTo(DESCRIPTION)))
                .andExpect(jsonPath("$[0].done", equalTo(false)));
    }
}
