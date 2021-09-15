package game.myStrategy.lib.math;

public class Collision {

    private double delta(double[] d) throws Exception {
        if (d.length < 4) throw new Exception("length array < 4");
        return (d[0] * d[3]) - (d[1] * d[2]);
    }

    public boolean areCrossing(Line line1, Line line2) {
        Vec2D p1 = line1.getP1();
        Vec2D p2 = line1.getP2();
        Vec2D p3 = line2.getP1();
        Vec2D p4 = line2.getP2();

        double v1 = 0, v2 = 0, v3 = 0, v4 = 0;

        try {
            v1 = delta(new double[] {p4.x - p3.x, p4.y - p3.y, p1.x - p3.x, p1.y - p3.y});
            v2 = delta(new double[] {p4.x - p3.x, p4.y - p3.y, p2.x - p3.x, p2.y - p3.y});

            v3 = delta(new double[] {p2.x - p1.x, p2.y - p1.y, p3.x - p1.x, p3.y - p1.y});
            v4 = delta(new double[] {p2.x - p1.x, p2.y - p1.y, p4.x - p1.x, p4.y - p1.y});
        } catch(Exception e) {
            e.printStackTrace();
        }

        return (v1 * v2) < 0 && (v3 * v4) < 0;
    }

    public Vec2D getPoint(Line line1, Line line2) throws Exception {
        double[] l1 = line1.getCoeff();
        double[] l2 = line2.getCoeff();

        double D =  delta(new double[] {l1[0], l1[1], l2[0], l2[1]});
        double Dx = delta(new double[] {-l1[2], l1[1], -l2[2], l2[1]});
        double Dy = delta(new double[] {l1[0], -l1[2], l2[0], -l2[2]});
        if (D == 0.0) {
            if (Dx == 0 || Dy == 0) throw new Exception("совпадают");
            throw new Exception("паралельны");
        }
        return new Vec2D(Dx/D, Dy/D);
    }

    public Vec2D getCrossingPoint(Line line1, Line line2) throws Exception {
        if (areCrossing(line1, line2)) {
            return getPoint(line1, line2);
        } else {
            throw new Exception("не пересекаются");
        }
    }


    public boolean tempName(Line l, Vec2D v) {
        Vec2D tl = Vec2D.sub(l.getP1(), l.getP2());
        Vec2D t = Vec2D.sub(l.getP1(), v);
        return tl.getAngle() == t.getAngle() && t.getLength() < tl.getLength();
    }
}