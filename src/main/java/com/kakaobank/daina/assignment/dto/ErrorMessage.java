package com.kakaobank.daina.assignment.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessage {
    private List<String> messages = new ArrayList<>();

    public ErrorMessage() {
    }

    public ErrorMessage(String message) {
        this.messages = List.of(message);
    }

    public ErrorMessage(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
