package com.example.kafkawebsocketapplication.service;

import com.example.kafkawebsocketapplication.CustomWebSocketMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import static com.example.kafkawebsocketapplication.consumer.KafkaMessageConsumer.isJson;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KafkaMessageService {
     SimpMessagingTemplate messagingTemplate;

    public void getMessages(int offset, int limit) {
        int currentOffset = offset;

        while (currentOffset < offset + limit) {
            String message = "message from kafka";
            boolean isJson = isJson(message);

            if (isJson) {
                messagingTemplate.convertAndSend("/topic/messages", new CustomWebSocketMessage<>(message, message.length(), true));
            } else {
                messagingTemplate.convertAndSend("/topic/messages", new CustomWebSocketMessage<>(null, message.length(), true));
            }

            currentOffset++;
        }
    }
}
