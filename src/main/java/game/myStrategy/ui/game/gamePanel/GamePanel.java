package game.myStrategy.ui.game.gamePanel;

import game.myStrategy.ui.game.gamePanel.events.UIEventSander;
import game.myStrategy.ui.game.gamePanel.listener.KeyListener;
import game.myStrategy.ui.game.gamePanel.listener.MouseListener;
import game.myStrategy.ui.game.gamePanel.listener.MouseMotionListener;
import game.myStrategy.ui.game.gamePanel.listener.MouseWheelListener;

import javax.swing.*;
import java.awt.*;
import java.util.TreeMap;

public class GamePanel extends JPanel {
    private final UIEventSander eventSander;

    private KeyListener keyListener;
    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;
    private MouseWheelListener mouseWheelListener;

    public GamePanel() {
        super();

        this.eventSander = new UIEventSander();
        addKeyListener(new KeyListener(eventSander));
        addMouseListener(new MouseListener(eventSander));
        addMouseMotionListener(new MouseMotionListener(eventSander));
        addMouseWheelListener(new MouseWheelListener(eventSander));

        setBackground(new Color(139, 136, 136));
    }
    public UIEventSander getEventSander() {
        return eventSander;
    }

    public void setFocus() {
        this.setFocusable(true);
        this.requestFocus();
    }

    private static TreeMap<String, Cursor> cursors;
    public void setCursor(String name) {
        if (cursors == null) cursors = new TreeMap<>();
        if (cursors.containsKey(name)) {
            setCursor(cursors.get(name));
        } else {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image = toolkit.getImage(name);
            Cursor cursor = toolkit.createCustomCursor(image, new Point(16, 16), "img");
            cursors.put(name, cursor);
            setCursor(cursor);
        }
    }
    public void resetCursor() {
        setCursor(Cursor.getDefaultCursor());
    }

}
