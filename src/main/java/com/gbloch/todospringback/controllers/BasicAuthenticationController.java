package com.gbloch.todospringback.controllers;

import com.gbloch.todospringback.model.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gaëtan Bloch
 * Created on 03/05/2020
 */
@RestController
public class BasicAuthenticationController extends AbstractController {

    @GetMapping("/basicauth")
    public Authentication authenticate() {
        return new Authentication("You are authenticated");
    }
}
