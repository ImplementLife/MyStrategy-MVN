package game.myStrategy.lib.draw.drawer.drawCall;

import game.myStrategy.lib.math.Angle;
import game.myStrategy.lib.math.Vec2D;

public abstract class DrawCallBase implements DrawCall {
    private int layer = 0;
    protected Angle angle;
    protected Vec2D pos;

    public void setPos(Vec2D pos) {
        this.pos = pos;
    }

    public Vec2D getPos() {
        return pos;
    }

    public void setAngle(Angle angle) {
        this.angle = angle;
    }

    public Angle getAngle() {
        return angle;
    }

    @Override
    public void setLayer(int layer) {
        this.layer = layer;
    }

    @Override
    public int getLayer() {
        return layer;
    }
}
