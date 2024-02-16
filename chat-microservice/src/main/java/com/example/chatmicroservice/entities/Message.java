package com.example.chatmicroservice.entities;

import java.time.LocalDateTime;

public class Message {

    private String sender;
    private String reciever;
    private String information;
    private LocalDateTime timeSent;

    public Message(String sender, String reciever) {
        this.sender = sender;
        this.reciever = reciever;
        this.timeSent = LocalDateTime.now();
    }

    public Message() {
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public LocalDateTime getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(LocalDateTime timeSent) {
        this.timeSent = timeSent;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", reciever='" + reciever + '\'' +
                ", information='" + information + '\'' +
                ", timeSent=" + timeSent +
                '}';
    }
}
