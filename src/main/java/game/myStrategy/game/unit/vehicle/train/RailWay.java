package game.myStrategy.game.unit.vehicle.train;

import game.myStrategy.game.objects.GameObject;
import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.math.Vec2D;

import java.awt.*;
import java.util.ArrayList;

public class RailWay extends GameObject {
    //==========     Static     =============//
    private static final GameObjectType TYPE = GameObjectType.RAIL_WAY;


    //=======================================//

    public static ArrayList<WayPoint> allWayPoint = new ArrayList<>();
    public static ArrayList<Intersection> allIntersection = new ArrayList<>();
    public static ArrayList<RailWay> allRailWay = new ArrayList<>();
    static {
        RailWay firstRailWay = new RailWay(new Vec2D(0, 200), new Vec2D(1000, 200));
        allRailWay.get(0).addNew(new Vec2D(10800, 300));
        allRailWay.get(1).addNew(new Vec2D(11800, 1700));
        allRailWay.get(2).addNew(new Vec2D(10000, 2800));
        allRailWay.get(3).addNew(new Vec2D(0,2800));
        allRailWay.get(4).addNew(new Vec2D(-800, 2000));
        allRailWay.get(5).addNew(new Vec2D(-800, 1000));
        allRailWay.get(6).link(firstRailWay);

    }

    public static void createNewIntersection(RailWay mainWayPoint, RailWay leftWayPoint, RailWay rightWayPoint) {
        new Intersection(mainWayPoint, leftWayPoint, rightWayPoint);
    }
    public static class Intersection {
        private RailWay mainWayPoint;
        private RailWay leftWayPoint;
        private RailWay rightWayPoint;

        public Intersection(RailWay mainWayPoint, RailWay leftWayPoint, RailWay rightWayPoint) {
            this.mainWayPoint = mainWayPoint;
            this.leftWayPoint = leftWayPoint;
            this.rightWayPoint = rightWayPoint;
            allIntersection.add(this);
        }
    }

    //=======================================//

    private WayPoint left;
    private WayPoint right;

    public RailWay(WayPoint left, WayPoint right) {
        super(TYPE);
        enableDraw();
        allRailWay.add(this);
        this.left = left;
        this.right = right;
    }
    private RailWay(Vec2D start, Vec2D end) {
        this(null, null);
        this.left = new WayPoint(start);
        this.right = this.left.createNext(end);
    }

    public WayPoint getLeft() {
        return left;
    }
    public WayPoint getRight() {
        return right;
    }

    public RailWay addNew(Vec2D pos) {
        return new RailWay(right, this.right.createNext(pos));
    }
    public RailWay link(RailWay railWay) {
        this.right.nextWayPoint = railWay.left;
        railWay.left.previousWayPoint = this.right;
        return new RailWay(this.right, railWay.left);
    }

    @Override
    public void update() {

    }
    @Override
    public void draw(Drawer drawer) {
        drawer.drawLine(left, right, Color.BLACK, 20);
        drawer.drawLine(left, right, Color.GRAY, 15);
    }
}
