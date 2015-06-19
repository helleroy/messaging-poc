package no.helleroy.messaging.controllers;

import no.helleroy.messaging.domain.ChatMessage;
import no.helleroy.messaging.domain.Destination;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/message")
    @SendTo(Destination.Chat.MESSAGES)
    public ChatMessage greeting(ChatMessage message) {
        return message;
    }
}
