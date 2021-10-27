package game.myStrategy.lib.draw.drawer.drawCall;

import game.myStrategy.lib.draw.drawer.Drawer;

public interface DrawCall {
    void draw(Drawer drawer);

    void setLayer(int layer);

    int getLayer();
}
