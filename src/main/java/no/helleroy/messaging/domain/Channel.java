package no.helleroy.messaging.domain;

public class Channel {
    private String name;
    private boolean isPersonal;

    public String getName() {
        return name;
    }

    public Channel setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isPersonal() {
        return isPersonal;
    }

    public Channel setIsPersonal(boolean isPersonal) {
        this.isPersonal = isPersonal;
        return this;
    }
}
