package game.myStrategy.lib.math.bezier;

import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.math.Vec2D;

import java.awt.*;

class Collision {
    private final BezierCurveSingle target;

    public Collision(BezierCurveSingle curve) {
        this.target = curve;
    }

    String s[] = new String[0];
    Vec2D a;
    Vec2D b;
    Vec2D oc;
    double d1 = 0;
    boolean b2;

    private void u() {
        this.d1 = target.d1;
        this.b2 = target.b2;
        this.oc = target.oc;
    }

    void draw(Drawer drawer) {
        u();
        //        s = new String[] {
//                "ang a-b " + Math.toDegrees(a.angBtw(b)),
//                "ang b-proj " + Math.toDegrees(b.angBtw(proj)),
//        };
        drawer.drawBezierCurve(target, Color.DARK_GRAY, 6);
        drawer.drawBezierCurve(target, Color.GRAY, 3);

        for (Vec2D buildPoint : target.getBuildPoints()) {
            drawer.drawLine(target.getCenter(), buildPoint, Color.magenta, 1);
            drawer.drawCircle(buildPoint, 3, Color.YELLOW, 1);
        }


        drawer.drawCircle(target.getCenter(), 5, Color.RED, 1);
        drawer.drawCircle(target.getCenter(), (float) target.getRadius(), Color.YELLOW, 1);

        drawer.drawString(
            target.getBuildPoints().getFirst().clone().subY(-50),
            new String[]{
                String.format("L= %.0f", target.getLength()),
                String.format("R= %.2f", target.getRadius()),
                String.format("d1= %.2f", d1),
                String.format("b2= %b", b2),
                String.format("b2= %s", oc),
            }, 26, Color.black
        );
        drawer.drawString(target.getBuildPoints().get(0), "f", 16, Color.BLACK);
        drawer.drawString(target.getBuildPoints().get(1), "m", 16, Color.BLACK);
        drawer.drawString(target.getBuildPoints().get(2), "e", 16, Color.BLACK);
        if (b2) {
            drawer.drawCircle(oc, 5, Color.RED, 3);
            drawer.drawString(oc, "oc", 16, Color.BLACK);
        }

//        drawer.drawString(target.getCenter(), s, 16, Color.BLACK);
    }
}
