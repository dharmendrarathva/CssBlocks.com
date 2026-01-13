package com.example.Blocks.repository;

import com.example.Blocks.dto.VerificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends MongoRepository<VerificationToken, String> {
    Optional<VerificationToken> findByEmailAndCode(String email, String code);
}
