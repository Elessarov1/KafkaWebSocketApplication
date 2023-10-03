package com.example.kafkawebsocketapplication.controller;

import com.example.kafkawebsocketapplication.service.KafkaMessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebSocketController {
    private KafkaMessageService kafkaMessageService;

    @MessageMapping("/subscribe")
    public void subscribe(@Payload Map<String, Integer> payload) {
        int offset = payload.get("offset");
        int limit = payload.get("limit");
        kafkaMessageService.getMessages(offset, limit);
    }
}
