package game.myStrategy.game.objects.managers;

public enum GameObjectType {
    DECAL(-1),
    CURVE(0),
    ROAD(1),
    RAIL_WAY(2),
    PLATFORM(3),

    ARROW(4),
    BASE(5),
    CLICK_ANIMATION(6),

    HUMAN(7),

    ROLLING_STOCK(12),

    WAGON(13),
    CAR(15),
    TANK(16),

    BULLET(18),
    EXPLOSION(19),
    SQUAD(20),
    BUTTON(21),

    DEV(10_000),
    ;

    //=======================================//

    public final byte type;
    GameObjectType(int type) { this.type = (byte) type; }
}
