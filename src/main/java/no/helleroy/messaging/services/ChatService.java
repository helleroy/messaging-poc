package no.helleroy.messaging.services;

import no.helleroy.messaging.domain.ChatMessage;
import no.helleroy.messaging.domain.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private SimpMessageSendingOperations messageTemplate;

    public void sendToUser(String username, ChatMessage message) {
        messageTemplate.convertAndSendToUser(username, Destination.Chat.MESSAGES, message);
    }
}
