package game.myStrategy.game.unit.vehicle.tank;

import game.myStrategy.game.unit.vehicle.DBT;
import game.myStrategy.game.update.move.Mover;
import game.myStrategy.game.update.move.build.BuilderMover;
import game.myStrategy.lib.math.Vec2D;

public class BuilderMoverTank extends BuilderMover {
    private DBT dbt;
    private Vec2D pos;

    public BuilderMoverTank setDbt(final DBT dbt) {
        this.dbt = dbt;
        return this;
    }

    public BuilderMoverTank setPos(final Vec2D pos) {
        this.pos = pos;
        return this;
    }

    /**
     * @throws RuntimeException if (pos == null || dbt == null)
     * @return MoverHuman
     */
    @Override
    public Mover build() {
        return new MoverTank(dbt, pos, maxSpeed, maxSpeedBack, accel, accelToBrake);
    }
}
