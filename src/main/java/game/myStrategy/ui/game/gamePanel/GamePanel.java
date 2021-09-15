package game.myStrategy.ui.game.gamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.TreeMap;

public class GamePanel extends JPanel {
    private final Container container;
    public GamePanel() {
        super();
        container = new Container(this);
        this.setBackground(new Color(139, 136, 136));
    }

    public Container getContainer() {
        return container;
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
