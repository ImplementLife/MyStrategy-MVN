package game.myStrategy.ui.menu.settingsMenu;

import game.myStrategy.ui.menu.settingsMenu.tabs.Common;
import game.myStrategy.ui.menu.settingsMenu.tabs.Control;
import game.myStrategy.ui.menu.settingsMenu.tabs.Graphic;
import game.myStrategy.ui.menu.settingsMenu.tabs.Sound;

import javax.swing.*;

public class SettingsMenu {
    private static SettingsMenu instance;
    public static SettingsMenu get() {
        if (instance == null) {
            instance = new SettingsMenu();
            instance.closeAction = () -> {};
            instance.buttonsInit();
            instance.tabsInit();
        }
        return instance;
    }

    private JPanel root;
    private JTabbedPane tabbedPane1;

    private JButton confirm;
    private JButton cancel;
    private JButton close;

    private JPanel graphicTab;
    private JPanel soundTab;
    private JPanel controlTab;
    private JPanel commonTab;

    private Runnable closeAction;

    private Graphic graphic;
    private Sound sound;
    private Control control;
    private Common common;

    private SettingsMenu() {}

    private void tabsInit() {
        if (graphic == null) graphic = new Graphic();
        if (sound == null) sound = new Sound();
        if (control == null) control = new Control();
        if (common == null) common = new Common();

        graphicTab.add(graphic.getRoot());
        soundTab.add(sound.getRoot());
        controlTab.add(control.getRoot());
        commonTab.add(common.getRoot());
    }

    private void buttonsInit() {
        confirm.addActionListener(e -> saveAll());
        cancel.addActionListener(e -> closeAction.run());
        close.addActionListener(e -> closeAction.run());

        confirm.setFocusable(false);
        cancel.setFocusable(false);
        close.setFocusable(false);
    }

    private void saveAll() {
        graphic.saveAll();
        sound.saveAll();
        control.saveAll();
        common.saveAll();
    }

    public void setCloseAction(Runnable closeAction) {
        this.closeAction = closeAction;
    }

    public JPanel getRoot() {
        return root;
    }
}
