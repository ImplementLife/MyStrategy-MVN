package game.myStrategy.game.unit.vehicle.train;

import game.myStrategy.game.objects.GameObject;
import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.lib.math.bezier.BezierCurve;

import java.awt.*;

public class RailWayBezier extends GameObject {
    private BezierCurve curve;

    private Vec2D a;
    private Vec2D b;
    private Vec2D proj;
    private Vec2D rej;
    private String s[];

    public RailWayBezier(BezierCurve curve) {
        super(GameObjectType.RAIL_WAY);

        this.curve = curve;

        a = new Vec2D(100, -100);
        b = new Vec2D(-150, 30);
        proj = b.project(a);
        rej = b.reject(a).add(proj);
        s = new String[] {
                "ang a-b " + Math.toDegrees(a.angBtw(b)),
                "ang b-proj " + Math.toDegrees(b.angBtw(proj)),
        };
        //super.enableDraw();
    }

    @Override
    public void draw(Drawer drawer) {
        drawer.drawBezierCurve(curve, Color.DARK_GRAY, 6);
        drawer.drawBezierCurve(curve, Color.GRAY, 3);

        for (Vec2D buildPoint : curve.getBuildPoints()) {
            drawer.drawCircle(buildPoint, 6, Color.YELLOW, 2);
        }
        drawer.drawString(
                curve.getBuildPoints().getFirst().clone().subY(-50),
                new String[]{
                        "length = " + curve.getLength(),
                        "radius = " + curve.getRadius(),
                }, 26, Color.black
        );
        drawer.drawCircle(curve.getCenter(), 6, Color.YELLOW, 2);
        drawer.drawCircle(curve.getCenter(), (float) curve.getRadius(), Color.YELLOW, 2);


        drawer.drawLine(new Vec2D(), a, Color.red, 4);
        drawer.drawLine(new Vec2D(), b, Color.yellow, 4);

        drawer.drawLine(new Vec2D(), proj, Color.ORANGE, 2);
        drawer.drawLine(proj, rej, Color.darkGray, 2);

        drawer.drawString(new Vec2D(0,50), s, 16, Color.BLACK);
    }
}
