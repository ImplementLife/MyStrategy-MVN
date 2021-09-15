package game.myStrategy.ui.game.gamePanel;

import game.myStrategy.ui.game.gamePanel.events.UIEventSander;
import game.myStrategy.ui.game.gamePanel.listener.KeyListener;
import game.myStrategy.ui.game.gamePanel.listener.MouseListener;
import game.myStrategy.ui.game.gamePanel.listener.MouseMotionListener;
import game.myStrategy.ui.game.gamePanel.listener.MouseWheelListener;

import javax.swing.*;

public class Container {
    private final UIEventSander eventSander;
    private final JComponent component;

    private KeyListener keyListener;
    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;
    private MouseWheelListener mouseWheelListener;

    public Container(JComponent component) {
        this.component = component;
        this.eventSander = new UIEventSander();

        this.component.addKeyListener(new KeyListener(eventSander));
        this.component.addMouseListener(new MouseListener(eventSander));
        this.component.addMouseMotionListener(new MouseMotionListener(eventSander));
        this.component.addMouseWheelListener(new MouseWheelListener(eventSander));

    }

    public UIEventSander getEventSander() {
        return eventSander;
    }
}
