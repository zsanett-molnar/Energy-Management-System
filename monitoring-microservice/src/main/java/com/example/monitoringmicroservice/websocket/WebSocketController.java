package com.example.monitoringmicroservice.websocket;


import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // This method can be called from any part of your backend to send a notification to the frontend
    public void sendNotificationToFrontend(String message) {
        messagingTemplate.convertAndSend("/topic/notification", message);
    }
}