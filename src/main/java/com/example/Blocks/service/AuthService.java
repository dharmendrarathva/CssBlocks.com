package com.example.Blocks.service;

import com.example.Blocks.dto.*;
import com.example.Blocks.entity.*;
import com.example.Blocks.repository.UserRepository;
import com.example.Blocks.repository.VerificationTokenRepository;
import com.example.Blocks.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    private final UserRepository repo;
    private final VerificationTokenRepository tokenRepo;
    private final EmailService emailService;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthService(
            UserRepository repo,
            VerificationTokenRepository tokenRepo,
            EmailService emailService,
            PasswordEncoder encoder,
            JwtService jwtService,
            AuthenticationManager authManager) {

        this.repo = repo;
        this.tokenRepo = tokenRepo;
        this.emailService = emailService;
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    public void register(RegisterRequest request) {

        if (repo.existsByEmail(request.getEmail()))
            throw new RuntimeException("User already exists");

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole()));
        user.setEnabled(false);

        repo.save(user);    

        String code = String.valueOf(100000 + new Random().nextInt(900000));

        VerificationToken token = new VerificationToken();
        token.setEmail(user.getEmail());
        token.setCode(code);
        token.setExpiresAt(System.currentTimeMillis() + 30 * 60 * 1000);

        tokenRepo.save(token);

        emailService.sendVerification(user.getEmail(), code);
    }

    public AuthResponse login(LoginRequest request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        String token = jwtService.generateToken(request.getEmail());
        return new AuthResponse(token);
    }
}
