package no.helleroy.messaging.flux;

import java.util.function.Consumer;

public interface Reactive<T> {

    void register(Consumer<T> consumer);
}
