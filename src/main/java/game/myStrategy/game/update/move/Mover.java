package game.myStrategy.game.update.move;

import game.myStrategy.lib.draw.drawer.GameDrawer;
import game.myStrategy.lib.math.Vec2D;

public interface Mover {
    void moveTo(Vec2D pos);
    void update();
    boolean isMove();

    @Deprecated
    void draw(GameDrawer drawer);
}
