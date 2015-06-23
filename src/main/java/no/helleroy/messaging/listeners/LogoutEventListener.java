package no.helleroy.messaging.listeners;

import no.helleroy.messaging.domain.ConnectionMessage;
import no.helleroy.messaging.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

@Component
public class LogoutEventListener implements ApplicationListener<SessionDestroyedEvent> {

    private UserService userService;

    @Autowired
    public LogoutEventListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        userService.sendUserLoginMessage(new ConnectionMessage(event.getId(), false));
    }
}
