package com.example.myapplication;

// Message.java
public class Message {
    private String content;
    private boolean isSender;

    public Message(String content, boolean isSender) {
        this.content = content;
        this.isSender = isSender;
    }

    public String getContent() {
        return content;
    }

    public boolean isSender() {
        return isSender;
    }
}
