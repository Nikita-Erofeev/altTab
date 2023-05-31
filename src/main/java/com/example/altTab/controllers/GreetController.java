package com.example.altTab.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class GreetController {
    @GetMapping("/hello")
    public String greeting(@RequestParam(value="name", defaultValue = "World") String name){
        return "Hello, " + name;
    }

    @PostMapping("/login")
    public String login(@RequestBody String string){
        return "Received and send: " + string;
    }
}
