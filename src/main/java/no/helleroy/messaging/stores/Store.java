package no.helleroy.messaging.stores;

import no.helleroy.messaging.flux.Publisher;
import no.helleroy.messaging.flux.RegisterablePublisher;
import no.helleroy.messaging.flux.Subscriber;

import java.util.ArrayList;
import java.util.List;

public abstract class Store<T> extends RegisterablePublisher<T> implements Subscriber<T> {

    private final List<T> messages;

    public Store() {
        this.messages = new ArrayList<>();
    }

    public List<T> getMessages() {
        return new ArrayList<>(messages);
    }

    @Override
    public void publish(T message) {
        messages.add(message);
        super.publish(message);
    }

    @Override
    public void subscribe(Publisher<T> publisher) {
        publisher.register(this::publish);
    }
}
