package game.myStrategy.game.draw.drawers;

import game.myStrategy.lib.draw.drawer.DrawerAWT;

import javax.swing.*;
import java.awt.*;

public class DrawerJPanel {
    private final JPanel panel;
    private final DrawerAWT drawerImpl;

    public DrawerJPanel(JPanel panel, DrawerAWT drawerImpl) {
        this.panel = panel;
        this.drawerImpl = drawerImpl;
    }

    public void draw() {
        Graphics g2 = panel.getGraphics();
        g2.drawImage(drawerImpl.getImage(),0,0,null);
        g2.dispose();
    }
}
