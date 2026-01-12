package com.example.Blocks.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/hello")
    public String admin() {
        return "Hello ADMIN";
    }
}
