package no.helleroy.messaging.listeners;

import no.helleroy.messaging.domain.ConnectionMessage;
import no.helleroy.messaging.domain.User;
import no.helleroy.messaging.flux.RegisterablePublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.security.Principal;
import java.util.Date;

import static org.springframework.messaging.simp.SimpMessageHeaderAccessor.getUser;

@Component
public class ConnectEventListener extends RegisterablePublisher<ConnectionMessage> implements ApplicationListener<SessionConnectedEvent> {

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        Principal principal = getUser(event.getMessage().getHeaders());
        publish(new ConnectionMessage(new User().setName(principal.getName()).setLastActive(new Date()), true));
    }
}
