package no.helleroy.messaging.listeners;

import no.helleroy.messaging.domain.UserLoginMessage;
import no.helleroy.messaging.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class LoginEventListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        userService.sendUserLoginMessage(new UserLoginMessage(event.getAuthentication().getName(), true));
    }
}
