package game.myStrategy.game.unit.squads;

import game.myStrategy.game.unit.Unit;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.ui.game.CursorService;
import game.myStrategy.ui.game.gamePanel.events.MouseKeyCode;
import game.myStrategy.ui.game.gamePanel.events.UIEventListener;
import game.myStrategy.ui.game.gamePanel.listener.Listener;
import game.myStrategy.ui.menu.FrameController;

import static game.myStrategy.game.unit.Unit.units;

public class SquadAttackManager {
    private CursorService cursorService = CursorService.get();

    private final Squad squad;
    private boolean canUpdate;

    public SquadAttackManager(Squad squad) {
        this.squad = squad;
    }

    void attack(Unit unit) {
        if (unit instanceof Squad) {
            for (Unit u : squad.members) u.attack(((Squad) unit).members.getRandom());
        } else {
            for (Unit u : squad.members) u.attack(unit);
        }
    }

    void setCanUpdate(boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    void update() {
        if (squad.controlPanel.inFound()) {
            boolean b = false;
            squad.controlPanel.foundCanUpdate(false);
            for (Unit u : units) {
                if (Vec2D.getLength(u.getPos(), Listener.getGlobalMousePos()) < u.getSize()) {
                    if (u.isEnemy()) {
                        b = true;
                        attackedUnit = u;
                        break;
                    }
                }
            }
            if (b && Squad.squadsInFocus().size() > 0) {
                cursorService.setCursor("resource/images/cursors/attack.png");
            } else {
                squad.controlPanel.foundCanUpdate(true);
                attackedUnit = null;
                cursorService.resetCursor();
            }
        }
    }

    private Unit attackedUnit;
    private final UIEventListener listener = FrameController.get().registerListener(e -> {
        if (e.isReleased(MouseKeyCode.LEFT_MOUSE_BUTTON)) {
            if (attackedUnit != null)attack(attackedUnit);
        }
    });
}
