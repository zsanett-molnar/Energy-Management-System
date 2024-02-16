package com.example.chatmicroservice.websocket;

import com.example.chatmicroservice.chat.Chat;
import com.example.chatmicroservice.entities.Message;
import com.example.chatmicroservice.entities.SeenMessage;
import com.example.chatmicroservice.entities.TypingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class WebsocketController {
    private final Chat chat;

    @Autowired
    public WebsocketController(Chat chat) {
        this.chat = chat;
    }

    @MessageMapping("/send")
    public void handleChatMessage(@Payload Message chatMessage) {
        chat.sendMessage("/topic/chat", chatMessage);
    }

    @MessageMapping("/typing")
    public void handleTypingNotification(@Payload TypingMessage typingMessage) {
        chat.sendTypingMessage("/topic/typing", typingMessage);
    }

    @MessageMapping("/seen")
    public void handleSeenNotification(@Payload SeenMessage seenMessage) {
        chat.sendSeenNotification("/topic/seen", seenMessage);
    }

}
