package game.myStrategy.ui.game.gamePanel.listener;

import game.myStrategy.ui.game.gamePanel.events.UIEvent;
import game.myStrategy.ui.game.gamePanel.events.UIEventSander;

public abstract class Listener {
    protected Listener(UIEventSander eventSander) {
        this.eventSander = eventSander;
    }

    private final UIEventSander eventSander;
    protected void addEvent(UIEvent e) {
        eventSander.addEvent(e);
    }
}
