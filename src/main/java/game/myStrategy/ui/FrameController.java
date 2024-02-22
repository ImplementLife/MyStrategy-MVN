package game.myStrategy.ui;

import game.myStrategy.game.GameService;
import game.myStrategy.game.draw.GameDrawService;
import game.myStrategy.game.resource.ResourceService;
import game.myStrategy.game.update.UpdateService;
import game.myStrategy.lib.events.EventBus;
import game.myStrategy.ui.game.cursor.CursorService;
import game.myStrategy.ui.game.gamePanel.GamePanel;
import game.myStrategy.ui.game.gamePanel.control.Control;
import game.myStrategy.ui.game.gamePanel.events.UIEvent;
import game.myStrategy.ui.game.gamePanel.events.UIEventListener;
import game.myStrategy.ui.menu.GameMenu;
import game.myStrategy.ui.menu.mainMenu.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

@Component
@Scope("singleton")
public class FrameController {
    @Autowired
    private UpdateService updateService;
    @Autowired
    private EventBus eventBus;
    @Autowired
    private Control control;
    @Autowired
    private GameService gameService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private GameDrawService gameDrawService;

    private Frame frame;
    private GamePanel gamePanel;

    @PostConstruct
    private void postConstruct() {
        frame = new Frame();
        frame.setIconImage(resourceService.getImage("resource/images/com/IL.png"));
    }

    public UIEventListener registerListener(Consumer<UIEvent> execute) {
        return gamePanel.getEventSander().registerListener(execute);
    }

    public void setPanel(JPanel panel) {
        frame.setPanel(panel);
    }

    public void setMainMenu() {
        MainMenu mainMenu = new MainMenu(this);
        frame.setPanel(mainMenu.getRoot());
    }

    public void startNewGame() {
        gamePanel = new GamePanel();
        frame.setPanel(gamePanel);
        CursorService.get().setPanel(gamePanel);
        //==========================================//

        gameService.createMap();
        gameDrawService.start(gamePanel);
        gamePanel.setFocus();
        gameService.addUnit();
        updateService.start();
        control.enabled();
    }

    public Dimension getFrameSize() {
        return frame.getSize();
    }

    public void showGameMenu() {
        GameMenu gameMenu = new GameMenu(this);
        JPanel root = gameMenu.getRoot();

        updateService.pause();
        gameDrawService.setPause(true);

        frame.setPanel(root);

        control.disabled();
    }
    public void unshodGameMenu() {
        frame.setPanel(gamePanel);
        updateService.pause();
        gameDrawService.setPause(false);
        control.enabled();
        gamePanel.setFocus();
    }
}
