package com.example.chatmicroservice.entities;

public class TypingMessage {

    private String sender;
    private String reciever;
    private boolean isTyping;

    public TypingMessage(String sender, String reciever, boolean isTyping) {
        this.sender = sender;
        this.reciever = reciever;
        this.isTyping = isTyping;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public boolean isTyping() {
        return isTyping;
    }

    public void setTyping(boolean typing) {
        isTyping = typing;
    }
}
