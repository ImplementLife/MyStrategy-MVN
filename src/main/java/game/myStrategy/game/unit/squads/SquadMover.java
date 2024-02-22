package game.myStrategy.game.unit.squads;

import game.myStrategy.Boot;
import game.myStrategy.game.unit.Unit;
import game.myStrategy.lib.draw.FX.Arrow;
import game.myStrategy.lib.math.Angle;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.ui.game.gamePanel.events.MouseKeyCode;
import game.myStrategy.ui.game.gamePanel.events.UIEventListener;
import game.myStrategy.ui.menu.FrameController;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

public class SquadMover {
    private static boolean shift, rightB;
    private static final UIEventListener EVENT_LISTENER = Boot.getBean(FrameController.class).registerListener(e -> {
        if (e.isClicked(MouseKeyCode.LEFT_MOUSE_BUTTON)) {
            if (Squad.squadsInFocus().size() > 0) rightB = true;
        }
        if (e.isReleased(KeyEvent.VK_SHIFT)) shift = false;
        if (e.isPressed(KeyEvent.VK_SHIFT)) shift = true;
    });


    private final Squad squad;

    final Vec2D centerPos = new Vec2D();
    final Vec2D endPos = new Vec2D();
    final Queue<Vec2D> way = new LinkedList<>();
    final Vec2D wayPoint = new Vec2D();
    final Arrow arrow = new Arrow();

    boolean move;
    float minSize = 50;
    float maxSize = 120;

    SquadMover(Squad squad) {
        this.squad = squad;
    }

    void move() {
        if (move) {
            squad.controlPanel.setPos(centerPos.setXY(squad.getPos()));
            if (squad.isMove()) {
                Vec2D pos = way.poll();
                if (pos != null) {
                    wayPoint.setXY(pos);
                    moveUnitsTo(wayPoint);
                } else if (centerPos.sub(endPos).getLength() < 50) {
                    arrow.setDraw(false);
                    move = false;
                }
            }

            arrow.setFirst(squad.getPos());
            arrow.setEnd(endPos);
        }
    }

    boolean isMove() {
        int help = 0;
        for (Unit u : squad.members) if (!u.isMove()) help++;
        return help == squad.members.size();
    }

    void moveTo(Vec2D pos) {
        if (!shift) {
            way.clear();
            moveUnitsTo(pos);
        } else way.offer(pos);
        move = true;
        arrow.setDraw(true);
    }

    void moveUnitsTo(Vec2D pos) {
        endPos.setXY(pos);
        for (Unit u : squad.members) {
            u.moveTo(Vec2D.newAngVec(pos, minSize + (maxSize - minSize) * Math.random(), Angle.E * Math.random()));
        }
    }
}
