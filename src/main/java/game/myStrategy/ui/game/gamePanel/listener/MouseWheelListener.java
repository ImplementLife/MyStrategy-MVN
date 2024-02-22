package game.myStrategy.ui.game.gamePanel.listener;

import game.myStrategy.ui.game.gamePanel.events.UIEvent;
import game.myStrategy.ui.game.gamePanel.events.MouseKeyCode;
import game.myStrategy.ui.game.gamePanel.events.State;
import game.myStrategy.ui.game.gamePanel.events.UIEventSander;

import java.awt.event.MouseWheelEvent;

public class MouseWheelListener extends Listener implements java.awt.event.MouseWheelListener {
    public MouseWheelListener(UIEventSander eventSander) {
        super(eventSander);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) addEvent(new UIEvent(State.CLICKED, MouseKeyCode.WHEEL_MOVE_UP));
        else addEvent(new UIEvent(State.CLICKED, MouseKeyCode.WHEEL_MOVE_DOWN));
    }
}
