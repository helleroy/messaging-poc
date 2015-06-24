package no.helleroy.messaging.actionconsumers;

import no.helleroy.messaging.clients.inbound.ChatController;
import no.helleroy.messaging.domain.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatActionConsumer extends ActionConsumer<ChatMessage> {

    @Autowired
    public ChatActionConsumer(ChatController chatController) {
        subscribe(chatController);
    }
}
