package game.myStrategy.game.draw.camera;

import game.myStrategy.Boot;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.ui.game.gamePanel.events.MouseKeyCode;
import game.myStrategy.ui.game.gamePanel.events.UIEventListener;
import game.myStrategy.ui.menu.FrameController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Camera {
    //region Static

    private static final Vec2D mousePos = game.myStrategy.ui.game.gamePanel.listener.MouseMotionListener.getMousePos();
    private static final Vec2D globalMousePos = game.myStrategy.ui.game.gamePanel.listener.MouseMotionListener.getGlobalMousePos();
    private static Vec2D sizeFrame;

    public static Vec2D getSizeFrame() {
        if (sizeFrame == null) sizeFrame = new Vec2D(Boot.getBean(FrameController.class).getFrameSize());
        return sizeFrame;
    }

    //endregion

    //region Fields
    public final Vec2D firstPos;
    public final Vec2D endPos;
    public final Vec2D size;

    private final Graphics2D[] g;
    private final AffineTransform[] transforms;

    //endregion

    //region Constructors

    public Camera(Vec2D pos, Vec2D size, Graphics2D[] g) {
        this.firstPos = new Vec2D(pos);
        this.endPos = new Vec2D(pos).add(size);
        this.size = new Vec2D(size);
        this.g = g;
        this.transforms = new AffineTransform[g.length];
        for (int i = 0; i < g.length; i++) transforms[i] = (AffineTransform) g[i].getTransform().clone();
    }

    //endregion

    //region Public methods

    public double getCurrentScale() {
        return currentScale;
    }

    public Vec2D getFirstPos() {
        return firstPos;
    }

    public Vec2D getEndPos() {
        return endPos;
    }

    public Vec2D getSize() {
        return size;
    }

    public void upScale() {
        resizeUp = true;
    }

    public void downScale() {
        resizeDown = true;
    }

    public void update() {
        resize();

        mover.update();

        if (moveLeft) moveLeft();
        if (moveRight) moveRight();

        if (moveUp) moveUp();
        if (moveDown) moveDown();

        if (mousePos.getX() < sizeZoneMouseMove) moveLeft();
        if (mousePos.getX() > (getSizeFrame().getX() - sizeZoneMouseMove)) moveRight();

        if (mousePos.getY() < sizeZoneMouseMove) moveUp();
        if (mousePos.getY() > (getSizeFrame().getY() - sizeZoneMouseMove)) moveDown();
    }

    public void tracking(Vec2D vec) {
        firstPos.add(vec);
        endPos.add(vec);
        globalMousePos.add(vec);
    }

    //endregion

    /**
     * Метод служит для изменения границ отрисовки объектов
     */
    private void resize() {
        if (resizeUp && resizeDown) return;
        if (resizeUp) {
            resizeUp = false;
            for (Graphics2D G : g) G.scale(SCALE_UP, SCALE_UP); //Масштаб увеличили
            newDrawZone(SCALE_UP); //Зону отрисовки уменьшаем
        }
        if (resizeDown) {
            resizeDown = false;
            for (Graphics2D G : g) G.scale(SCALE_DOWN, SCALE_DOWN); //Масштаб уменьшили
            newDrawZone(SCALE_DOWN); //Зону отрисовки увеличиваем
        }

//        altResize();
    }

    private void newDrawZone(double scalar) {
        currentScale /= scalar;
        size.setXY(getSizeFrame()).scalar(currentScale).ceil();
        firstPos.setXY(Vec2D.sub(globalMousePos, Vec2D.sub(globalMousePos, firstPos).antScalar(scalar)));
        endPos.setXY(Vec2D.add(firstPos, size));
        speed /= scalar;
    }

    private void altNewDrawZone(double scalar) {
        currentScale += scalar;
        currentScale = game.myStrategy.lib.math.Util.ceil(currentScale);
        size.setXY(getSizeFrame()).antScalar(currentScale).ceil();
//        firstPos.setXY(Vec2D.sub(globalMousePos, Vec2D.sub(globalMousePos, firstPos).antScalar(scalar)));
        firstPos.setXY(new Vec2D());
        endPos.setXY(Vec2D.add(firstPos, size));
        speed = speedDef / currentScale;

        for (int i = 0; i < g.length; i++) {
            AffineTransform t = (AffineTransform) transforms[i].clone();
            t.setToScale(currentScale, currentScale);
            g[i].setTransform(t);
        }

//        for (Graphics2D G : g) {
//            AffineTransform t = G.getTransform();
//            AffineTransform t2 = (AffineTransform) t.clone();
//            t2.setToScale(currentScale, currentScale);
//            G.setTransform(t2);
//        }
    }

    private void altResize() {
        double scalar = 0.1;
        if (resizeUp) altNewDrawZone(scalar);
        if (resizeDown) altNewDrawZone(-scalar);
        resizeUp = false;
        resizeDown = false;
    }

    //region Inner states fields

    private final int sizeZoneMouseMove = 20;
    private final double speedDef = 25;
    private double speed = 25;
    private boolean moveUp, moveDown, moveLeft, moveRight;

    private final MoverCamera mover = new MoverCamera();
    private final double SCALE_UP = 1.1f, SCALE_DOWN = 0.90909090909090909091f;
    public double currentScale = 1.0f;
    private boolean resizeUp, resizeDown;

    //endregion

    //region Private methods

    private void moveUp() {
        tracking(new Vec2D(0, -speed));
    }

    private void moveDown() {
        tracking(new Vec2D(0, speed));
    }

    private void moveLeft() {
        tracking(new Vec2D(-speed,0));
    }

    private void moveRight() {
        tracking(new Vec2D(speed,0));
    }

    //endregion

    public void moveTo(Vec2D pos) {
        mover.moveTo(pos);
    }

    public void moveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }
    public void moveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }
    public void moveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }
    public void moveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public Vec2D posOnCamera(Vec2D pos) {
        return Vec2D.sub(pos, firstPos);
    }

    //==================================================//
    class MoverCamera {
        private final Vec2D endPos;
        private final Vec2D centerPos;
        private final Vec2D course;
        private final float maxSpeed = 50f;
        private final float acceleration = 2f; //Ускорение

        private float speed = 0f;
        private float fullWayDist, dist;
        private boolean move;

        //==================================================//
        private MoverCamera() {
            this.centerPos = new Vec2D();
            this.endPos = new Vec2D();
            this.course = new Vec2D();

            int countTicksToMaxSpeed = (int) (maxSpeed / acceleration);
            float speed = 0;
            for (int i = 0; i < countTicksToMaxSpeed; i++) {
                speed += acceleration;
                fullWayDist += speed;
            }
        }

        //==================================================//
        public void moveTo(Vec2D pos) {
            endPos.setXY(pos);
            centerPos.setXY(setCenterPos());
            dist = (float) getCourse().getLength();
            if (dist < fullWayDist*2) dist /= 2;
            else dist = fullWayDist;
            move = true;
        }

        private Vec2D getCourse() {
            centerPos.setXY(setCenterPos());
            return Vec2D.sub(endPos, centerPos);
        }
        private Vec2D setCenterPos() {
//            centerPos.setXY(firstPos).sub(Vec2D.sub(firstPos, endPos).scalar(0.5));
            return Vec2D.sub(firstPos, Vec2D.sub(firstPos, endPos).scalar(0.5));
        }

        private void update() {
            centerPos.setXY(setCenterPos());
            if (move) {
                course.setXY(getCourse());
                if (course.getLength() > dist) {
                    if (speed < maxSpeed) speed += acceleration;
                } else {
                    if (speed > 0) speed -= acceleration;
                    if (speed <= 0) move = false;
                }

                tracking(new Vec2D().addAngVec(speed, course.getAngle()));
            }
        }
    }
}
