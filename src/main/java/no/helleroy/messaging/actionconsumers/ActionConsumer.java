package no.helleroy.messaging.actionconsumers;

import no.helleroy.messaging.flux.RegisterablePublisher;
import no.helleroy.messaging.flux.Publisher;
import no.helleroy.messaging.flux.Subscriber;

public abstract class ActionConsumer<T> extends RegisterablePublisher<T> implements Subscriber<T> {
    @Override
    public void subscribe(Publisher<T> publisher) {
        publisher.register(this::publish);
    }
}
