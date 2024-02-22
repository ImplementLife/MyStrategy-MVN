package game.myStrategy.lib.draw.drawer;

import game.myStrategy.Boot;
import game.myStrategy.game.draw.GameDrawService;
import game.myStrategy.game.draw.camera.Camera;
import game.myStrategy.lib.draw.drawer.settings.SettingsDrawer;
import game.myStrategy.lib.draw.drawer.settings.SettingsG;
import game.myStrategy.lib.math.Angle;
import game.myStrategy.lib.math.bezier.BezierCurve;
import game.myStrategy.lib.math.Vec2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class DrawerCamera implements Drawer {
    //==========     Static     =============//
    public static boolean inCamera(Vec2D pos, double size) {
        Camera camera = Boot.getBean(GameDrawService.class).getCamera();
        if (pos.getX() < camera.firstPos.getX() - size || pos.getY() < camera.firstPos.getY() - size) return false;
        else return (pos.getX() < camera.endPos.getX() + size) && (pos.getY() < camera.endPos.getY() + size);
    }

    public static Vec2D posOnCamera(Vec2D pos) {
        return Vec2D.sub(pos, Boot.getBean(GameDrawService.class).getCamera().firstPos);
    }
    public static Vec2D posCamera() {
        return Boot.getBean(GameDrawService.class).getCamera().firstPos;
    }

    private static boolean rectInCamera(Vec2D posStart, Vec2D size) {
        Vec2D help = size.clone().scalar(0.5);
        double helpLength = help.getLength();
        return inCamera(posStart, helpLength);
    }

    //=======================================//
    private final DrawerAWT drawerImpl;
    public DrawerCamera(Vec2D size, boolean alpha) {
        this.drawerImpl = new DrawerAWT(size, alpha);
    }

    //=======================================//

    @Override
    public void drawString(Vec2D pos, String text, int size, Color color) {
        drawerImpl.drawString(posOnCamera(pos), text, size, color);
    }

    @Override
    public void drawString(Vec2D pos, String[] text, int size, Color color) {
        drawerImpl.drawString(posOnCamera(pos), text, size, color);
    }

    @Override
    public void drawShape(Vec2D pos, Angle angle, Color color, float l, int c, float t) {
        drawerImpl.drawShape(posOnCamera(pos), angle, color, l, c, t);
    }

    @Override
    public void fillShape(Vec2D pos, Angle angle, Color color, float s, int c) {
        drawerImpl.fillShape(posOnCamera(pos), angle, color, s, c);
    }

    @Override
    public void fillShape(Vec2D pos, Angle angle, Color colF, Color colD, float s, int c, float t) {
        drawerImpl.fillShape(posOnCamera(pos), angle, colF, colD, s, c, t);
    }

    @Override
    public void drawRect(Vec2D pos, Vec2D size, Color color, Angle angle, float t) {
        drawerImpl.drawRect(posOnCamera(pos), size, color, angle, t);
    }

    @Override
    public void fillRect(Vec2D pos, Vec2D size, Color color, Angle angle) {
        drawerImpl.fillRect(posOnCamera(pos), size, color, angle);
    }

    @Override
    public void fillRect(Vec2D pos, Vec2D size, Color colF, Color colD, Angle angle, float t) {
        drawerImpl.fillRect(posOnCamera(pos), size, colF, colD, angle, t);
    }

    @Override
    public void drawBezierCurve(BezierCurve curve, Color color, float t) {
        drawerImpl.setOffset(posCamera());
        drawerImpl.drawBezierCurve(curve, color, t);
    }

    @Override
    public void drawLine(Vec2D v1, Vec2D v2, Color color, float thickness) {
        drawerImpl.drawLine(posOnCamera(v1), posOnCamera(v2), color, thickness);
    }

    @Override
    public void drawCircle(Vec2D pos, float radius, Color color, float t) {
        drawerImpl.drawCircle(posOnCamera(pos), radius, color, t);
    }

    @Override
    public void fillCircle(Vec2D pos, float radius, Color color) {
        drawerImpl.fillCircle(posOnCamera(pos), radius, color);
    }

    @Override
    public void fillCircle(Vec2D pos, float radius, Color colF, Color colD, float t) {
        drawerImpl.fillCircle(posOnCamera(pos), radius, colF, colD, t);
    }

    @Override
    public void drawImage(Vec2D pos, Image image) {
        drawerImpl.drawImage(posOnCamera(pos), image);
    }

    @Override
    public void drawImage(Vec2D pos, Image image, Angle angle) {
        drawerImpl.drawImage(posOnCamera(pos), image, angle);
    }

    @Override
    public void drawImage(Vec2D pos, Image image, Angle angle, Vec2D offset) {
        drawerImpl.drawImage(posOnCamera(pos), image, angle, offset);
    }

    //=======================================//
    public BufferedImage getImage() {
        return drawerImpl.getImage();
    }
    public Graphics2D getG() {
        return drawerImpl.getG();
    }
    public void dispose() {
        drawerImpl.dispose();
    }

    public void setAntialiasing(SettingsG value) {
        drawerImpl.setAntialiasing(value);
    }
    public void setRendering(SettingsG value) {
        drawerImpl.setRendering(value);
    }
    public void setDithering(SettingsG value) {
        drawerImpl.setDithering(value);
    }
    public void setTextAntialiasing(SettingsG value) {
        drawerImpl.setTextAntialiasing(value);
    }
    public void setFractionalMetrics(SettingsG value) {
        drawerImpl.setFractionalMetrics(value);
    }
    public void setAlphaInterpolation(SettingsG value) {
        drawerImpl.setAlphaInterpolation(value);
    }
    public void setColorRendering(SettingsG value) {
        drawerImpl.setColorRendering(value);
    }

    public void setInterpolation(Object value) {
        drawerImpl.setInterpolation(value);
    }
    public void setResolutionVariant(Object value) {
        drawerImpl.setResolutionVariant(value);
    }
    public void setStrokeControl(Object value) {
        drawerImpl.setStrokeControl(value);
    }

    public void setAll(SettingsDrawer settingsDrawer) {
        drawerImpl.setAll(settingsDrawer);
    }
}
