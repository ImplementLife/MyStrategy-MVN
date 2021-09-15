package game.myStrategy.ui.game;

import game.myStrategy.game.context.InContext;
import game.myStrategy.ui.game.gamePanel.GamePanel;

@Deprecated(since = "bad implement")
public class CursorService extends InContext {
    //region Singleton block
    private static CursorService instance;
    public static CursorService get() {
        if (instance == null) instance = new CursorService();
        return instance;
    }
    private CursorService() {}
    //endregion

    private GamePanel gamePanel;

    public void setCursor(String name) {
        gamePanel.setCursor(name);
    }
    public void resetCursor() {
        gamePanel.resetCursor();
    }

    public void setPanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
