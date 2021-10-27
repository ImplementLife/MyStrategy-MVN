package game.myStrategy.game.unit;

import game.myStrategy.game.context.InContext;
import game.myStrategy.game.unit.human.Human;
import game.myStrategy.lib.math.Vec2D;

public class UnitFactory extends InContext {

    public Human newHuman(Vec2D pos) {
        Human human = new Human(pos);
        human.enableUpdateDraw();
        return human;
    }
}
