package no.helleroy.messaging.stores;

import no.helleroy.messaging.actionconsumers.ActionConsumer;
import no.helleroy.messaging.actionconsumers.ChatActionConsumer;
import no.helleroy.messaging.domain.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatStore extends Store<ChatMessage> {

    @Autowired
    public ChatStore(ChatActionConsumer chatActionConsumer) {
        super();
        this.subscribe(chatActionConsumer);
    }

    @Override
    protected void subscribe(ActionConsumer<ChatMessage> actionConsumer) {
        actionConsumer.register(this::publishMessage);
    }
}
