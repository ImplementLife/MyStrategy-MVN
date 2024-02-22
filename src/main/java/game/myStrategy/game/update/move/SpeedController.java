package game.myStrategy.game.update.move;

import game.myStrategy.lib.threads.bt.DT;

public abstract class SpeedController implements Mover {
    protected final Speeds speeds;
    private float speed = 0f;
    private boolean moveBack, moveForward, toBrake;

    protected SpeedController(float maxSpeed, float maxSpeedBack, float accel, float accelToBrake) {
        speeds = Speeds.getSpeeds(maxSpeed, maxSpeedBack, accel, accelToBrake);
    }

    public void moveForward() {
        if (speed != 0 && !moveForward) stop();
        moveForward = true;
        moveBack = false;
    }
    public void moveBack() {
        if (speed != 0 && !moveBack) stop();
        moveBack = true;
        moveForward = false;
    }
    public boolean stop() {
        toBrake = true;
        moveBack = false;
        moveForward = false;
        return speed == 0;
    }

    private void accelerateForward(DT dt) {
        if (speed < speeds.maxSpeedForward) speed += dt.scalar(speeds.acceleration);
    }
    private void accelerateBack(DT dt) {
        if (Math.abs(speed) < speeds.maxSpeedBack) {
            speed -= dt.scalar(speeds.acceleration);
        }
    }
    private void toBrake(DT dt) {
        if (speed - dt.scalar(speeds.accelToBrake) > 0) {
            speed -= dt.scalar(speeds.accelToBrake);
        } else if (speed + dt.scalar(speeds.accelToBrake) < 0) {
            speed += dt.scalar(speeds.accelToBrake);
        } else {
            speed = 0;
            toBrake = false;
            moveForward = false;
            moveBack = false;
        }
    }

    protected void updateSpeed(DT dt) {
        if (toBrake) toBrake(dt);
        else if (moveForward) accelerateForward(dt);
        else if (moveBack) accelerateBack(dt);
    }

    public float notFullWayIntegral(double length) {
        return speeds.notFullWayIntegral(length);
    }

    //==========     Getters

    public float getSpeed() {
        return speed;
    }
}