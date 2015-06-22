package no.helleroy.messaging.services;

import no.helleroy.messaging.domain.ConnectionMessage;
import no.helleroy.messaging.domain.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private SimpMessageSendingOperations messageTemplate;

    public void sendUserLoginMessage(ConnectionMessage message) {
        messageTemplate.convertAndSend(Destination.Chat.USERS, message);
    }
}
