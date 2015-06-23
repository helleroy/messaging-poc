package no.helleroy.messaging.services;

import no.helleroy.messaging.domain.ChatMessage;

public interface ChatService {
    void sendToUser(String username, ChatMessage message);
}
