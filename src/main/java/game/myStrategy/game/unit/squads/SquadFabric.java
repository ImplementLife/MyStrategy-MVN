package game.myStrategy.game.unit.squads;

import game.myStrategy.game.unit.human.Human;
import game.myStrategy.game.unit.vehicle.tank.Tank;
import game.myStrategy.lib.math.Vec2D;

public final class SquadFabric {
    private SquadFabric(){}

    public static Squad createTankSquad(Vec2D pos, int count, float radius, int player) {
        Squad squad = new Squad();
        for (int i = 0; i < count; i++) {
            Tank tank = new Tank(Vec2D.newRandomVec(pos, radius), 0);
            tank.setPlayer(player);
            squad.putMembers(tank);

        }
        squad.setPlayer(player);

        return squad;
    }
    public static Squad createHumanSquad(Vec2D pos, int count, float radius, int player) {
        Squad squad = new Squad();
        for (int i = 0; i < count; i++) {
            Human human = new Human(Vec2D.newRandomVec(pos, radius));
            human.setPlayer(player);
            squad.putMembers(human);
        }
        squad.setPlayer(player);

        return squad;
    }
}
