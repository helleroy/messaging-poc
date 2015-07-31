package no.helleroy.messaging.clients.outbound;

import no.helleroy.messaging.flux.Publisher;
import no.helleroy.messaging.flux.Subscriber;

import java.util.function.Consumer;

public abstract class OutboundClient<T> implements Publisher<T>, Subscriber<T> {
    @Override
    public void register(Consumer<T> consumer) {
        // NOOP
    }
}
