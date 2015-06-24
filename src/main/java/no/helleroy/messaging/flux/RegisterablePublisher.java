package no.helleroy.messaging.flux;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class RegisterablePublisher<T> implements Publisher<T> {

    private final List<Consumer<T>> consumers;

    protected RegisterablePublisher() {
        consumers = new ArrayList<>();
    }

    @Override
    public void register(Consumer<T> consumer) {
        consumers.add(consumer);
    }

    @Override
    public void publish(T message) {
        consumers.forEach(f -> f.accept(message));
    }
}
