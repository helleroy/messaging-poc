package no.helleroy.messaging.domain;

public class Channel {
    private String name;
    private boolean personal;

    public String getName() {
        return name;
    }

    public Channel setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isPersonal() {
        return personal;
    }

    public Channel setPersonal(boolean personal) {
        this.personal = personal;
        return this;
    }
}
