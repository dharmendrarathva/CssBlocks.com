package com.example.Blocks.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/hello")
    public String user() {
        return "Hello USER";
    }
}

