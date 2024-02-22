package game.myStrategy.ui.game.gamePanel.listener;

import game.myStrategy.ui.game.gamePanel.events.UIEvent;
import game.myStrategy.ui.game.gamePanel.events.UIEventSander;

import java.awt.event.KeyEvent;

import static game.myStrategy.ui.game.gamePanel.events.State.*;

public class KeyListener extends Listener implements java.awt.event.KeyListener {
    public KeyListener(UIEventSander eventSander) {
        super(eventSander);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        addEvent(new UIEvent(PRESSED, e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        addEvent(new UIEvent(RELEASED, e.getKeyCode()));
    }
}
