package com.example.trendyol.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailConsumerService {

    private final ObjectMapper objectMapper;
    private final JavaMailSender mailSender;

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeEmail(String message) {
        try {

            SimpleMailMessage simpleMailMessage = objectMapper.readValue(message, SimpleMailMessage.class);
            mailSender.send(simpleMailMessage);

        } catch (Exception e) {
            e.getMessage();
        }
    }

}
