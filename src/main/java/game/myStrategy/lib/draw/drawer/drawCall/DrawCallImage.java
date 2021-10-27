package game.myStrategy.lib.draw.drawer.drawCall;

import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.math.Vec2D;

import java.awt.*;

public class DrawCallImage extends DrawCallBase {

    private Image image;
    private Vec2D offset;

    public void setOffset(Vec2D offset) {
        this.offset = offset;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void draw(Drawer drawer) {
        if (offset != null) {
            drawer.drawImage(super.pos, image, super.angle, offset);
        } else if (super.angle != null) {
            drawer.drawImage(super.pos, image, super.angle);
        } else {
            drawer.drawImage(super.pos, image);
        }
    }
}
