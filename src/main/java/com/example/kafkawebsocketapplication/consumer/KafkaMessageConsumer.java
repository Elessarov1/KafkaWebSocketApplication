package com.example.kafkawebsocketapplication.consumer;

import com.example.kafkawebsocketapplication.CustomWebSocketMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KafkaMessageConsumer {
    SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "my_topic")
    public void listen(ConsumerRecord<String, String> record) {
        String message = record.value();
        boolean isJson = isJson(message);

        if (isJson) {
            messagingTemplate.convertAndSend("/topic/messages", new CustomWebSocketMessage<>(message, message.length(), true));
        } else {
            messagingTemplate.convertAndSend("/topic/messages", new CustomWebSocketMessage<>(null, message.length(), true));
        }
    }

    public static boolean isJson(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.readTree(message);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
