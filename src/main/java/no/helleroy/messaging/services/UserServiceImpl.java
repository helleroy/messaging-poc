package no.helleroy.messaging.services;

import no.helleroy.messaging.domain.ConnectionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public static final String DESTINATION_USERS = "/chat/users";

    private SimpMessageSendingOperations messageTemplate;

    @Autowired
    public UserServiceImpl(SimpMessageSendingOperations messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @Override
    public void sendUserLoginMessage(ConnectionMessage message) {
        messageTemplate.convertAndSend(DESTINATION_USERS, message);
    }
}
