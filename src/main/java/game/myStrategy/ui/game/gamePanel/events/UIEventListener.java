package game.myStrategy.ui.game.gamePanel.events;

import game.myStrategy.lib.events.EventListener;

import java.util.function.Consumer;

public class UIEventListener extends EventListener<Event> {
    public UIEventListener(Consumer<Event> exe, UIEventSander eventSander) {
        super(exe, eventSander);
    }
}
