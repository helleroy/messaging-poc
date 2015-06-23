package no.helleroy.messaging.listeners;

import no.helleroy.messaging.domain.ConnectionMessage;
import no.helleroy.messaging.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class LoginEventListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    private UserService userService;

    @Autowired
    public LoginEventListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        userService.sendUserLoginMessage(new ConnectionMessage(event.getAuthentication().getName(), true));
    }
}
