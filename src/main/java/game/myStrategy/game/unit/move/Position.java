package game.myStrategy.game.unit.move;

import game.myStrategy.lib.math.Angle;
import game.myStrategy.lib.math.Vec2D;

public class Position {
    private final Vec2D pos;
    private final Angle angle;

    public Position(Vec2D pos, Angle angle) {
        this.pos = pos;
        this.angle = angle;
    }

    public Vec2D getPos() {
        return pos;
    }
    public void setPos(Vec2D pos) {
        this.pos.setXY(pos);
    }

    public Angle getAngle() {
        return angle;
    }
    public void setAngle(Angle angle) {
        this.angle.setValue(angle);
    }
    public void setAngle(float angle) {
        this.angle.setValue(angle);
    }
}
