package game.myStrategy.lib.events;

import java.util.function.Consumer;

public class EventListener<T> {
    private final Consumer<T> execute;
    private final EventSander<T> eventSander;

    public EventListener(Consumer<T> exe, EventSander<T> eventSander) {
        this.execute = exe;
        this.eventSander = eventSander;
    }

    public void processingEvent(T e) {
        execute.accept(e);
    }
    public void unsubscribe() {
        eventSander.remove(this);
    }
}
