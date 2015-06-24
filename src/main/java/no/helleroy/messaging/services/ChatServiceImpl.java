package no.helleroy.messaging.services;

import no.helleroy.messaging.domain.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    public static final String DESTINATION_MESSAGES = "/chat/messages";

    private SimpMessageSendingOperations messageTemplate;

    @Autowired
    public ChatServiceImpl(SimpMessageSendingOperations messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @Override
    public void broadcast(ChatMessage message) {
        messageTemplate.convertAndSend(DESTINATION_MESSAGES, message);
    }

    @Override
    public void sendToUser(String username, ChatMessage message) {
        messageTemplate.convertAndSendToUser(username, DESTINATION_MESSAGES, message);
    }
}
