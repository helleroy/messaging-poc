package no.helleroy.messaging.listeners;

import no.helleroy.messaging.components.UserRegistry;
import no.helleroy.messaging.domain.ConnectionMessage;
import no.helleroy.messaging.domain.User;
import no.helleroy.messaging.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.security.Principal;
import java.util.Date;

import static org.springframework.messaging.simp.SimpMessageHeaderAccessor.getUser;

@Component
public class ConnectEventListener implements ApplicationListener<SessionConnectedEvent> {

    private UserService userService;
    private UserRegistry userRegistry;

    @Autowired
    public ConnectEventListener(UserService userService, UserRegistry userRegistry) {
        this.userService = userService;
        this.userRegistry = userRegistry;
    }

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        Principal principal = getUser(event.getMessage().getHeaders());

        User user = new User().setName(principal.getName()).setLastActive(new Date());
        userRegistry.users.put(principal.getName(), user);
        userService.sendUserLoginMessage(new ConnectionMessage(user, true));
    }
}
