package game.myStrategy.ui.game.gamePanel.listener;

import game.myStrategy.Boot;
import game.myStrategy.game.draw.GameDrawService;
import game.myStrategy.game.draw.camera.Camera;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.ui.game.gamePanel.events.UIEventSander;

import java.awt.event.MouseEvent;

public class MouseMotionListener extends Listener implements java.awt.event.MouseMotionListener {
    public MouseMotionListener(UIEventSander eventSander) {
        super(eventSander);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setPos(new Vec2D(e.getX(), e.getY()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        setPos(new Vec2D(e.getX(), e.getY()));
    }

    private static Vec2D mousePos;
    private static Vec2D globalMousePos;
    public static Vec2D getMousePos() {
        if (mousePos == null) mousePos = new Vec2D(100, 100);
        return mousePos;
    }
    public static Vec2D getGlobalMousePos() {
        if (globalMousePos == null) globalMousePos = new Vec2D(100, 100);
        return globalMousePos;
    }
    public static Vec2D posToMouse(Vec2D pos) {
        return Vec2D.sub(getGlobalMousePos(), pos);
    }
    protected final void setPos(Vec2D pos) {
        getMousePos().setXY(pos);
        Camera camera = Boot.getBean(GameDrawService.class).getCamera();
        getGlobalMousePos().setXY(Vec2D.add(camera.firstPos, Vec2D.scalar(mousePos, camera.currentScale)));
    }
}
