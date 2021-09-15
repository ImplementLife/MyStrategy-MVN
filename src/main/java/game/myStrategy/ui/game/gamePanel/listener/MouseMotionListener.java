package game.myStrategy.ui.game.gamePanel.listener;

import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.ui.game.gamePanel.events.UIEventSander;

import java.awt.event.MouseEvent;

public class MouseMotionListener extends Listener implements java.awt.event.MouseMotionListener {
    public MouseMotionListener(UIEventSander eventSander) {
        super(eventSander);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setPos(new Vec2D(e.getX(), e.getY()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        setPos(new Vec2D(e.getX(), e.getY()));
    }
}
