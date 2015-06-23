package no.helleroy.messaging.listeners;

import no.helleroy.messaging.components.UserRegistry;
import no.helleroy.messaging.domain.ConnectionMessage;
import no.helleroy.messaging.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;

import static org.springframework.messaging.simp.SimpMessageHeaderAccessor.getUser;

@Component
public class DisconnectEventListener implements ApplicationListener<SessionDisconnectEvent> {

    private UserService userService;
    private UserRegistry userRegistry;

    @Autowired
    public DisconnectEventListener(UserService userService, UserRegistry userRegistry) {
        this.userService = userService;
        this.userRegistry = userRegistry;
    }

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        Principal user = getUser(event.getMessage().getHeaders());

        userService.sendUserLoginMessage(new ConnectionMessage(userRegistry.users.get(user.getName()), false));
        userRegistry.users.remove(user.getName());
    }
}
