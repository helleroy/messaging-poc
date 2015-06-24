package no.helleroy.messaging.actionconsumers;

import no.helleroy.messaging.controllers.ChatController;
import no.helleroy.messaging.domain.ChatMessage;
import no.helleroy.messaging.flux.Reactive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatActionConsumer extends ActionConsumer<ChatMessage> {

    @Autowired
    public ChatActionConsumer(ChatController chatController) {
        super();
        this.subscribe(chatController);
    }

    @Override
    protected void subscribe(Reactive<ChatMessage> controller) {
        controller.register(this::publishMessage);
    }
}
