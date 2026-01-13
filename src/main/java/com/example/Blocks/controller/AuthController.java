package com.example.Blocks.controller;

import com.example.Blocks.dto.*;
import com.example.Blocks.service.AuthService;
import com.example.Blocks.service.VerificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;
    private final VerificationService verificationService;

    public AuthController(AuthService service, VerificationService verificationService) {
        this.service = service;
        this.verificationService = verificationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        service.register(request);
    }

    @PostMapping("/verify")
    public void verify(@RequestBody VerifyRequest request) {
        verificationService.verify(request.getEmail(), request.getCode());
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return service.login(request);
    }
}
