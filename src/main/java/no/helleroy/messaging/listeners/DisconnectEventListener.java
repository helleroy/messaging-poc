package no.helleroy.messaging.listeners;

import no.helleroy.messaging.domain.ConnectionMessage;
import no.helleroy.messaging.flux.RegisterablePublisher;
import no.helleroy.messaging.registries.UserRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;

import static org.springframework.messaging.simp.SimpMessageHeaderAccessor.getUser;

@Component
public class DisconnectEventListener extends RegisterablePublisher<ConnectionMessage> implements ApplicationListener<SessionDisconnectEvent> {

    private UserRegistry userRegistry;

    @Autowired
    public DisconnectEventListener(UserRegistry userRegistry) {
        this.userRegistry = userRegistry;
    }

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        Principal user = getUser(event.getMessage().getHeaders());
        publish(new ConnectionMessage(userRegistry.users.get(user.getName()), false));
    }
}
