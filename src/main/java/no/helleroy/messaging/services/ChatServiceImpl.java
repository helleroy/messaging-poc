package no.helleroy.messaging.services;

import no.helleroy.messaging.domain.ChatMessage;
import no.helleroy.messaging.domain.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private SimpMessageSendingOperations messageTemplate;

    @Autowired
    public ChatServiceImpl(SimpMessageSendingOperations messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @Override
    public void broadcast(ChatMessage message) {
        messageTemplate.convertAndSend(Destination.Chat.MESSAGES, message);
    }

    @Override
    public void sendToUser(String username, ChatMessage message) {
        messageTemplate.convertAndSendToUser(username, Destination.Chat.MESSAGES, message);
    }
}
