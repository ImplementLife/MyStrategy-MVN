package game.myStrategy.lib.math;

import java.util.ArrayList;

/**
 * Кривая Безье
 */
public class BezierCurve {
    private ArrayList<String> p;

    private ArrayList<Line> create(Vec2D ... r) {
        ArrayList<Line> result = new ArrayList<>();
        Line l1 = new Line(r[0], r[1]);
        Line l2 = new Line(r[1], r[2]);
        Vec2D temp = get(l1, 0);
        for (int i = 1; i <= 100; i+=3) {
            Line tempL = new Line(get(l1, i), get(l2, i));
            Vec2D p = get(tempL, i);
            result.add(new Line(temp, p));
            temp = p;
        }
        return result;
    }

    private Vec2D get(Line l, int c) {
        Vec2D temp = Vec2D.sub(l.getP2(), l.getP1());
        return Vec2D.newAngVec(l.getP1(), temp.getLength() * (double)c/100, temp.getAngle());
    }
}
