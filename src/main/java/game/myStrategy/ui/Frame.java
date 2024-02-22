package game.myStrategy.ui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private GraphicsDevice device;

    public GraphicsDevice getDevice() {
        if (device == null) device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
        return device;
    }

    public Frame() throws HeadlessException {
        super("IL Strategy");
        setFullScreen();
    }

    public void setPanel(JPanel panel) {
        setContentPane(panel);
        setVisible(true);

    }
    public void setFullScreen() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
    }
}
