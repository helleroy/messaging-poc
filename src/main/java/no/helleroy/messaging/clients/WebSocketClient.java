package no.helleroy.messaging.clients;

import no.helleroy.messaging.flux.Publisher;
import no.helleroy.messaging.flux.Subscriber;

public abstract class WebSocketClient<T> implements Publisher<T>, Subscriber<T> {
}
