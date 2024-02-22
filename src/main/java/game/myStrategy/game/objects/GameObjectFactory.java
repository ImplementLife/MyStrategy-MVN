package game.myStrategy.game.objects;

import game.myStrategy.game.GameService;
import game.myStrategy.game.unit.human.Human;
import game.myStrategy.game.unit.squads.Squad;
import game.myStrategy.game.unit.vehicle.tank.Tank;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.lib.math.bezier.BezierCurveSingle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class GameObjectFactory {
    @Autowired
    private GameService gameService;

    public void createRandBezierCurves() {
        for (int i = 0; i < 15; i++) {
            gameService.regGameObject(new BezierCurveSingle(
                Vec2D.newRandomVec(new Vec2D(), 200).addX(i*300),
                Vec2D.newRandomVec(new Vec2D(), 200).addX(i*300),
                Vec2D.newRandomVec(new Vec2D(), 200).addX(i*300)
            ));
        }
    }

    public void createTankSquad(Vec2D pos, int count, float radius, int player) {
        Squad squad = new Squad();
        for (int i = 0; i < count; i++) {
            Tank tank = new Tank(Vec2D.newRandomVec(pos, radius), 0);
            tank.setPlayer(player);
            tank.enableUpdateDraw();
            gameService.regGameObject(tank);

            squad.putMembers(tank);
        }
        squad.setPlayer(player);
        squad.enableUpdate();

        gameService.regGameObject(squad);
    }

    public void createHumanSquad(Vec2D pos, int count, float radius, int player) {
        Squad squad = new Squad();
        for (int i = 0; i < count; i++) {
            Human human = new Human(Vec2D.newRandomVec(pos, radius));
            human.setPlayer(player);
            human.enableUpdateDraw();
            gameService.regGameObject(human);

            squad.putMembers(human);
        }
        squad.setPlayer(player);
        squad.enableUpdate();

        gameService.regGameObject(squad);
    }

}
