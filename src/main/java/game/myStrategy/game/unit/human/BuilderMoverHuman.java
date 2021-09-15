package game.myStrategy.game.unit.human;

import game.myStrategy.game.update.move.Mover;
import game.myStrategy.game.update.move.build.BuilderMover;
import game.myStrategy.lib.math.Vec2D;

public class BuilderMoverHuman extends BuilderMover {

    protected Vec2D pos;

    public BuilderMoverHuman setPos(final Vec2D pos) {
        this.pos = pos;
        return this;
    }

    /**
     * @throws RuntimeException if (pos == null)
     * @return MoverHuman
     */
    @Override
    public Mover build() {
        if (pos == null) throw new RuntimeException("Pos can not be null");
        return new MoverHuman(pos, maxSpeed, maxSpeedBack, accel, accelToBrake);
    }
}
