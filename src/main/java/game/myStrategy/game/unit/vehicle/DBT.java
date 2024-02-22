package game.myStrategy.game.unit.vehicle;

import game.myStrategy.lib.threads.bt.DT;

/**
 * Dependence Body-Turret
 */
public class DBT {

    private final Rotate rBody;
    private final Rotate rTurret;

    private boolean bodyRotate;
    private boolean turretRotate;

    public DBT(float angleNow, float speedBody, float speedTurret) {
        rBody = new Rotate(angleNow, speedBody);
        rTurret = new Rotate(angleNow, speedTurret);
    }

    public void rotateBody(float angle) {
        rBody.setAngleEnd(angle);
        bodyRotate = true;
    }
    public void rotateTurret(float angle) {
        rTurret.setAngleEnd(angle);
        turretRotate = true;
    }

    public float getAngleBody() {
        return rBody.getAngleNow();
    }
    public float getAngleTurret() {
        return rTurret.getAngleNow();
    }

    public void update(DT dt) {
        if (turretRotate) rotateTurret(dt);
        if (bodyRotate) rotateBody(dt);
    }

    private void rotateBody(DT dt) {
        float temp = rBody.getAngleNow();
        if (rBody.rotate(dt)) bodyRotate = false;
        rTurret.setAngleNow(rTurret.getAngleNow() + rBody.getAngleNow() - temp);
    }
    private void rotateTurret(DT dt) {
        if (rTurret.rotate(dt) && !bodyRotate) turretRotate = false;
    }

    public boolean isBodyRotate() {
        return bodyRotate;
    }
}
