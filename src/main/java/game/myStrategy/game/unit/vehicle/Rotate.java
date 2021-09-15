package game.myStrategy.game.unit.vehicle;

import game.myStrategy.lib.math.Angle;

import static game.myStrategy.game.update.Updater.dt;

public class Rotate {
    //==========     Static     =============//
    private float angleNow;
    private float angleEnd;
    private float speed;

    public Rotate(float angleNow, float speed) {
        this.angleNow = angleNow;
        this.angleEnd = angleNow;
        this.speed = speed;
    }

    public float getAngleNow() {
        return angleNow;
    }

    public void setAngleNow(float angleNow) {
        this.angleNow = angleNow;
    }
    public void setAngleEnd(float angleEnd) {
        this.angleEnd = angleEnd;
    }

    public boolean rotate(float angleEnd) {
        setAngleEnd(angleEnd);
        return rotate();
    }

    public boolean rotate() {
        if (dt.scalar(speed) > Math.abs(angleNow - angleEnd)) {
            angleNow = angleEnd;
            return true;
        } else {
            if (angleNow < angleEnd) {
                if ((angleNow + Math.PI) < angleEnd) angleNow -= dt.scalar(speed);
                else angleNow += dt.scalar(speed);
            }
            if (angleNow > angleEnd) {
                if ((angleNow - Math.PI) > angleEnd) angleNow += dt.scalar(speed);
                else angleNow -= dt.scalar(speed);
            }
        }
        angleNow = Angle.valid(angleNow);
        return false;
    }
}
