package com.gbloch.todospringback;

import com.gbloch.todospringback.model.Todo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Gaëtan Bloch
 * Created on 04/05/2020
 */
public final class TestUtils {
    public static final String USERNAME = "gbloch";
    public static final String DESCRIPTION = "Become an expert at Angular";
    public static final Todo TODO = Todo.builder()
            .id(1L)
            .username(USERNAME)
            .description(DESCRIPTION)
            .targetDate(new Date())
            .isDone(false)
            .build();
    public static final List<Todo> TODOS = Arrays.asList(
            TODO,
            Todo.builder()
                    .id(2L)
                    .username(USERNAME)
                    .description("Become an expert at Node.js")
                    .targetDate(new Date())
                    .isDone(false)
                    .build()
    );

    private TestUtils() {
        // To prevent instantiation
        throw new UnsupportedOperationException();
    }
}
