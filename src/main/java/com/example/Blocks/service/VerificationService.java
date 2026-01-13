package com.example.Blocks.service;

import com.example.Blocks.entity.User;
import com.example.Blocks.repository.UserRepository;
import com.example.Blocks.repository.VerificationTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {

    private final VerificationTokenRepository repo;
    private final UserRepository userRepo;

    public VerificationService(VerificationTokenRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public void verify(String email, String code) {

        var token = repo.findByEmailAndCode(email, code)
                .orElseThrow(() -> new RuntimeException("Invalid code"));

        if (token.getExpiresAt() < System.currentTimeMillis())
            throw new RuntimeException("Code expired");

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEnabled(true);
        userRepo.save(user);

        repo.delete(token);
    }
}
