package no.helleroy.messaging.controllers;

import no.helleroy.messaging.domain.ChatMessage;
import no.helleroy.messaging.domain.Destination;
import no.helleroy.messaging.flux.Reactive;
import no.helleroy.messaging.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Controller
public class ChatController implements Reactive<ChatMessage> {

    private ChatService chatService;
    private final List<Consumer<ChatMessage>> consumers;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
        this.consumers = new ArrayList<>();
    }

    @MessageMapping("/message")
    @SendTo(Destination.Chat.MESSAGES)
    public ChatMessage message(ChatMessage message) {
        consumers.forEach(c -> c.accept(message));
        return message;
    }

    @MessageMapping("/user/{username}/message")
    public void messageToUser(@DestinationVariable String username, ChatMessage message) {
        chatService.sendToUser(username, message);
    }

    @Override
    public void register(Consumer<ChatMessage> consumer) {
        consumers.add(consumer);
    }
}
