package com.cicd.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CiCdController {

    @RequestMapping("/greet")
    public String helloWorld() {
        return "Hello, this is a CI/CD demonstration application!";
    }
}
