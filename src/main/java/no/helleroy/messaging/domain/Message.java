package no.helleroy.messaging.domain;

import java.io.Serializable;
import java.util.Date;

public abstract class Message implements Serializable {

    protected final Date timestamp;
    protected String sender, receiver;

    public Message() {
        this.timestamp = new Date();
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getSender() {
        return sender;
    }

    public Message setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public String getReceiver() {
        return receiver;
    }

    public Message setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }
}
