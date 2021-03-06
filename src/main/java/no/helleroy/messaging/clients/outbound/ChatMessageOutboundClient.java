package no.helleroy.messaging.clients.outbound;

import no.helleroy.messaging.domain.ChatMessage;
import no.helleroy.messaging.flux.Publisher;
import no.helleroy.messaging.services.ChatService;
import no.helleroy.messaging.stores.ChatStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageOutboundClient extends OutboundClient<ChatMessage> {

    private ChatService chatService;

    @Autowired
    public ChatMessageOutboundClient(ChatService chatService, ChatStore chatStore) {
        this.chatService = chatService;
        subscribe(chatStore);
    }

    @Override
    public void subscribe(Publisher<ChatMessage> publisher) {
        publisher.register(this::publish);
    }

    @Override
    public void publish(ChatMessage message) {
        if (message.getReceiver() != null) {
            chatService.sendToUser(message.getReceiver(), message);
            if (!message.getReceiver().equals(message.getSender())) {
                chatService.sendToUser(message.getSender(), message);
            }
        } else {
            chatService.broadcast(message);
        }
    }
}
