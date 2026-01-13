package com.example.Blocks.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "verification_tokens")
@Data
public class VerificationToken {

    @Id
    private String id;
    private String email;
    private String code;
    private long expiresAt;
}
