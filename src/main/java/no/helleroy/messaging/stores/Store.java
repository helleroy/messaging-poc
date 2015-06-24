package no.helleroy.messaging.stores;

import no.helleroy.messaging.actionconsumers.ActionConsumer;
import no.helleroy.messaging.flux.Reactive;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class Store<T> implements Reactive<T> {

    private final List<T> messages;
    private final List<Consumer<T>> consumers;

    public Store() {
        this(new ArrayList<>());
    }

    public Store(List<T> messages) {
        this.messages = messages;
        this.consumers = new ArrayList<>();
    }

    public List<T> getMessages() {
        return new ArrayList<>(messages);
    }

    @Override
    public void register(Consumer<T> consumer) {
        consumers.add(consumer);
    }

    protected void publishMessage(T message) {
        messages.add(message);
        consumers.forEach(f -> f.accept(message));
    }

    protected abstract void subscribe(ActionConsumer<T> actionConsumer);
}
