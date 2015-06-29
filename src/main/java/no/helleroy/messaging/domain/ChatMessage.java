package no.helleroy.messaging.domain;

import java.io.Serializable;

public class ChatMessage implements Serializable {

    private String message, sender, receiver;
    private Channel channel;

    public String getMessage() {
        return message;
    }

    public ChatMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public ChatMessage setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public String getReceiver() {
        return receiver;
    }

    public ChatMessage setReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public Channel getChannel() {
        return channel;
    }

    public ChatMessage setChannel(Channel channel) {
        this.channel = channel;
        return this;
    }
}
