package game.myStrategy.ui.game.gamePanel.control;

import game.myStrategy.game.draw.GameDrawService;
import game.myStrategy.game.unit.squads.SquadFabric;
import game.myStrategy.game.update.UpdateService;
import game.myStrategy.lib.draw.FX.Animation.Animation;
import game.myStrategy.ui.game.gamePanel.events.Event;
import game.myStrategy.ui.game.gamePanel.events.MouseKeyCode;
import game.myStrategy.ui.game.gamePanel.events.UIEventListener;
import game.myStrategy.ui.game.gamePanel.listener.Listener;
import game.myStrategy.ui.menu.FrameController;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* TODO Продумать и реализовать реакции на комбинации клавиш */
public class Control {
    //region Singleton
    private static Control instance;
    public static Control get() {
        if (instance == null) instance = new Control();
        return instance;
    }
    private Control() {
        UIEventListener = FrameController.get().registerListener(this::update);
        loadConf();
        bezierControl = new BezierControl(keys);
    }
    //endregion

    private Map<String, Integer> keys;
    private final UIEventListener UIEventListener;
    private boolean enabled;

    public void enabled() {
        enabled = true;
    }
    public void disabled() {
        enabled = false;
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

    protected void update(Event e) {
        if(!enabled) return;

        class Ev {
            final Event event = e;
            boolean isReleased(String s) {
                return event.isReleased(keys.get(s));
            }
        }
        Ev ev = new Ev();

        if (ev.isReleased("exit")) System.exit(0);

        if (ev.isReleased("newTankSquad")) {
            SquadFabric.createTankSquad(Listener.getGlobalMousePos().clone(), 2, 200, 0);
        }
        if (ev.isReleased("newTankSquadEnemy")) {
            SquadFabric.createTankSquad(Listener.getGlobalMousePos().clone().addX(400), 2, 200, 1);
        }
        if (ev.isReleased("newPersonSquad")) {
            SquadFabric.createHumanSquad(Listener.getGlobalMousePos().clone(), 10, 200, 0);
        }
        if (ev.isReleased("newPersonSquadEnemy")) {
            SquadFabric.createHumanSquad(Listener.getGlobalMousePos().clone(), 10, 200, 1);
        }

        bezierControl.bezierCurve(e);

        if (e.isReleased(keys.get("pause"))) UpdateService.get().pause();
        if (e.isReleased(keys.get("screenshot"))) GameDrawService.takeScreenshot();

        if (e.isReleased(keys.get("v"))) {
//            Генерация анимации взрыва
            if (temp < 5) temp++;
            if (temp == 5) temp = 1;
            new Animation(Listener.getGlobalMousePos().clone(), "exp_0" + temp, false)
                    .enableUpdateDraw();
//            new Animation(Listener.getGlobalMousePos().clone(), "exp_01", false);
        }
        if (e.isReleased(keys.get("alt"))) alt = !alt;

        if (e.isClicked(keys.get("WHEEL_MOVE_UP")))   GameDrawService.getCamera().upScale();
        if (e.isClicked(keys.get("WHEEL_MOVE_DOWN"))) GameDrawService.getCamera().downScale();
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
