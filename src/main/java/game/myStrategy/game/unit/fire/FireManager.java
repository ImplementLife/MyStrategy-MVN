package game.myStrategy.game.unit.fire;

import game.myStrategy.game.unit.Unit;

public interface FireManager {
    void update();
    void attack(Unit unit);
    boolean isFire();
    boolean isCanFire();
    int[] getAmmoCount();
}
