package no.helleroy.messaging.domain;

public class ConnectionMessage {

    private User user;
    private boolean connected;

    public ConnectionMessage(User user, boolean connected) {
        this.user = user;
        this.connected = connected;
    }

    public User getUser() {
        return user;
    }

    public boolean isConnected() {
        return connected;
    }
}
