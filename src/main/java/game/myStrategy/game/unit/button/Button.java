package game.myStrategy.game.unit.button;

import game.myStrategy.game.objects.GameObject;
import game.myStrategy.game.objects.managers.GameObjectType;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.lib.threads.bt.DT;
import game.myStrategy.ui.game.gamePanel.events.MouseKeyCode;
import game.myStrategy.ui.game.gamePanel.events.UIEventListener;
import game.myStrategy.ui.menu.FrameController;

import java.awt.event.KeyEvent;

public abstract class Button extends GameObject {
    /*==========     Static     =============*/
    private static final GameObjectType TYPE = GameObjectType.BUTTON;

    /*=======================================*/
    public enum Positioning {
        TOP_RIGHT, TOP, TOP_LEFT,
        RIGHT, CENTER, LEFT,
        BOTTOM_RIGHT, BOTTOM, BOTTOM_LEFT
    }
    public enum ButtonState {
        ACTIVE,
        DISABLE,
        PRESSED,
        FOCUSED;
    }

    protected static boolean ctrl;
    /*=======================================*/
    private final UIEventListener UIEventListener;
    private boolean updateState = true;
    protected boolean visible = true;

    protected ButtonState state = ButtonState.ACTIVE;
    private Runnable updateExecute;

    protected Positioning positioning = Positioning.CENTER;

    /*=======================================*/
    public Button() {
        super(TYPE);
        UIEventListener = FrameController.get().registerListener(e -> {
            if (visible) {
                if (e.isReleased()) {
                    if (e.isKeyCode(MouseKeyCode.LEFT_MOUSE_BUTTON)) setFocus();
                    if (e.isKeyCode(MouseKeyCode.RIGHT_MOUSE_BUTTON) && this.state == ButtonState.PRESSED) {
                        if (updateExecute != null) updateExecute.run();
                    }
                    if (e.isKeyCode(KeyEvent.VK_CONTROL)) ctrl = false;
                }
                if (e.isPressed(KeyEvent.VK_CONTROL)) ctrl = true;
            }
        });
    }
    public Button(Positioning positioning) {
        this();
        this.positioning = positioning;
    }

    public abstract void setPos(Vec2D pos);
    public abstract Vec2D getPos();
    protected abstract void updateState();

    /*=======================================*/
    public void setCanUpdate(boolean updateState) {
        this.updateState = updateState;
    }
    public void setPositioning(Positioning positioning) {
        this.positioning = positioning;
    }
    public void setExe(Runnable exe) {
        updateExecute = exe;
    }
    public void setFocus() {
        if (updateState) {
            if (state == ButtonState.FOCUSED) {
                state = ButtonState.PRESSED;
            } else if (!ctrl) {
                state = ButtonState.ACTIVE;
            }
        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    /*=======================================*/
    public boolean isPressed() {
        return state == ButtonState.PRESSED;
    }
    public boolean isFocused() {
        return state == ButtonState.FOCUSED;
    }
    public boolean isActive() {
        return state == ButtonState.ACTIVE;
    }

    /*=======================================*/
    @Override
    public void update(DT dt) {
        if (updateState) updateState();
    }
}