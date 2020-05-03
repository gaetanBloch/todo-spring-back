package com.gbloch.todospringback.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.gbloch.todospringback.controllers.BasicAuthenticationController.AUTH_MESSAGE;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Gaëtan Bloch
 * Created on 03/05/2020
 */
class BasicAuthenticationControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new BasicAuthenticationController()).build();
    }

    @Test
    void authenticateTest() throws Exception {
        // When
        mockMvc.perform(get("/api/basicauth"))

                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", equalTo(AUTH_MESSAGE)));
    }
}
