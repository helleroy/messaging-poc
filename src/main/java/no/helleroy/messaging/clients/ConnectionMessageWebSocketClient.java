package no.helleroy.messaging.clients;

import no.helleroy.messaging.domain.ConnectionMessage;
import no.helleroy.messaging.flux.Publisher;
import no.helleroy.messaging.services.UserService;
import no.helleroy.messaging.stores.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ConnectionMessageWebSocketClient extends WebSocketClient<ConnectionMessage> {

    private UserService userService;

    @Autowired
    public ConnectionMessageWebSocketClient(UserService userService, UserStore userStore) {
        this.userService = userService;
        subscribe(userStore);
    }

    @Override
    public void publish(ConnectionMessage message) {
        userService.sendUserLoginMessage(message);
    }

    @Override
    public void subscribe(Publisher<ConnectionMessage> publisher) {
        publisher.register(this::publish);
    }

    @Override
    public void register(Consumer<ConnectionMessage> consumer) {
    }
}
