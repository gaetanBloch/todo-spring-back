package com.gbloch.todospringback.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Gaëtan Bloch
 * Created on 02/05/2020
 */
@Data
@AllArgsConstructor
@Builder
public class Todo {
    private Long id;
    private String username;
    private String description;
    private Date targetDate;
    private boolean isDone;
}
