package game.myStrategy.game;

import game.myStrategy.game.audio.AudioService;
import game.myStrategy.game.diplomacy.DiplomacyService;
import game.myStrategy.game.draw.DrawService;
import game.myStrategy.game.map.MapGenerator;
import game.myStrategy.game.map.MapService;
import game.myStrategy.game.net.NetService;
import game.myStrategy.game.objects.GameObject;
import game.myStrategy.game.objects.GameObjectFactory;
import game.myStrategy.game.objects.IdService;
import game.myStrategy.game.resource.ResourceService;
import game.myStrategy.game.unit.UnitFactory;
import game.myStrategy.game.unit.vehicle.train.RailWay;
import game.myStrategy.game.unit.vehicle.train.RailWayBezier;
import game.myStrategy.game.unit.vehicle.train.Wagon;
import game.myStrategy.game.update.UpdateService;
import game.myStrategy.lib.events.EventBus;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.lib.math.bezier.BezierCurveSingle;
import game.myStrategy.ui.UIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public final class GameService {
    @Autowired private AudioService      audioService;
    @Autowired private UpdateService     updateService;
    @Autowired private DrawService       drawService;
    @Autowired private NetService        netService;

    @Autowired private UIService         uiService;
    @Autowired private ResourceService   resourceService;
    @Autowired private GameService       gameService;

    @Autowired private IdService         idService;
    @Autowired private MapService        mapService;
    @Autowired private DiplomacyService  diplomacyService;
    @Autowired private UnitFactory       unitFactory;
    @Autowired private EventBus          eventBus;
    @Autowired private GameObjectFactory gameObjectFactory;

    public void regGameObject(GameObject gameObject) {
        gameObject.processEventBus(eventBus);
    }

    public void createMap() {
        MapGenerator.newMap(new Vec2D(100,100), 1);
    }

    public void addSomeUnits() {
//        gameObjectFactory.createRandBezierCurves();
//
//        for (int i = 0; i < 1; i++) {
//            new Wagon(RailWay.allRailWay.get(0), 200 + i*600);
//        }
//        Vec2D offset = new Vec2D().x(2000);
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
