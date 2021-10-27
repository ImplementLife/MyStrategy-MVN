package game.myStrategy.lib.draw.drawer.drawCall;

import game.myStrategy.lib.draw.drawer.Drawer;

import java.util.function.Consumer;

public class DrawCallCustom extends DrawCallBase {
    private Consumer<Drawer> customDrawCode;

    public void setCustomDrawCode(Consumer<Drawer> customDrawCode) {
        this.customDrawCode = customDrawCode;
    }

    @Override
    public void draw(Drawer drawer) {
        customDrawCode.accept(drawer);
    }
}
