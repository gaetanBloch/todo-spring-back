package com.gbloch.todospringback.model;

import lombok.*;

import java.util.Date;

/**
 * @author Gaëtan Bloch
 * Created on 02/05/2020
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {
    private Long id;
    private String username;
    private String description;
    private Date targetDate;
    private boolean isDone;
}
