package com.example.Blocks.service;

import com.example.Blocks.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
@Service
public class ResendService {

    private final WebClient client;
    private final String from;

    public ResendService(
            @Value("${resend.api-key}") String apiKey,
            @Value("${resend.from}") String from) {

        this.from = from;

        this.client = WebClient.builder()
                .baseUrl("https://api.resend.com")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public void sendEmail(EmailRequest req) {
        client.post()
                .uri("/emails")
                .bodyValue(Map.of(
                        "from", from,
                        "to", req.getTo(),
                        "subject", req.getSubject(),
                        "html", req.getHtml()
                ))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
