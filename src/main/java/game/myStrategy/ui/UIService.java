package game.myStrategy.ui;

import game.myStrategy.game.GameService;
import game.myStrategy.game.draw.GameDrawService;
import game.myStrategy.game.draw.camera.Camera;
import game.myStrategy.game.resource.ResourceService;
import game.myStrategy.game.update.UpdateService;
import game.myStrategy.lib.events.EventBus;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.ui.game.gamePanel.control.Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class UIService {
    @Autowired
    private UpdateService updateService;
    @Autowired
    private EventBus eventBus;
    @Autowired
    private Control control;
    @Autowired
    private GameService gameService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private GameDrawService gameDrawService;


    private Vec2D mousePos;
    private Vec2D globalMousePos;

    public final void setMousePos(Vec2D pos) {
        getMousePos().setXY(pos);
        Camera camera = gameDrawService.getCamera();
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
