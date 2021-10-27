package game.myStrategy.game.unit.human;

import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.game.unit.Unit;
import game.myStrategy.game.unit.fire.FireManagerImpl;
import game.myStrategy.game.update.move.Mover;
import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.math.Angle;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.ui.game.gamePanel.Control;

import java.awt.*;

public class Human extends Unit {
    private static final GameObjectType TYPE = GameObjectType.HUMAN;

    private final Vec2D posNow;
    private final Angle angle;
    private final Mover mover;
    private final float size = 8;
    private Type type;

    //=======================================//
    public Human(Vec2D pos) {
        this(pos, Type.SMG);
    }
    public Human(Vec2D pos, Type type) {
        super(TYPE);
        this.setFireManager(new FireManagerImpl(this,80, 2000, 30, 7));
        this.posNow = new Vec2D(pos);
        this.angle = new Angle(0);
        this.mover = new MoverHuman(posNow, 300, 16, 1200, 1200);

        double random = Math.random();
        if (random < 0.33) this.type = Type.MUNG;
        else if (random > 0.66) this.type = Type.RIFE;
        else this.type = type;
    }

    //=======================================//
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    //   Unit Overrides
    private float health = 100;
    @Override
    public boolean kill() {
        health--;
        if (health <=0 ) {
            delete();
            return true;
        }
        return false;
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
    public Vec2D getPos() {
        return posNow;
    }

    @Override
    public float getSize() {
        return size;
    }

    //=======================================//
    //   Obj Overrides

    @Override
    public void update() {
        super.update();
        mover.update();
    }

    @Override
    public void draw(Drawer drawer) {
        angle.setValue(angle.getValue() + 0.05f);
        Color color = Color.GREEN;
        if (getPlayer().isEnemy()) color = Color.RED;
        switch (type) {
            case SMG:  drawer.fillShape(posNow, angle, color, Color.BLACK, size, 3, 0.3f); break;
            case RIFE: drawer.fillShape(posNow, angle, color, Color.BLACK, size, 4, 0.3f); break;
            case MUNG: drawer.fillShape(posNow, angle, color, Color.BLACK, size, 5, 0.3f); break;
        }
//        drawer.drawCircle(posNow, 10, Color.blue, 0.5f);
        if (Control.get().alt()) {
            drawAmmoCount(drawer);
            drawHealth(drawer);
        }
        //mover.games.myStrategy.lib.draw();
    }

    private void drawAmmoCount(Drawer drawer) {
        int[] i = fireManager.getAmmoCount();
        drawer.drawString(posNow.clone().add(6, 6), i[0] + "/" + i[1], 16, Color.YELLOW);
    }

    private void drawHealth(Drawer drawer) {
        Vec2D vecHealth = posNow.clone().sub(25, 20);
        drawer.drawLine(vecHealth, vecHealth.clone().addX(50), Color.GRAY, 2);
        drawer.drawLine(vecHealth, vecHealth.clone().addX(health/2), Color.YELLOW, 2);
        drawer.drawRect(vecHealth.sub(2,2), new Vec2D(54, 4), Color.BLACK, null, 2);
    }

}
