package game.myStrategy.game.draw.drawers;

import game.myStrategy.lib.draw.drawer.Drawer;

import javax.swing.*;
import java.awt.*;

public class DrawerJPanel {
    private final JPanel panel;
    private final Drawer drawer;

    public DrawerJPanel(JPanel panel, Drawer drawer) {
        this.panel = panel;
        this.drawer = drawer;
    }

    public void draw() {
        Graphics g2 = panel.getGraphics();
        g2.drawImage(drawer.getImage(),0,0,null);
        g2.dispose();
    }
}
