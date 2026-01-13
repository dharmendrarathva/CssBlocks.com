package com.example.Blocks.service;

import com.example.Blocks.dto.EmailRequest;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final ResendService resendService;

    public EmailService(ResendService resendService) {
        this.resendService = resendService;
    }

    public void sendVerification(String email, String code) {

        EmailRequest req = new EmailRequest();
        req.setTo(email);
        req.setSubject("Verify your account");
        req.setHtml("<h3>Your verification code: " + code + "</h3>");

        resendService.sendEmail(req);
    }
}
