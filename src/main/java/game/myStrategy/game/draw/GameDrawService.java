package game.myStrategy.game.draw;

import game.myStrategy.game.draw.camera.Camera;
import game.myStrategy.game.draw.drawers.DrawerJPanel;
import game.myStrategy.game.draw.drawers.DrawerMap;
import game.myStrategy.game.objects.managers.DrawService;
import game.myStrategy.game.update.Updater;
import game.myStrategy.lib.draw.drawer.Drawer;
import game.myStrategy.lib.draw.drawer.GameDrawer;
import game.myStrategy.lib.draw.drawer.settings.SettingsDrawer;
import game.myStrategy.lib.draw.drawer.settings.SettingsG;
import game.myStrategy.lib.math.Angle;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.lib.timer.Timer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import static game.myStrategy.lib.myFormatString.MyFormatString.date;

public final class GameDrawService {
    //region Static
    private static GameDrawService gameDrawService;
    private GameDrawService() {}
    public static void start(JPanel panel) {
        if (gameDrawService == null) {
            gameDrawService = new GameDrawService();
            gameDrawService.init(panel);
        }
    }

    public static Camera getCamera() {
        return gameDrawService.camera;
    }
    //endregion

    //region Fields
    private ThreadManager threadManager;

    private Camera camera;

    private Drawer finalDrawer;
    private GameDrawer painterMap;
    private GameDrawer painterObj;

    private DrawerMap drawerMap;
    private DrawerJPanel drawerJPanel;

    private SettingsDrawer settingsDrawer;

    //endregion

    private void setSettingsDrawers() {
        settingsDrawer = new SettingsDrawer();
        settingsDrawer.setAntialiasing(SettingsG.QUALITY);
        settingsDrawer.setRendering(SettingsG.QUALITY);
        settingsDrawer.setDithering(SettingsG.QUALITY);
        settingsDrawer.setTextAntialiasing(SettingsG.QUALITY);
        settingsDrawer.setFractionalMetrics(SettingsG.QUALITY);
        settingsDrawer.setAlphaInterpolation(SettingsG.QUALITY);
        settingsDrawer.setColorRendering(SettingsG.QUALITY);

        settingsDrawer.setResolutionVariant(RenderingHints.VALUE_RESOLUTION_VARIANT_BASE);
        settingsDrawer.setInterpolation(RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        settingsDrawer.setStrokeControl(RenderingHints.VALUE_STROKE_PURE);
    }

    private Vec2D size;

    private void setSettings() {
        setSettingsDrawers();
        finalDrawer = new Drawer(size, true);
        painterMap = new GameDrawer(size, true);
        painterObj = new GameDrawer(size, true);

        finalDrawer.setAll(settingsDrawer);
        painterMap.setAll(settingsDrawer);
        painterObj.setAll(settingsDrawer);

        Graphics2D[] graphics = new Graphics2D[] {painterMap.getG(), painterObj.getG()};
        camera = new Camera(new Vec2D(), size, graphics);
    }

    private void d(JPanel panel) {
        drawerMap = new DrawerMap(painterMap);
        drawerJPanel = new DrawerJPanel(panel, finalDrawer);
    }

    private void init(JPanel panel) {
        size = new Vec2D(panel.getSize());
        setSettings();
        d(panel);

        {
            Timer timer = new Timer(500);
            threadManager = ThreadManager.get();
            threadManager.drawMap.setExe(() -> drawerMap.draw());
            threadManager.drawObj.setExe(() -> DrawService.get().iterate(painterObj));
            threadManager.drawFinal.setExe(() -> {
                finalDrawer.fillRect(new Vec2D(), size, Color.GRAY, new Angle(0));
                finalDrawer.drawImage(new Vec2D(), painterMap.getImage());
                finalDrawer.drawImage(new Vec2D(), painterObj.getImage());
                this.screenshot();


                camera.getCurrentScale();

                if (timer.startF()) {
                    data = new String[] {
                            "UPS thread Map = " + threadManager.drawMap.getEPS(),
                            "UPS thread Obj = " + threadManager.drawObj.getEPS(),
                            "UPS thread Update = " + Updater.get().getEPS(),
                            "",
                            "camera.scale = " + camera.getCurrentScale(),
                            "camera.firstPos = " + camera.getFirstPos(),
                            "camera.endPos = " + camera.getEndPos(),
                            "camera.size = " + camera.getSize(),
                    };
                }


                finalDrawer.drawString(new Vec2D(), data, 16, Color.YELLOW);

                drawerJPanel.draw();

                painterMap.dispose();
                painterObj.dispose();
                camera.update();
            });
            try { Thread.sleep(1); } catch (InterruptedException ignore) {}
            threadManager.start();
        }
    }

    /*=======================================================*/
    private static String data[] = new String[0];
    public static boolean screenshot;

    private void screenshot() {
        if (screenshot) {
            String str = "resource/images/scr/" + date();
            try {
                File f = new File(str + "scr.png");
                ImageIO.write(finalDrawer.getImage(), "PNG", f);
            } catch(Exception e) {
                e.printStackTrace();
            }
            screenshot = false;
        }
    }

}
