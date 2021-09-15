package game.myStrategy.game.unit.human;

import game.myStrategy.game.update.move.SpeedController;
import game.myStrategy.lib.draw.drawer.GameDrawer;
import game.myStrategy.lib.math.Vec2D;

import java.awt.*;

import static game.myStrategy.game.update.Updater.dt;

public class MoverHuman extends SpeedController {
    private final Vec2D posNow;
    private final Vec2D posEnd;
    private final Vec2D course;
    private boolean move;
    private float distToBrake;

    public MoverHuman(final Vec2D pos, float maxSpeed, float maxSpeedBack, float accel, float accelToBrake) {
        super(maxSpeed, maxSpeedBack, accel, accelToBrake);
        this.posNow = pos;
        this.posEnd = new Vec2D(pos);
        this.course = new Vec2D();
    }

    public void moveTo(Vec2D pos) {
        posEnd.setXY(pos);
        course.setXY(posEnd).sub(posNow);
        if (course.getLength() > 5) {
            move = true;
            if (course.getLength() > speeds.distAccelForward + speeds.distToBrakeForward) {
                distToBrake = speeds.distToBrakeForward;
            } else {
                distToBrake = notFullWayIntegral((float) course.getLength());
            }
        }
    }

    public void update() {
        if (move) {
            course.setXY(posEnd).sub(posNow);
            if (course.getLength() > distToBrake) {
                moveForward();
            } else if (stop()) {
                move = false;
                posNow.setXY(posEnd);
            }
            super.updateSpeed();
            posNow.addAngVec(dt.scalar(getSpeed()), course.getAngle());
        }
    }

    public boolean isMove() {
        return move;
    }

    //////////////===========

    public void draw(GameDrawer drawer) {
        String[] strings = {
                "speed = " + getSpeed(),
                "Length Way = " + course.getLength(),
        };
        drawer.drawString(posNow, strings, 16, Color.YELLOW);
    }

}
