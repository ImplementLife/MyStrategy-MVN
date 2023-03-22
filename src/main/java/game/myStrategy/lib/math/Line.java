package game.myStrategy.lib.math;

public class Line {
    private Vec2D p1;
    private Vec2D p2;

    public Line(Vec2D pos1, Vec2D pos2) {
        this.p1 = pos1;
        this.p2 = pos2;
    }

    public Vec2D getP1() {
        return p1;
    }
    public void setP1(Vec2D p1) {
        this.p1 = p1;
    }

    public Vec2D getP2() {
        return p2;
    }
    public void setP2(Vec2D p2) {
        this.p2 = p2;
    }

    public double getLength() {
        return Vec2D.getLength(p1, p2);
    }

    double[] getCoeff() {
        double a = p1.y - p2.y;
        double b = p2.x - p1.x;
        double c = p1.x * p2.y - p2.x * p1.y;
        return new double[] {a, b, c};
    }
}
