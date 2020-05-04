package com.gbloch.todospringback.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Gaëtan Bloch
 * Created on 02/05/2020
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public abstract class AbstractController {
}
