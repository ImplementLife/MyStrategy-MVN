package game.myStrategy.ui.game.gamePanel.events;

import game.myStrategy.lib.events.EventListener;

import java.util.function.Consumer;

public class UIEventListener extends EventListener<UIEvent> {
    public UIEventListener(Consumer<UIEvent> exe, UIEventSander eventSander) {
        super(exe, eventSander);
    }
}
