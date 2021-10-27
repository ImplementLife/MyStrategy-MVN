package game.myStrategy.lib.draw.drawer;

import game.myStrategy.lib.draw.drawer.drawCall.DrawCall;

public interface DrawCallManager {
    void setDrawer(Drawer drawer);
    void add(DrawCall drawCall);
    void draw();
}
