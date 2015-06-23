package no.helleroy.messaging.domain;

import java.util.Date;

public class User {

    private String name;
    private Date lastActive;

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Date getLastActive() {
        return lastActive;
    }

    public User setLastActive(Date lastActive) {
        this.lastActive = lastActive;
        return this;
    }
}
