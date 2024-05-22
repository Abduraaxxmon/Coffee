package com.example.democoffee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")

public class HelloController {
    @GetMapping
    public String hello() {
        System.out.println();
        return "server is running";
    }
}
