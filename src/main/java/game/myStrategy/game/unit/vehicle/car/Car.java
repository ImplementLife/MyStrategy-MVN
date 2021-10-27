package game.myStrategy.game.unit.vehicle.car;

import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.game.unit.Unit;
import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.math.Angle;
import game.myStrategy.lib.math.Vec2D;

import javax.swing.*;
import java.awt.*;

public class Car extends Unit {
    //==========     Static     =============//
    private static final GameObjectType TYPE = GameObjectType.CAR;

    private static Image image = new ImageIcon("resource/equipment/railEquipment/wagon/wagon.png").getImage();
    //=======================================//

    private final Vec2D pos;
    private float angle = 0f;
    private float angleWheel = (float) (Math.PI/2);

    private float acceleration = 0.32f;
    private float speed = 0f;
    private float maxSpeed = 20f;

    private final Vec2D firstPos = new Vec2D(100, 0);
    private final Vec2D lastPos = new Vec2D(100, 500);
    private float length = (float) (lastPos.getY() - firstPos.getY());

    private void help() {
        if (angleWheel != 0) {
            float radius = (float) (length / Math.sin(angleWheel));
            float w = speed/radius;
            angle += w;
        }
    }
    private void help2() {
        if (speed < maxSpeed) {
            speed += acceleration;
        }
    }

    public Car(Vec2D pos) {
        super(TYPE);
        this.pos = new Vec2D(pos);
    }

    @Override
    public void moveTo(Vec2D pos) {

    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public Vec2D getPos() {
        return null;
    }

    @Override
    public float getSize() {
        return 0;
    }

    @Override
    public void update() {
        help();
        help2();
        pos.setXY(Vec2D.newAngVec(pos, speed, angle));
    }

    @Override
    public boolean kill() {
        return false;
    }

    @Override
    public void draw(Drawer drawer) {
        drawer.drawImage(pos, /*lastPos,*/ image, new Angle(angle));
        //Wheels
        Vec2D w11 = Vec2D.newAngVec(pos, length/2, angle);
        Vec2D w22 = Vec2D.newAngVec(pos, length/2, angle - Math.PI);

        Vec2D w1 = Vec2D.newAngVec(w11, 60, angle + Math.PI/2);
        Vec2D w2 = Vec2D.newAngVec(w11, 60, angle - Math.PI/2);

        Vec2D w3 = Vec2D.newAngVec(w22, 60, angle + Math.PI/2);
        Vec2D w4 = Vec2D.newAngVec(w22, 60, angle - Math.PI/2);

//        drawer.fillCircle(pos, 15, 2, Color.RED);
//
//        drawer.fillCircle(w11, 10, 0, Color.YELLOW);
//        drawer.fillCircle(w22, 10, 0, Color.YELLOW);
//
//        drawer.fillCircle(w1, 10, 0, Color.YELLOW);
//        drawer.fillCircle(w2, 10, 0, Color.YELLOW);
//        drawer.fillCircle(w3, 10, 0, Color.YELLOW);
//        drawer.fillCircle(w4, 10, 0, Color.YELLOW);
    }
}
