package game.myStrategy.ui.game.gamePanel.listener;

import game.myStrategy.game.draw.GameDrawService;
import game.myStrategy.game.draw.camera.Camera;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.ui.game.MouseService;
import game.myStrategy.ui.game.gamePanel.events.Event;
import game.myStrategy.ui.game.gamePanel.events.UIEventSander;

public abstract class Listener {
    protected Listener(UIEventSander eventSander) {
        this.eventSander = eventSander;
    }

    protected void addEvent(Event e) {
        eventSander.addEvent(e);
    }

    protected MouseService mouseService;

    private final UIEventSander eventSander;

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
        Camera camera = GameDrawService.getCamera();
        getGlobalMousePos().setXY(Vec2D.add(camera.firstPos, Vec2D.scalar(mousePos, camera.currentScale)));
    }
}
