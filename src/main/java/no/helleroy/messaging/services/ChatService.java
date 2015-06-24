package no.helleroy.messaging.services;

import no.helleroy.messaging.domain.ChatMessage;

public interface ChatService {
    void broadcast(ChatMessage message);

    void sendToUser(String username, ChatMessage message);
}
