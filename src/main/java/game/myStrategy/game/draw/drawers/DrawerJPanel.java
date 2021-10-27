package game.myStrategy.game.draw.drawers;

import game.myStrategy.lib.draw.drawer.DrawerImpl;

import javax.swing.*;
import java.awt.*;

public class DrawerJPanel {
    private final JPanel panel;
    private final DrawerImpl drawerImpl;

    public DrawerJPanel(JPanel panel, DrawerImpl drawerImpl) {
        this.panel = panel;
        this.drawerImpl = drawerImpl;
    }

    public void draw() {
        Graphics g2 = panel.getGraphics();
        g2.drawImage(drawerImpl.getImage(),0,0,null);
        g2.dispose();
    }
}
