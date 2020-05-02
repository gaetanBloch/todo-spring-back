package com.gbloch.todospringback.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.gbloch.todospringback.controllers.HelloWorldController.HELLO_WORLD;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Gaëtan Bloch
 * Created on 02/05/2020
 */
@ExtendWith(MockitoExtension.class)
class HelloWorldControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
    }

    @Test
    void helloWorldTest() throws Exception {
        // When
        mockMvc.perform(get("/api/hello-world"))

                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo(HELLO_WORLD)));
    }

    @Test
    void helloWorldModelTest() throws Exception {
        // When
        mockMvc.perform(get("/api/hello-world-model"))

                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", equalTo(HELLO_WORLD)));
    }

    @Test
    void helloWorldNameTest() throws Exception {
        // When
        mockMvc.perform(get("/api/hello-world/name"))

                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", equalTo(HELLO_WORLD + ", name")));
    }
}
