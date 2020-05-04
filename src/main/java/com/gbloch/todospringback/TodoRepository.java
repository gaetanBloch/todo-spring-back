package com.gbloch.todospringback;

import com.gbloch.todospringback.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gaëtan Bloch
 * Created on 04/05/2020
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
