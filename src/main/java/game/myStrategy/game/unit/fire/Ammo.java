package game.myStrategy.game.unit.fire;

import game.myStrategy.game.unit.fire.bullet.Bullet;
import game.myStrategy.lib.math.Vec2D;

public class Ammo {
    public void shot(Vec2D p1, Vec2D p2) {
        new Bullet(p1, p2);
    }
    public void shot(Vec2D p1, Vec2D p2, Runnable exe) {
        new Bullet(p1, p2).setRemoveExe(exe);
    }
}
