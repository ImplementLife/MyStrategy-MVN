package game.myStrategy.game.unit.squads;

import game.myStrategy.game.unit.button.Button;
import game.myStrategy.game.unit.button.RectButton;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.ui.game.gamePanel.listener.MouseMotionListener;

public class SquadControlPanel {
    private final Squad squad;

    final Button button;

    public SquadControlPanel(Squad squad) {
        this.squad = squad;
        button = new RectButton(squad.mover.centerPos, () -> squad.moveTo(MouseMotionListener.getGlobalMousePos().clone()));
        //button = new RatioButton(centerPos, 20, () -> moveTo(Listener.getGlobalMousePos()));
        button.enableUpdateDraw();
    }

    void foundCanUpdate(boolean isUpdate) {
        button.setCanUpdate(isUpdate);
    }

    boolean inFound() {
        return button.isPressed();
    }

    public void setPos(Vec2D pos) {
        button.setPos(pos);
    }

    public void setVisible(boolean visible) {
        button.setVisible(visible);
    }
}
