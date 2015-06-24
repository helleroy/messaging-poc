package no.helleroy.messaging.client;

import no.helleroy.messaging.domain.ChatMessage;
import no.helleroy.messaging.flux.Publisher;
import no.helleroy.messaging.flux.Subscriber;
import no.helleroy.messaging.services.ChatService;
import no.helleroy.messaging.stores.ChatStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ChatMessageWebSocketClient implements Publisher<ChatMessage>, Subscriber<ChatMessage> {

    private ChatService chatService;

    @Autowired
    public ChatMessageWebSocketClient(ChatService chatService, ChatStore chatStore) {
        chatStore.register(this::publish);
        this.chatService = chatService;
    }

    @Override
    public void subscribe(Publisher<ChatMessage> publisher) {
        publisher.register(this::publish);
    }

    @Override
    public void publish(ChatMessage message) {
        if (message.getReceiver() != null) {
            chatService.sendToUser(message.getReceiver(), message);
        } else {
            chatService.broadcast(message);
        }
    }

    @Override
    public void register(Consumer<ChatMessage> consumer) {
    }
}
