package no.helleroy.messaging.actionconsumers;

import no.helleroy.messaging.domain.ConnectionMessage;
import no.helleroy.messaging.listeners.ConnectEventListener;
import no.helleroy.messaging.listeners.DisconnectEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserActionConsumer extends ActionConsumer<ConnectionMessage> {

    @Autowired
    public UserActionConsumer(ConnectEventListener connectEventListener, DisconnectEventListener disconnectEventListener) {
        subscribe(connectEventListener);
        subscribe(disconnectEventListener);
    }
}
