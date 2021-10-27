package game.myStrategy.lib.draw.FX;

import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.math.Vec2D;

import java.awt.*;

public class Arrow {
    private final Vec2D first, end, right, left;
    private float angleBetweenArr = 2.8f;
    private float lengthArr = 25f;
    private boolean draw;

    public Arrow() {
        this.first = new Vec2D();
        this.end = new Vec2D();
        this.right = new Vec2D();
        this.left = new Vec2D();
    }
    public Arrow(Vec2D first, Vec2D end) {
        this();
        this.first.setXY(first);
        this.end.setXY(end);
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }
    public void setFirst(Vec2D first) {
        this.first.setXY(first);
    }
    public void setEnd(Vec2D end) {
        this.end.setXY(end);
    }

    public void draw(Drawer drawer) {
        if (draw) {
            float angle = (float) Vec2D.getAngle(first, end);
            right.setAngVec(end, lengthArr, angle - angleBetweenArr);
            left.setAngVec( end, lengthArr, angle + angleBetweenArr);

            drawer.drawLine(first, end, Color.BLACK, 2);
            drawer.drawLine(end, right, Color.BLACK, 2);
            drawer.drawLine(end, left, Color.BLACK, 2);
        }
    }

}
