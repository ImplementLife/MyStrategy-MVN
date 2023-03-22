package game.myStrategy.game;

import game.myStrategy.game.context.InContext;
import game.myStrategy.game.map.MapGenerator;
import game.myStrategy.game.unit.vehicle.train.RailWayBezier;
import game.myStrategy.lib.math.bezier.BezierCurveSingle;
import game.myStrategy.lib.math.Vec2D;

public final class GameService extends InContext {
    //region Singleton
    private static GameService instance;
    public static GameService get() {
        if (instance == null) instance = new GameService();
        return instance;
    }
    private GameService() {}
    //endregion

    public void createMap() {
        MapGenerator.newMap(new Vec2D(100,100), 1);
    }

    public void addUnit() {
//        SquadFabric.createTankSquad(new Vec2D(100, 100), 1, 1, 0);
//        Vec2D pos = new Vec2D(0, 200);
//        for (int i = 0; i < 3; i++) {
//            SquadFabric.createHumanSquad(pos.addX(200), 10, 200, 0);
//        }
//        SquadFabric.createHumanSquad(pos.addX(1000), 2, 200, 1);
//        SquadFabric.createHumanSquad(pos.addY(300), 2, 200, 1);
//        SquadFabric.createHumanSquad(pos.addY(300), 2, 200, 1);
//        SquadFabric.createHumanSquad(pos.addY(300), 2, 200, 1);
//        SquadFabric.createHumanSquad(pos.addY(300), 2, 200, 1);

//        for (int i = 0; i < 1; i++) {
//            new Wagon(RailWay.allRailWay.get(0), 200 + i*600);
//        }

//        Vec2D offset = new Vec2D().x(2000);
        for (int i = 0; i < 15; i++) {
            new BezierCurveSingle(
                    Vec2D.newRandomVec(new Vec2D(), 200).addX(i*300),
                    Vec2D.newRandomVec(new Vec2D(), 200).addX(i*300),
                    Vec2D.newRandomVec(new Vec2D(), 200).addX(i*300)
            );
        }
//        new RailWayBezier(
//            new BezierCurveSingle(
//                new Vec2D(-100, 300),
//                new Vec2D(300, -100),
//                new Vec2D(-500, -500)
//            )
//        );
//        offset.add(offset);
//        new RailWayBezier(
//            new BezierCurveSingle(
//                new Vec2D(100, 300).add(offset),
//                new Vec2D(300, 100).add(offset),
//                new Vec2D(500, 300).add(offset)
//            )
//        );
//        offset.add(offset);
//        new RailWayBezier(
//            new BezierCurveSingle(
//                new Vec2D(100, 600).add(offset),
//                new Vec2D(300, 100).add(offset),
//                new Vec2D(500, 300).add(offset)
//            )
//        );
    }

}
