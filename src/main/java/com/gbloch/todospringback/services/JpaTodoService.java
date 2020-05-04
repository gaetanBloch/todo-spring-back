package com.gbloch.todospringback.services;

import com.gbloch.todospringback.TodoRepository;
import com.gbloch.todospringback.exceptions.ResourceNotFoundException;
import com.gbloch.todospringback.model.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Gaëtan Bloch
 * Created on 04/05/2020
 */
@RequiredArgsConstructor
@Service
@Primary
final class JpaTodoService implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public List<Todo> findAllByUsername(String username) {
        return todoRepository.findAllByUsername(username);
    }

    @Override
    public Todo findById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Todo not found for id = " + id);
        });
    }

    @Override
    public void deleteById(Long id) {
        Todo todo = findById(id);
        todoRepository.deleteById(todo.getId());
    }

    @Override
    public Todo update(Todo todo) {
        findById(todo.getId());
        return todoRepository.save(todo);
    }

    @Override
    public Todo create(Todo todo) {
        return todoRepository.save(todo);
    }
}
