package com.gbloch.todospringback.exceptions;

/**
 * @author Gaetan Bloch
 * Created on 02/05/2020
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
