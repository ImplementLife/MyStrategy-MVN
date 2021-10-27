package game.myStrategy.lib.draw.drawer.drawCall;

import game.myStrategy.lib.draw.drawer.Drawer;

import java.awt.*;
import java.util.Objects;

public class DrawCallPrimitive extends DrawCallBase {

    private Color colorFill;
    private Color colorDraw;
    private float length;
    private int countVertex;
    private float thicknesses;

    private Shape shape;
    private Type type;

    public enum Shape {
        LINE, SHAPE, RECT, CIRCLE,
    }

    public enum Type {
        DRAW, FILL
    }

    @Override
    public void draw(Drawer drawer) {
        switch (type) {
            case DRAW:
                Objects.requireNonNull(colorDraw, "colorDraw");
                drawer.drawShape(super.pos, super.angle, colorDraw, length, countVertex, thicknesses);
            break;
            case FILL:
                Objects.requireNonNull(colorFill, "colorFill");
                Objects.requireNonNull(colorDraw, "colorDraw");
                drawer.fillShape(super.pos, super.angle, colorFill, colorDraw, length, countVertex, thicknesses);
            break;
            default:
        }
    }
}
