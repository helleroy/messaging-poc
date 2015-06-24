package no.helleroy.messaging.flux;

import java.util.function.Consumer;

public interface Publisher<T> {
    void register(Consumer<T> consumer);

    void publish(T message);
}
