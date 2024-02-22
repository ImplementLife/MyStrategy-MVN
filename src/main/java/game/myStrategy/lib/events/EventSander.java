package game.myStrategy.lib.events;

import game.myStrategy.lib.noConcurrent.NoConcurrentList;

import java.util.function.Consumer;

public class EventSander<T> {
    protected final NoConcurrentList<EventListener<T>> listeners = new NoConcurrentList<>();

    public void addEvent(T e) {
        listeners.forEach(l -> l.processingEvent(e));
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
