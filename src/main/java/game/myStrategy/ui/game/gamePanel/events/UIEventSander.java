package game.myStrategy.ui.game.gamePanel.events;

import game.myStrategy.lib.events.EventSander;

import java.util.HashMap;
import java.util.function.Consumer;

public class UIEventSander extends EventSander<UIEvent> {
    private final HashMap<Integer, State> states = new HashMap<>();

    public void addEvent(UIEvent e) {
        super.addEvent(e);
        //printStates(e);
    }

    private void printStates(UIEvent e) {
        if (!e.isMouseKeyCode()) states.put(e.getKeyCode(), e.getState());
        System.out.println("////////////////// key = " + UIEvent.getKeyText(e.getKeyCode()) + " - " + e.getState());
        for (int i : states.keySet()) System.out.printf("key=%s, state=%s%n", UIEvent.getKeyText(i), states.get(i));

    }

    public State getStateButton(int keyCode) {
        return states.get(keyCode);
    }

    @Override
    public UIEventListener registerListener(Consumer<UIEvent> execute) {
        UIEventListener eventListener = new UIEventListener(execute, this);
        listeners.add(eventListener);
        return eventListener;
    }
}
