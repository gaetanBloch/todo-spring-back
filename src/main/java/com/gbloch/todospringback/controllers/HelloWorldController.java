package com.gbloch.todospringback.controllers;

import com.gbloch.todospringback.model.HelloWorld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gaëtan Bloch
 * Created on 02/05/2020
 */
@RestController
public class HelloWorldController {
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-model")
    public HelloWorld helloWorldModel() {
        return new HelloWorld("Hello World");
    }

    @GetMapping("/hello-world/{name}")
    public HelloWorld helloWorldName(@PathVariable String name) {
        return new HelloWorld(String.format("Hello World, %s", name));
    }
}
