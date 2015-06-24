package no.helleroy.messaging.controllers;

import no.helleroy.messaging.domain.ChatMessage;
import no.helleroy.messaging.flux.RegisterablePublisher;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ChatController extends RegisterablePublisher<ChatMessage> {

    @MessageMapping("/message")
    public void message(ChatMessage message, Principal principal) {
        publish(message.setSender(principal.getName()));
    }

    @MessageMapping("/user/{username}/message")
    public void messageToUser(@DestinationVariable String username, ChatMessage message, Principal principal) {
        publish(message
                .setSender(principal.getName())
                .setReceiver(username));
    }
}
