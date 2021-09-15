package game.myStrategy.game.update.move.build;

import game.myStrategy.game.update.move.Mover;

public abstract class BuilderMover {
    protected float maxSpeed;
    protected float maxSpeedBack;
    protected float accel;
    protected float accelToBrake;

    public BuilderMover setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }
    public BuilderMover setMaxSpeedBack(float maxSpeedBack) {
        this.maxSpeedBack = maxSpeedBack;
        return this;
    }
    public BuilderMover setAccel(float accel) {
        this.accel = accel;
        return this;
    }
    public BuilderMover setAccelToBrake(float accelToBrake) {
        this.accelToBrake = accelToBrake;
        return this;
    }

    public abstract Mover build();
}
