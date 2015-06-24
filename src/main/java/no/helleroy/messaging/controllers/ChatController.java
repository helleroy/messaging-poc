package no.helleroy.messaging.controllers;

import no.helleroy.messaging.domain.ChatMessage;
import no.helleroy.messaging.domain.Destination;
import no.helleroy.messaging.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/message")
    @SendTo(Destination.Chat.MESSAGES)
    public ChatMessage message(ChatMessage message) {
        return message;
    }

    @MessageMapping("/user/{username}/message")
    public void messageToUser(@DestinationVariable String username, ChatMessage message) {
        chatService.sendToUser(username, message);
    }

}
