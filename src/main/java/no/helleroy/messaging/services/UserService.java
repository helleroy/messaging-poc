package no.helleroy.messaging.services;

import no.helleroy.messaging.domain.ConnectionMessage;

public interface UserService {
    void sendUserLoginMessage(ConnectionMessage message);
}
