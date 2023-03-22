package game.myStrategy.game.update.move.managers;

import game.myStrategy.game.unit.Unit;
import game.myStrategy.game.update.move.Mover;
import game.myStrategy.game.update.move.WayPoint;
import game.myStrategy.lib.draw.drawer.DrawerCamera;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.ui.game.gamePanel.events.UIEventListener;
import game.myStrategy.ui.menu.FrameController;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

@Deprecated
public class MoveManager implements Mover {
    private final Queue<WayPoint> way;
    private final UIEventListener UIEventListener;
    private boolean shift, move;

    private final Unit unit;
    private final Vec2D posNow;
    private final Vec2D posEnd;

    public MoveManager(Unit unit) {
        this.unit = unit;
        way = new LinkedList<>();
        UIEventListener = FrameController.get().registerListener(e -> {
            if (e.isReleased(KeyEvent.VK_SHIFT)) shift = false;
            if (e.isPressed(KeyEvent.VK_SHIFT)) shift = true;
        });
        posNow = unit.getPos();
        posEnd = new Vec2D();
    }

    @Override
    public void moveTo(Vec2D pos) {
//        if (!shift) {
//            way.clear();
//            unit.moveTo(new WayPoint(pos));
//        } else way.offer(new WayPoint(pos));
//        move = true;
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public void update() {
//        if (move) {
//            if (!unit.isMove()) {
//                WayPoint point = way.poll();
//                if (point != null) unit.moveTo(point);
//                else move = unit.isMove();
//            }
//        }
    }

    @Override
    public void draw(DrawerCamera drawer) {

    }
}
