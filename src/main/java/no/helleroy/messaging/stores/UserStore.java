package no.helleroy.messaging.stores;

import no.helleroy.messaging.actionconsumers.UserActionConsumer;
import no.helleroy.messaging.domain.ConnectionMessage;
import no.helleroy.messaging.registries.UserRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserStore extends Store<ConnectionMessage> {

    private UserRegistry userRegistry;

    @Autowired
    public UserStore(UserActionConsumer userActionConsumer, UserRegistry userRegistry) {
        this.userRegistry = userRegistry;
        subscribe(userActionConsumer);
    }

    @Override
    public void publish(ConnectionMessage message) {
        if (message.isConnected()) {
            userRegistry.users.put(message.getUser().getName(), message.getUser());
        } else {
            userRegistry.users.remove(message.getUser().getName());
        }
        super.publish(message);
    }
}
