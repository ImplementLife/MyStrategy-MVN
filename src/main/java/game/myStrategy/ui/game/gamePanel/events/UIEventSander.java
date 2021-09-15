package game.myStrategy.ui.game.gamePanel.events;

import game.myStrategy.lib.events.EventListener;
import game.myStrategy.lib.events.EventSander;

import java.util.HashMap;
import java.util.function.Consumer;

public class UIEventSander extends EventSander<Event> {
    private final HashMap<Integer, State> states = new HashMap<>();

    public void addEvent(Event e) {
        super.addEvent(e);
        //soutStates(e);
    }

    private void printStates(Event e) {
        if (!e.isMouseKeyCode()) states.put(e.getKeyCode(), e.getState());
        System.out.println("////////////////// key = " + Event.getKeyText(e.getKeyCode()) + " - " + e.getState());
        for (int i : states.keySet()) System.out.printf("key=%s, state=%s%n", Event.getKeyText(i), states.get(i));

    }

    public State getStateButton(int keyCode) {
        return states.get(keyCode);
    }

    @Override
    public UIEventListener registerListener(Consumer<Event> execute) {
        UIEventListener eventListener = new UIEventListener(execute, this);
        listeners.add(eventListener);
        return eventListener;
    }
}
