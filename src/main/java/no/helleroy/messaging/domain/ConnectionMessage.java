package no.helleroy.messaging.domain;

public class ConnectionMessage {

    private String username;
    private boolean connected;

    public ConnectionMessage(String username, boolean connected) {
        this.username = username;
        this.connected = connected;
    }

    public String getUsername() {
        return username;
    }

    public boolean isConnected() {
        return connected;
    }
}
