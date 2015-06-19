package no.helleroy.messaging.services;

import no.helleroy.messaging.domain.UserLoginMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MessageSendingOperations<String> messageTemplate;

    public void sendUserLoginMessage(UserLoginMessage message) {
        messageTemplate.convertAndSend("/chat/users", message);
    }
}
