package game.myStrategy.ui.game.gamePanel.control;

import game.myStrategy.Boot;
import game.myStrategy.game.draw.GameDrawService;
import game.myStrategy.game.draw.camera.Camera;
import game.myStrategy.game.objects.GameObjectFactory;
import game.myStrategy.game.update.UpdateService;
import game.myStrategy.lib.draw.FX.Animation.Animation;
import game.myStrategy.lib.events.EventBus;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.ui.game.gamePanel.events.MouseKeyCode;
import game.myStrategy.ui.game.gamePanel.events.UIEvent;
import game.myStrategy.ui.game.gamePanel.events.UIEventListener;
import game.myStrategy.ui.FrameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static game.myStrategy.ui.game.gamePanel.listener.MouseMotionListener.getGlobalMousePos;

/* TODO Продумать и реализовать реакции на комбинации клавиш */
@Component
@Scope("singleton")
public class Control {
    @Autowired
    private FrameController frameController;
    @Autowired
    private GameDrawService gameDrawService;
    @Autowired
    private UpdateService updateService;
    @Autowired
    private EventBus eventBus;
    @Autowired
    private GameObjectFactory gameObjectFactory;

    public static Control get() {
        return Boot.getBean(Control.class);
    }

    @PostConstruct
    private void postConstruct() {
//        eventBus.regListener("", );
//        UIEventListener = frameController.registerListener(this::update);
        loadConf();
//        bezierControl = new BezierControl(keys);
    }

    private Map<String, Integer> keys;
    private UIEventListener UIEventListener;
    private boolean enabled;

    public void enabled() {
        enabled = true;
        UIEventListener = frameController.registerListener(this::update);
    }
    public void disabled() {
        enabled = false;
        UIEventListener.unsubscribe();
    }

    private void loadConf() {
        keys = new HashMap<>();

        keys.put("exit", KeyEvent.VK_ESCAPE);
        keys.put("newTankSquad", KeyEvent.VK_T);
        keys.put("newTankSquadEnemy", KeyEvent.VK_Y);
        keys.put("newPersonSquad", KeyEvent.VK_G);
        keys.put("newPersonSquadEnemy", KeyEvent.VK_H);
        keys.put("pause", KeyEvent.VK_P);
        keys.put("screenshot", KeyEvent.VK_F2);
        keys.put("v", KeyEvent.VK_V);
        keys.put("alt", KeyEvent.VK_ALT);

        keys.put("newCurve", MouseKeyCode.LEFT_MOUSE_BUTTON.getCode());
        keys.put("newCurveEnable", KeyEvent.VK_B);

        keys.put("WHEEL_MOVE_UP", MouseKeyCode.WHEEL_MOVE_UP.getCode());
        keys.put("WHEEL_MOVE_DOWN", MouseKeyCode.WHEEL_MOVE_DOWN.getCode());
    }
    private boolean alt;
    private int temp = 0;

    public BezierControl bezierControl;

    protected void update(UIEvent e) {
        if(!enabled) return;

        class Ev {
            final UIEvent event = e;
            boolean isPressed(String s) {
                return event.isPressed(keys.get(s));
            }
            boolean isReleased(String s) {
                return event.isReleased(keys.get(s));
            }
            boolean isClicked(String s) {
                return event.isClicked(keys.get(s));
            }
        }
        Ev ev = new Ev();

        if (ev.isReleased("exit")) {
            frameController.showGameMenu();
        }
        Vec2D mousePos = getGlobalMousePos().clone();

        {
            if (ev.isReleased("newTankSquad")) {
                gameObjectFactory.createTankSquad(mousePos, 2, 200, 0);
            }
            if (ev.isReleased("newTankSquadEnemy")) {
                gameObjectFactory.createTankSquad(mousePos.addX(400), 2, 200, 1);
            }
            if (ev.isReleased("newPersonSquad")) {
                gameObjectFactory.createHumanSquad(mousePos, 10, 200, 0);
            }
            if (ev.isReleased("newPersonSquadEnemy")) {
                gameObjectFactory.createHumanSquad(mousePos, 10, 200, 1);
            }
        }

        getBezierControl().bezierCurve(e);

        if (ev.isReleased("pause")) updateService.pause();
        if (ev.isReleased("screenshot")) gameDrawService.takeScreenshot();

        if (ev.isReleased("v")) {
//            Генерация анимации взрыва
            if (temp < 5) temp++;
            if (temp == 5) temp = 1;
            new Animation(mousePos, "exp_0" + temp, false)
                    .enableUpdateDraw();
//            new Animation(Listener.getGlobalMousePos().clone(), "exp_01", false);
        }
        if (ev.isReleased("alt")) alt = !alt;

        {
            Camera camera = gameDrawService.getCamera();
            if (e.isClicked(keys.get("WHEEL_MOVE_UP")))   camera.upScale();
            if (e.isClicked(keys.get("WHEEL_MOVE_DOWN"))) camera.downScale();

            if (e.isPressed()) {
                if (e.isKeyCode(KeyEvent.VK_W)) camera.moveUp(true);
                if (e.isKeyCode(KeyEvent.VK_S)) camera.moveDown(true);
                if (e.isKeyCode(KeyEvent.VK_A)) camera.moveLeft(true);
                if (e.isKeyCode(KeyEvent.VK_D)) camera.moveRight(true);
            } else if (e.isReleased()) {
                if (e.isKeyCode(KeyEvent.VK_W)) camera.moveUp(false);
                if (e.isKeyCode(KeyEvent.VK_S)) camera.moveDown(false);
                if (e.isKeyCode(KeyEvent.VK_A)) camera.moveLeft(false);
                if (e.isKeyCode(KeyEvent.VK_D)) camera.moveRight(false);

                if (e.isKeyCode(KeyEvent.VK_ALT)) {
                    if (e.isKeyCode(MouseKeyCode.RIGHT_MOUSE_BUTTON)) camera.moveTo(mousePos);
                }
            }
        }

    }

    public BezierControl getBezierControl() {
        if (bezierControl == null) {
            bezierControl = new BezierControl(keys);
        }
        return bezierControl;
    }

    //[["Key_Y"], ["Key_Ctrl", "Key_Y"], ["Key_Alt", "Key_Y"]]
    @FunctionalInterface
    private interface Condition {
        boolean isTrue();

        class Checker {
            public boolean and(List<Condition> conditions) {
                for (Condition condition : conditions) if (!condition.isTrue()) return false;
                return true;
            }
            public boolean and(Condition... conditions) {
                for (Condition condition : conditions) if (!condition.isTrue()) return false;
                return true;
            }
            public boolean or(List<Condition> conditions) {
                for (Condition condition : conditions) if (condition.isTrue()) return true;
                return false;
            }
            public boolean or(Condition... conditions) {
                for (Condition condition : conditions) if (condition.isTrue()) return true;
                return false;
            }
        }

    }

    public boolean alt() {
        return alt;
    }
}
