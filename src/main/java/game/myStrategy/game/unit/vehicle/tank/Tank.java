package game.myStrategy.game.unit.vehicle.tank;

import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.game.unit.Unit;
import game.myStrategy.game.unit.vehicle.DBT;
import game.myStrategy.game.unit.vehicle.Element;
import game.myStrategy.game.update.move.Mover;
import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.math.Vec2D;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Tank extends Unit {
    //region Static

    private static final GameObjectType TYPE = GameObjectType.TANK;

    private static Map<String, Image> images;
    static {
        images = new HashMap<>();
        putImage("1b" ,"resource/entity/equipment/Tank/default/1/body.png");
        putImage("1t" ,"resource/entity/equipment/Tank/default/1/turret.png");
        putImage("2b" ,"resource/entity/equipment/Tank/default/2/body.png");
        putImage("2t" ,"resource/entity/equipment/Tank/default/2/turret.png");
    }
    private static void putImage(String name, String path) {
        images.put(name, new ImageIcon(path).getImage());
    }

    //endregion

    //region Fields

    private final Vec2D posNow;
    private final Vec2D posEnd;

    private final Vec2D posTurretNow;
    private double lTurret = 10;

    private final Element body;
    private final Element turret;
    private final DBT dbt;
    private final Mover mover;

    //endregion

    public Tank(Vec2D pos, float angle) {
        super(TYPE);
        this.posNow = new Vec2D(pos);
        this.posEnd = new Vec2D(pos);
        this.posTurretNow = new Vec2D(posNow).addAngVec(lTurret, angle);

        this.dbt = new DBT(angle, 1.2f, 1.5f);
        BuilderMoverTank builder = new BuilderMoverTank();
        builder
                .setDbt(dbt)
                .setPos(posNow)
                .setMaxSpeed(300)
                .setMaxSpeedBack(120)
                .setAccel(200)
                .setAccelToBrake(600);
        this.mover = builder.build();

        this.body = new Element(posNow, images.get("2b"), angle);
        this.turret = new Element(posTurretNow, images.get("2t"), angle);
    }

    public void rotateBody(Vec2D pos) {
        dbt.rotateBody((float) Vec2D.getAngle(pos, posNow));
    }
    public void rotateTurret(Vec2D pos) {
        dbt.rotateTurret((float) Vec2D.getAngle(pos, posTurretNow));
    }

    //region Unit Overrides

    @Override
    public void attack(Unit unit) {

    }

    @Override
    public boolean kill() {
        return false;
    }

    @Override
    public Vec2D getPos() {
        return posNow;
    }

    @Override
    public boolean isMove() {
        return mover.isMove();
    }

    @Override
    public void moveTo(Vec2D pos) {
        mover.moveTo(pos);
    }

    @Override
    public float getSize() {
        return 0;
    }

    //endregion

    //region Obj Overrides

    @Override
    public void update() {
        super.update();
        mover.update();
        posTurretNow.setAngVec(posNow, lTurret, dbt.getAngleBody());
        body.setAngle(dbt.getAngleBody());
        turret.setAngle(dbt.getAngleTurret());
    }

    @Override
    public void draw(Drawer drawer) {
        body.draw(drawer);
        turret.draw(drawer);
//        mover.games.myStrategy.lib.draw();
    }

    //endregion

}
