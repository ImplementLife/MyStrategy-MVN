package game.myStrategy.game.unit.fire.bullet;

import game.myStrategy.game.objects.GameObject;
import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.lib.draw.FX.Animation.Animation;
import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.math.Angle;
import game.myStrategy.lib.math.Vec2D;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static game.myStrategy.game.update.UpdateService.dt;

public class Bullet extends GameObject {
    //==========     Static     =============//
    private static final GameObjectType TYPE = GameObjectType.BULLET;
    private static HashMap<String, Image> images;
    static {
        images = new HashMap<>();
        putImage("resource/bullet/trace_10.png");
    }

    private static void putImage(String path) {
        images.put(path, new ImageIcon(path).getImage());
    }

    //=======================================//
    private final Vec2D pos;
    private final Vec2D posNow;
    private final Vec2D posEnd;

    private final Angle angle;
    private Image image;
    private Runnable removeExe;

    private float speed;

    //=======================================//
    public Bullet(Vec2D pos, Vec2D posEnd) {
        super(TYPE);
        this.pos = new Vec2D(pos);
        this.posNow = new Vec2D(pos);
        this.posEnd = new Vec2D(posEnd);
        this.angle = new Angle(Vec2D.getAngle(pos, posEnd));
        this.speed = 1500;

        super.enableUpdateDraw();
//        this.image = images.get("resource/bullet/trace_10.png");
    }
    public Bullet(Vec2D pos, Vec2D posEnd, String name) {
        this(pos, posEnd);
        if (name != null) this.image = images.get(name);
    }
    public Bullet(Vec2D pos, Vec2D posEnd, String name, float speed) {
        this(pos, posEnd, name);
        this.speed = speed;
    }

    //=======================================//
    public Vec2D getFirstPos() {
        return pos;
    }

    public void setRemoveExe(Runnable removeExe) {
        this.removeExe = removeExe;
    }

    //=======================================//
    @Override
    public void update() {
        float dist = (float) dt.scalar(speed);
        posNow.addAngVec(dist, angle.getValue());
        if (Vec2D.sub(posNow, posEnd).getLength() <= dist) {
            if (removeExe != null) removeExe.run();
            new Animation(posNow, "exp_01", false);
            delete();
        }
    }

    @Override
    public void draw(Drawer drawer) {
        if (image == null) {
            Vec2D temp = Vec2D.newAngVec(posNow, 10, angle.getValue() - Math.PI);
            drawer.drawLine(posNow, temp, Color.YELLOW, 0.8f);
        } else {
            drawer.drawImage(posNow, image, angle);
        }
    }
}
