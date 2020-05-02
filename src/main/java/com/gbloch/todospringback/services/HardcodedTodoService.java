package com.gbloch.todospringback.services;

import com.gbloch.todospringback.model.Todo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Gaëtan Bloch
 * Created on 02/05/2020
 */
@Service
final class HardcodedTodoService implements TodoService {
    static final String USERNAME = "gbloch";
    static final String DESCRIPTION = "Become an expert at Angular";
    private static Long idCounter = 0L;
    private static final List<Todo> TODOS = Arrays.asList(
            Todo.builder()
                    .id(++idCounter)
                    .username(USERNAME)
                    .description(DESCRIPTION)
                    .targetDate(new Date())
                    .isDone(false)
                    .build(),
            Todo.builder()
                    .id(++idCounter)
                    .username(USERNAME)
                    .description("Become an expert at Node.js")
                    .targetDate(new Date())
                    .isDone(false)
                    .build(),
            Todo.builder()
                    .id(++idCounter)
                    .username(USERNAME)
                    .description("Learn Reactjs")
                    .targetDate(new Date())
                    .isDone(false)
                    .build(),
            Todo.builder()
                    .id(++idCounter)
                    .username(USERNAME)
                    .description("Learn Ansible")
                    .targetDate(new Date())
                    .isDone(false)
                    .build()
    );

    @Override
    public List<Todo> findAll() {
        return TODOS;
    }
}
