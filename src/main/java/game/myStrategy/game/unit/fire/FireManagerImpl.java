package game.myStrategy.game.unit.fire;

import game.myStrategy.game.unit.Unit;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.lib.timer.Timer;

import java.util.LinkedList;
import java.util.Queue;

public class FireManagerImpl implements FireManager {
    private final Unit unit;
    private Unit attackedUnit;

    private final Timer reloadAmmo;
    private final Timer reloadClip;
    private final Timer queue;

    private final Queue<Clip> clips;
    private Clip currentClip;

    private boolean fire, pause = true;

    /**
     * @param unit Юнит к которому это относится
     * @param reloadAmmo скорость перезарядки одного патрона
     * @param reloadClip скорость перезарядки рожка
     * @param countAmmoInClip количество патронов в рожке
     * @param countClip количество рожков
     */
    public FireManagerImpl(final Unit unit, int reloadAmmo, int reloadClip, int countAmmoInClip, int countClip) {
        this.unit = unit;
        this.reloadAmmo = new Timer(reloadAmmo);
        this.reloadClip = new Timer(reloadClip);
        this.queue = new Timer((long) (200 + 800 * Math.random()));
        this.clips = new LinkedList<>();
        for (int i = 1; i < countClip; i++) clips.offer(new Clip(countAmmoInClip));
        this.currentClip = new Clip(countAmmoInClip);
    }

    @Override
    public void update() {
        if (fire) {
            if (!attackedUnit.isDeleted()) {
                if (currentClip != null) {
                    if (currentClip.getAmmoCount() > 0) {
                        if (queue.startF()) {
                            queue.setTimeDelay((long) (500 + 1000 * Math.random()));
                            pause = !pause;
                        }
                        if (pause && reloadAmmo.startF()) {
                            double rad = Vec2D.getLength(unit.getPos(), attackedUnit.getPos()) * 0.01;
                            currentClip.getAmmo().shot(unit.getPos(), Vec2D.newRandomVec(attackedUnit.getPos(), rad), () -> {
                                if (attackedUnit != null) {
                                    if (attackedUnit.isDeleted()) fire = false;
                                    else if (Math.random() > 0) attackedUnit.kill();
                                }
                            });
                            queue.setTimeDelay((long) (1000 + 1000 * Math.random()));
                        }
                    } else {
                        if (reloadClip.startF()) currentClip = clips.poll();
                    }
                } else {
                    fire = false;
                }
            } else {
                attackedUnit = null;
                fire = false;
            }
        }
    }

    @Override
    public void attack(Unit unit) {
        if (unit != null && !unit.isDeleted()) {
            if (this.unit.isEnemy(unit)) {
                this.attackedUnit = unit;
                fire = true;
            }
        }
    }

    @Override
    public boolean isFire() {
        return fire;
    }

    @Override
    public boolean isCanFire() {
        return currentClip != null && currentClip.getAmmoCount() > 0;
    }

    @Override
    public int[] getAmmoCount() {
        int i = currentClip != null ? currentClip.getAmmoCount() : 0;
        return new int[] {i, clips.size()};
    }
}
