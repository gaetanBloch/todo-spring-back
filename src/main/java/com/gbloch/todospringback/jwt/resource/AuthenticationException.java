package com.gbloch.todospringback.jwt.resource;

/**
 * @author Gaëtan Bloch
 * Created on 03/05/2020
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
