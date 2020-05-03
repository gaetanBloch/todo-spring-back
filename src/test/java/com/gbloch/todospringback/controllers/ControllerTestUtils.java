package com.gbloch.todospringback.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Gaëtan Bloch
 * Created on 03/05/2020
 */
final class ControllerTestUtils {
    private ControllerTestUtils() {
        // To prevent instantiation
        throw new UnsupportedOperationException();
    }

    static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
