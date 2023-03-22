package game.myStrategy.game.unit.button;

import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.ui.game.gamePanel.listener.Listener;

import java.awt.*;

import static game.myStrategy.game.unit.button.Button.Positioning.*;

public class RectButton extends Button {
    private final Vec2D pos;
    private final Vec2D size;

    public RectButton(Vec2D pos, Vec2D size) {
        this.pos = new Vec2D(pos);
        this.size = new Vec2D(size);
        this.setPos(pos);
    }
    public RectButton(Vec2D pos, Vec2D size, Runnable exp) {
        this(pos, size);
        super.setExe(exp);
    }
    public RectButton(Vec2D pos, Runnable exp) {
        this(pos, new Vec2D(50, 25));
        super.setExe(exp);
    }

    @Override
    public void setPos(Vec2D pos) {
        this.pos.setXY(pos);

        if      (positioning == TOP_RIGHT)       this.pos.setXY(pos);
        else if (positioning == TOP)             this.pos.subX(Vec2D.scalar(size,0.5).getX());
        else if (positioning == TOP_LEFT)        this.pos.setXY(pos).subX(size.getX());

        else if (positioning == RIGHT)           this.pos.subY(Vec2D.scalar(size,0.5).getY());
        else if (positioning == CENTER)          this.pos.sub(Vec2D.scalar(size,0.5));
        else if (positioning == LEFT)            this.pos.subX(size.getX()).subY(Vec2D.scalar(size,0.5).getY());

        else if (positioning == BOTTOM_RIGHT)    this.pos.subY(size.getY());
        else if (positioning == BOTTOM)          this.pos.subY(size.getY()).subX(Vec2D.scalar(size,0.5).getX());
        else if (positioning == BOTTOM_LEFT)     this.pos.sub(size);
    }

    @Override
    public Vec2D getPos() {
        return pos;
    }

    @Override
    protected void updateState() {
        if (state != ButtonState.PRESSED && Listener.getGlobalMousePos().inRect(pos, pos.clone().add(size))){
            state = ButtonState.FOCUSED;
        } else if (state != ButtonState.PRESSED && !Listener.getGlobalMousePos().inRect(pos, pos.clone().add(size))) {
            state = ButtonState.ACTIVE;
        }
    }

    @Override
    public void draw(Drawer drawer) {
        if (visible) {
            Color color = Color.GRAY;
            switch (state) {
                case ACTIVE:  color = new Color(0xA6FF000F, true); break;
                case FOCUSED: color = new Color(0xB3FF544A, true); break;
                case PRESSED: color = new Color(0xC0B4FF83, true); break;
            }
            drawer.fillRect(pos, size, color, Color.BLACK, null, 2);
        }
    }
}
