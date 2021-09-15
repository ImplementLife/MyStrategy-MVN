package game.myStrategy.ui.game.gamePanel.listener;

import game.myStrategy.ui.game.gamePanel.events.Event;
import game.myStrategy.ui.game.gamePanel.events.UIEventSander;

import java.awt.event.MouseEvent;

import static javax.swing.SwingUtilities.*;
import static game.myStrategy.ui.game.gamePanel.events.MouseKeyCode.*;
import static game.myStrategy.ui.game.gamePanel.events.State.*;

public class MouseListener extends Listener implements java.awt.event.MouseListener {
    public MouseListener(UIEventSander eventSander) {
        super(eventSander);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isLeftMouseButton(e)) addEvent(new Event(CLICKED, LEFT_MOUSE_BUTTON));
        if (isRightMouseButton(e)) addEvent(new Event(CLICKED, RIGHT_MOUSE_BUTTON));
        if (isMiddleMouseButton(e)) addEvent(new Event(CLICKED, WHEEL_MOUSE_BUTTON));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isLeftMouseButton(e)) addEvent(new Event(PRESSED, LEFT_MOUSE_BUTTON));
        if (isRightMouseButton(e)) addEvent(new Event(PRESSED, RIGHT_MOUSE_BUTTON));
        if (isMiddleMouseButton(e)) addEvent(new Event(PRESSED, WHEEL_MOUSE_BUTTON));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isLeftMouseButton(e)) addEvent(new Event(RELEASED, LEFT_MOUSE_BUTTON));
        if (isRightMouseButton(e)) addEvent(new Event(RELEASED, RIGHT_MOUSE_BUTTON));
        if (isMiddleMouseButton(e)) addEvent(new Event(RELEASED, WHEEL_MOUSE_BUTTON));
    }

    /*============================================================*/

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
