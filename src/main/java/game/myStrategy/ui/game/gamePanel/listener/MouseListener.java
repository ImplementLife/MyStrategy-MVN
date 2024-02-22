package game.myStrategy.ui.game.gamePanel.listener;

import game.myStrategy.ui.game.gamePanel.events.UIEvent;
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
        if (isLeftMouseButton(e)) addEvent(new UIEvent(CLICKED, LEFT_MOUSE_BUTTON));
        if (isRightMouseButton(e)) addEvent(new UIEvent(CLICKED, RIGHT_MOUSE_BUTTON));
        if (isMiddleMouseButton(e)) addEvent(new UIEvent(CLICKED, WHEEL_MOUSE_BUTTON));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isLeftMouseButton(e)) addEvent(new UIEvent(PRESSED, LEFT_MOUSE_BUTTON));
        if (isRightMouseButton(e)) addEvent(new UIEvent(PRESSED, RIGHT_MOUSE_BUTTON));
        if (isMiddleMouseButton(e)) addEvent(new UIEvent(PRESSED, WHEEL_MOUSE_BUTTON));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isLeftMouseButton(e)) addEvent(new UIEvent(RELEASED, LEFT_MOUSE_BUTTON));
        if (isRightMouseButton(e)) addEvent(new UIEvent(RELEASED, RIGHT_MOUSE_BUTTON));
        if (isMiddleMouseButton(e)) addEvent(new UIEvent(RELEASED, WHEEL_MOUSE_BUTTON));
    }

    /*============================================================*/

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
