package game.myStrategy.ui.game.gamePanel.events;

public enum MouseKeyCode {
    LEFT_MOUSE_BUTTON(-1),
    RIGHT_MOUSE_BUTTON(-2),
    WHEEL_MOUSE_BUTTON(-3),
    WHEEL_MOVE_UP(-4),
    WHEEL_MOVE_DOWN(-5),
    ;
    private final int code;

    MouseKeyCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
