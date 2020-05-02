package com.gbloch.todospringback.controllers;

import com.gbloch.todospringback.model.HelloWorld;
import org.springframework.web.bind.annotation.*;

/**
 * @author Gaëtan Bloch
 * Created on 02/05/2020
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController()
@RequestMapping("/api")
public class HelloWorldController {
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World from the Server";
    }

    @GetMapping("/hello-world-model")
    public HelloWorld helloWorldModel() {
        return new HelloWorld("Hello World from the Server");
    }

    @GetMapping("/hello-world/{name}")
    public HelloWorld helloWorldName(@PathVariable String name) {
        return new HelloWorld(String.format("Hello World from the server %s", name));
    }
}
