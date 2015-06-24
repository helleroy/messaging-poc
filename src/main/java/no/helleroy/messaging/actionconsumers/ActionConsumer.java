package no.helleroy.messaging.actionconsumers;

import no.helleroy.messaging.flux.Reactive;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class ActionConsumer<T> implements Reactive<T> {

    private final List<Consumer<T>> consumers;

    public ActionConsumer() {
        this.consumers = new ArrayList<>();
    }

    @Override
    public void register(Consumer<T> consumer) {
        consumers.add(consumer);
    }

    protected void publishMessage(T message) {
        consumers.forEach(f -> f.accept(message));
    }

    protected abstract void subscribe(Reactive<T> controller);
}
