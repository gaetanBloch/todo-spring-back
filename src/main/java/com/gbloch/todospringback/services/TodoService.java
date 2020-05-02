package com.gbloch.todospringback.services;

import com.gbloch.todospringback.model.Todo;

import java.util.List;

/**
 * @author Gaëtan Bloch
 * Created on 02/05/2020
 */
public interface TodoService {
    List<Todo> findAll();

    Todo findById(Long id);

    void deleteById(Long id);
}
