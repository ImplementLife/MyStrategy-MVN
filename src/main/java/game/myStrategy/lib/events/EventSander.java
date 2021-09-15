package game.myStrategy.lib.events;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.function.Consumer;

public class EventSander<T> {
    protected final HashSet<EventListener<T>> listeners = new HashSet<>();

    public void addEvent(T e) {
        for (EventListener<T> a : listeners) {
            try {
                a.processingEvent(e);
            } catch (ConcurrentModificationException ignore) {}
        }
    }

    public EventListener<T> registerListener(Consumer<T> execute) {
        EventListener<T> eventListener = new EventListener<T>(execute, this);
        listeners.add(eventListener);
        return eventListener;
    }

    public void remove(EventListener<T> eventListener) {
        listeners.remove(eventListener);
    }

}
