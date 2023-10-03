package com.example.kafkawebsocketapplication;

import org.springframework.web.socket.WebSocketMessage;

public class CustomWebSocketMessage<T> implements WebSocketMessage<T> {
    private T payload;
    private int payloadLength;
    private boolean last;

    public CustomWebSocketMessage(T payload, int payloadLength, boolean last) {
        this.payload = payload;
        this.payloadLength = payloadLength;
        this.last = last;
    }

    @Override
    public T getPayload() {
        return payload;
    }

    @Override
    public int getPayloadLength() {
        return payloadLength;
    }

    @Override
    public boolean isLast() {
        return last;
    }
}
