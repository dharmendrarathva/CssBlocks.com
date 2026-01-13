package com.example.Blocks.controller;

import com.example.Blocks.dto.EmailRequest;
import com.example.Blocks.service.ResendService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final ResendService service;

    public EmailController(ResendService service) {
        this.service = service;
    }

    @PostMapping("/send")
    public void send(@RequestBody EmailRequest req) {
        service.sendEmail(req);
    }
}
