package game.myStrategy.ui.menu;

import game.myStrategy.ui.game.gamePanel.control.Control;
import game.myStrategy.game.draw.GameDrawService;
import game.myStrategy.game.update.UpdateService;
import game.myStrategy.ui.Frame;
import game.myStrategy.ui.game.cursor.CursorService;
import game.myStrategy.game.GameService;
import game.myStrategy.ui.game.gamePanel.GamePanel;
import game.myStrategy.ui.game.gamePanel.events.Event;
import game.myStrategy.ui.game.gamePanel.events.UIEventListener;
import game.myStrategy.ui.menu.mainMenu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

import static game.myStrategy.game.context.Context.context;

public class FrameController {
    //region Singleton

    private static FrameController instance;
    public static FrameController get() {
        if (instance == null) instance = new FrameController();
        return instance;
    }
    private FrameController() {
        frame = new Frame();
        ImageIcon icon = new ImageIcon("resource/images/com/IL.png");
        if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            frame.setIconImage(icon.getImage());
        }
        /*JFrame.setDefaultLookAndFeelDecorated(false);
        frame.addFocusListener(new FocusListener() {
            private final KeyEventDispatcher altDisabler = new KeyEventDispatcher() {
                @Override
                public boolean dispatchKeyEvent(KeyEvent e) {
                    return e.getKeyCode() == 18;
                }
            };

            @Override
            public void focusGained(FocusEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(altDisabler);
            }

            @Override
            public void focusLost(FocusEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(altDisabler);
            }
        });*/
    }

    //endregion

    private final Frame frame;
    private GamePanel gamePanel;

    public UIEventListener registerListener(Consumer<Event> execute) {
        return gamePanel.getContainer().getEventSander().registerListener(execute);
    }

    public void setPanel(JPanel panel) {
        frame.setPanel(panel);
    }

    public void setMainMenu() {
        MainMenu mainMenu = new MainMenu();
        frame.setPanel(mainMenu.getRoot());
    }

    public void startNewGame() {
        gamePanel = new GamePanel();
        frame.setPanel(gamePanel);
        CursorService.get().setPanel(gamePanel);
        //==========================================//

        GameService gameService = GameService.get();
        gameService.createMap();
        context().setUpdateService(UpdateService.get());
        GameDrawService.start(gamePanel);
        gamePanel.setFocus();
        gameService.addUnit();
        context().getUpdateService().start();
        Control.get().enabled();
    }

    public Dimension getFrameSize() {
        return frame.getSize();
    }
}
