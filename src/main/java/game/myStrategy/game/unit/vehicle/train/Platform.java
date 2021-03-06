package game.myStrategy.game.unit.vehicle.train;

import game.myStrategy.game.objects.GameObject;
import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.lib.draw.drawer.Drawer;

public class Platform extends GameObject {
    //==========     Static     =============//
    private static final GameObjectType TYPE = GameObjectType.PLATFORM;

    //=======================================//

    private WayPoint firstWayPoint;
    private WayPoint lastWayPoint;

    public Platform(WayPoint firstWayPoint, WayPoint lastWayPoint) {
        super(TYPE);

        this.firstWayPoint = firstWayPoint;
        this.lastWayPoint = lastWayPoint;

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Drawer drawer) {

    }
}
