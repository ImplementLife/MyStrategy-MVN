package game.myStrategy.lib.events;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Component
@Scope("singleton")
public class EventBus {
    private Map<String, EventSander<Event>> sanders = new HashMap<>();

    public void regSender(String name, EventSander<Event> sander) {
        sanders.put(name, sander);
    }
    public void regListener(String sanderName, Consumer<Event> listener) {
        EventSander<Event> eventSander = sanders.get(sanderName);
        eventSander.registerListener(listener);
    }

    public void deleteSender(String sanderName) {
        EventSander<Event> removedSander = sanders.remove(sanderName);

    }
}
