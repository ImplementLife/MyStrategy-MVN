package game.myStrategy.game.unit;

import game.myStrategy.game.unit.human.Human;
import game.myStrategy.lib.math.Vec2D;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class UnitFactory {

    public Human newHuman(Vec2D pos) {
        Human human = new Human(pos);
        human.enableUpdateDraw();
        return human;
    }
}
