package no.helleroy.messaging.clients.outbound;

import no.helleroy.messaging.flux.Publisher;
import no.helleroy.messaging.flux.Subscriber;

public abstract class OutboundClient<T> implements Publisher<T>, Subscriber<T> {
}
