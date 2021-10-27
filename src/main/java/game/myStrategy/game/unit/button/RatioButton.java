package game.myStrategy.game.unit.button;

import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.ui.game.gamePanel.listener.Listener;

import java.awt.*;

import static game.myStrategy.game.unit.button.Button.Positioning.*;

public class RatioButton extends Button {
    private final Vec2D pos = new Vec2D();
    private final float radius;

    public RatioButton(Vec2D pos, float radius, Runnable exe) {
        this.pos.setXY(pos);
        this.radius = radius;
        setExe(exe);
    }

    @Override
    public void setPos(Vec2D pos) {
        if      (positioning == TOP_RIGHT)       this.pos.setXY(pos);
        else if (positioning == TOP)             this.pos.setXY(pos).subX(radius);
        else if (positioning == TOP_LEFT)        this.pos.setXY(pos).subX(radius*2);

        else if (positioning == RIGHT)           this.pos.setXY(pos).subY(radius);
        else if (positioning == CENTER)          this.pos.setXY(pos).subY(radius).subX(radius);
        else if (positioning == LEFT)            this.pos.setXY(pos).subY(radius).subX(radius*2);

        else if (positioning == BOTTOM_RIGHT)    this.pos.setXY(pos).subY(radius*2);
        else if (positioning == BOTTOM)          this.pos.setXY(pos).subY(radius*2).subX(radius);
        else if (positioning == BOTTOM_LEFT)     this.pos.setXY(pos).subY(radius*2).subX(radius*2);
    }

    @Override
    protected void updateState() {
        if (state != ButtonState.PRESSED && Listener.posToMouse(pos).getLength() < radius){
            state = ButtonState.FOCUSED;
        } else if (state != ButtonState.PRESSED && Listener.posToMouse(pos).getLength() > radius) {
            state = ButtonState.ACTIVE;
        }
    }

    @Override
    public void draw(Drawer drawer) {
        if (visible) {
            Color color = Color.GRAY;
            switch (state) {
                case ACTIVE:  color = new Color(0xFF000F); break;
                case FOCUSED: color = new Color(0xFF544A); break;
                case PRESSED: color = new Color(0xB4FF83); break;
            }
            drawer.fillCircle(pos, radius, color, Color.BLACK, 2);
        }
    }
}