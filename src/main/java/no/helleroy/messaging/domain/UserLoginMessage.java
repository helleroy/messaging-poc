package no.helleroy.messaging.domain;

public class UserLoginMessage {

    private String username;
    private boolean loggedIn;

    public UserLoginMessage(String username, boolean loggedIn) {
        this.username = username;
        this.loggedIn = loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
