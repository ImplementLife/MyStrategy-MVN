package game.myStrategy.ui.menu.mainMenu;

import game.myStrategy.ui.menu.FrameController;
import game.myStrategy.ui.menu.settingsMenu.SettingsMenu;

import javax.swing.*;

public class MainMenu {
    private JPanel root;

    private JButton start;
    private JButton settings;
    private JButton exit;
    private JButton workshop;
    private JButton gallery;

    public MainMenu() {
        start.addActionListener(e -> FrameController.get().startNewGame());
        SettingsMenu settingsMenu = SettingsMenu.get();
        settingsMenu.setCloseAction(() -> FrameController.get().setMainMenu());
        settings.addActionListener(e -> FrameController.get().setPanel(settingsMenu.getRoot()));
        exit.addActionListener(e -> System.exit(0));

        //region Disable tab focusable

        start.setFocusable(false);
        settings.setFocusable(false);
        exit.setFocusable(false);
        workshop.setFocusable(false);
        gallery.setFocusable(false);

        //endregion

    }

    public JPanel getRoot() {
        return root;
    }
}
