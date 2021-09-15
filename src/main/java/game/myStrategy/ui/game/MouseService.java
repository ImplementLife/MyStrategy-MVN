package game.myStrategy.ui.game;

import game.myStrategy.game.context.InContext;
import game.myStrategy.game.draw.GameDrawService;
import game.myStrategy.game.draw.camera.Camera;
import game.myStrategy.lib.math.Vec2D;

public class MouseService extends InContext {

    private Vec2D mousePos;
    private Vec2D globalMousePos;

    public final void setMousePos(Vec2D pos) {
        getMousePos().setXY(pos);
        Camera camera = GameDrawService.getCamera();
        getGlobalMousePos().setXY(Vec2D.add(camera.firstPos, Vec2D.scalar(mousePos, camera.currentScale)));
    }

    public Vec2D getMousePos() {
        if (mousePos == null) mousePos = new Vec2D(100, 100);
        return mousePos;
    }

    public Vec2D getGlobalMousePos() {
        if (globalMousePos == null) globalMousePos = new Vec2D(100, 100);
        return globalMousePos;
    }

    public Vec2D posToMouse(Vec2D pos) {
        return Vec2D.sub(getGlobalMousePos(), pos);
    }


}
