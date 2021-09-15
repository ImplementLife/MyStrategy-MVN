package game.myStrategy.lib.events;

import game.myStrategy.lib.noConcurrent.NoConcurrentList;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.function.Consumer;

public class EventSander<T> {
    protected final NoConcurrentList<EventListener<T>> listeners = new NoConcurrentList<>();
//    protected final HashSet<EventListener<T>> listeners = new HashSet<>();

    public void addEvent(T e) {
        listeners.iterate(l -> l.processingEvent(e));
    }

    public EventListener<T> registerListener(Consumer<T> execute) {
        EventListener<T> eventListener = new EventListener<T>(execute, this);
        listeners.add(eventListener);
        return eventListener;
    }

    public void remove(EventListener<T> eventListener) {
        listeners.del(eventListener);
    }

}
