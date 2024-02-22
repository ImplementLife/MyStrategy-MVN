package game.myStrategy.ui.game.gamePanel.events;

import game.myStrategy.lib.events.Event;

import java.awt.event.KeyEvent;

import static game.myStrategy.ui.game.gamePanel.events.State.*;

public class UIEvent extends Event {
    //region Fields

    private final State state;
    private final int keyCode;

    //endregion

    //region Constructors

    public UIEvent(State state, int keyCode) {
        this.state = state;
        this.keyCode = keyCode;
    }

    public UIEvent(State state, MouseKeyCode keyCode) {
        this.state = state;
        this.keyCode = keyCode.getCode();
    }

    //endregion

    public boolean isPressed(int keyCode) {
        return isPressed() && this.keyCode == keyCode;
    }
    public boolean isReleased(int keyCode) {
        return isReleased() && this.keyCode == keyCode;
    }
    public boolean isClicked(int keyCode) {
        return isClicked() && this.keyCode == keyCode;
    }

    public boolean isPressed(MouseKeyCode keyCode) {
        return isPressed() && this.keyCode == keyCode.getCode();
    }
    public boolean isReleased(MouseKeyCode keyCode) {
        return isReleased() && this.keyCode == keyCode.getCode();
    }
    public boolean isClicked(MouseKeyCode keyCode) {
        return isClicked() && this.keyCode == keyCode.getCode();
    }

    public boolean isPressed() {
        return state == PRESSED;
    }
    public boolean isReleased() {
        return state == RELEASED;
    }
    public boolean isClicked() {
        return state == CLICKED;
    }

    public boolean isKeyCode(int keyCode) {
        return this.keyCode == keyCode;
    }
    public boolean isKeyCode(MouseKeyCode keyCode) {
        return this.keyCode == keyCode.getCode();
    }

    public int getKeyCode() {
        return keyCode;
    }
    public State getState() {
        return state;
    }
    public String getKeyText() {
        return getKeyText(keyCode);
    }

    public boolean isMouseKeyCode() {
        return keyCode < 0;
    }

    public static String getKeyText(int keyCode) {
        if (keyCode < 0) {
            for (MouseKeyCode value : MouseKeyCode.values()) {
                if (value.getCode() == keyCode) return value.name();
            }
        }
        return KeyEvent.getKeyText(keyCode);
    }
}
