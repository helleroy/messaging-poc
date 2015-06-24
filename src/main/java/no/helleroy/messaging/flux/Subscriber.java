package no.helleroy.messaging.flux;


public interface Subscriber<T> {
    void subscribe(Publisher<T> publisher);
}
