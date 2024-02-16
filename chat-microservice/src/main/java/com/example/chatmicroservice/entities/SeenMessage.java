package com.example.chatmicroservice.entities;

public class SeenMessage {

    private String sender;
    private String reciever;
    private boolean seen;

    public SeenMessage(String sender, String reciever, boolean seen) {
        this.sender = sender;
        this.reciever = reciever;
        this.seen = seen;
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

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
