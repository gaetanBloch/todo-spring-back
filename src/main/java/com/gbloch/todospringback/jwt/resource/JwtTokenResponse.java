package com.gbloch.todospringback.jwt.resource;

import java.io.Serializable;

/**
 * @author Gaëtan Bloch
 * Created on 03/05/2020
 */
public class JwtTokenResponse implements Serializable {

    private static final long serialVersionUID = 8317676219297719109L;

    private final String token;

    public JwtTokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
