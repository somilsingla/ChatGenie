package com.example.chatgenie.Model;

// Model class for the chat item, which contains the message and the sender details

public class Chat {

    private String message;
    private String sender;

    public Chat(String message) {
        this.message = message;
    }

    public Chat(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

    public Chat() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
