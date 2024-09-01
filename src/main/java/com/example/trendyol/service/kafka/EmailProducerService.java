package com.example.trendyol.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailProducerService {

    @Value("${spring.kafka.producer.topic}")
    private String topicName;

    @Value("${spring.mail.username}")
    private String senderMail;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendEmail(String recipient, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(senderMail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        simpleMailMessage.setTo(recipient);

        try {

            String emailMessageJson = objectMapper.writeValueAsString(simpleMailMessage);
            kafkaTemplate.send(topicName, emailMessageJson);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
