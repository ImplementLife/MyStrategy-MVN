package game.myStrategy.lib.draw.FX.Animation;

import game.myStrategy.game.objects.GameObject;
import game.myStrategy.lib.draw.drawer.Draw;
import game.myStrategy.game.resource.Resource;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.lib.timer.Timer;

import java.awt.*;

public class Animation extends GameObject {
    private static final String PATH = "resource/images/animations/";
    private static final String SUFFIX = ".gif";

    private final Vec2D pos;
    private final Timer delay;
    private final Image[] images;
    private int currentImage;
    private final boolean loop;

    public Animation(final Vec2D pos, String name, int delay, boolean loop) {
        this.pos = pos;
        this.delay = new Timer(delay);
        this.images = Resource.getResImageArray().get(PATH + name + SUFFIX);
        this.loop = loop;
    }
    public Animation(final Vec2D pos, String name, boolean loop) {
        this(pos, name, 50, loop);
    }

    public void update() {
        if (images != null) {
            if (currentImage + 1 >= images.length) {
                if (loop) currentImage = 0;
                else delete();
            } else if (delay.startF()) {
                currentImage++;
            }
        }
    }

    public void draw(Draw drawer) {
        if (images != null) {
            Vec2D offset = new Vec2D();
            offset.setX(images[currentImage].getWidth(null));
            offset.setY(images[currentImage].getHeight(null));
            offset.scalar(0.5);
//            drawer.drawCall(0, () -> drawer.drawImage(Vec2D.sub(pos, offset), images[currentImage]));
            drawer.drawImage(Vec2D.sub(pos, offset), images[currentImage]);

        }
        drawer.drawString(pos, getId().toString(), 16, Color.BLUE);
    }
}
