package game.myStrategy.game.update.move;

import game.myStrategy.lib.draw.drawer.DrawerCamera;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.lib.threads.bt.DT;

public interface Mover {
    void moveTo(Vec2D pos);
    void update(DT dt);
    boolean isMove();

    @Deprecated
    void draw(DrawerCamera drawer);
}
