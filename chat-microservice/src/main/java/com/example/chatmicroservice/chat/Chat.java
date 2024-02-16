package com.example.chatmicroservice.chat;

import com.example.chatmicroservice.entities.Message;
import com.example.chatmicroservice.entities.SeenMessage;
import com.example.chatmicroservice.entities.TypingMessage;
import com.example.chatmicroservice.websocket.WebsocketController;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class Chat {

    private final SimpMessagingTemplate messagingTemplate;

    public Chat(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(String receiver, Message chatMessage) {
        messagingTemplate.convertAndSend(receiver, chatMessage);
    }

    public void sendTypingMessage(String destination, TypingMessage chatMessage) {
        messagingTemplate.convertAndSend(destination, chatMessage);
    }

    public void sendSeenNotification(String destination, SeenMessage seenMessage) {
        messagingTemplate.convertAndSend(destination, seenMessage);
    }
}
