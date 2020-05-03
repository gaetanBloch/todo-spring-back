package com.gbloch.todospringback.controllers;

import com.gbloch.todospringback.model.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gaëtan Bloch
 * Created on 03/05/2020
 */
@RestController
final class BasicAuthenticationController extends AbstractController {
    static final String AUTH_MESSAGE = "You are authenticated";

    @GetMapping("/basicauth")
    public Authentication authenticate() {
        return new Authentication(AUTH_MESSAGE);
    }
}
