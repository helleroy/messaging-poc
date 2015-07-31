package no.helleroy.messaging.domain;

public class ChatMessage extends Message {

    private String message;
    private Channel channel;

    public String getMessage() {
        return message;
    }

    public ChatMessage setMessage(String message) {
        this.message = message;
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
