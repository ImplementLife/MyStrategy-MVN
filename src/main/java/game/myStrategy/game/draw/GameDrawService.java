package game.myStrategy.game.draw;

import game.myStrategy.game.draw.camera.Camera;
import game.myStrategy.game.draw.drawers.DrawerJPanel;
import game.myStrategy.game.draw.drawers.DrawerMap;
import game.myStrategy.game.update.UpdateService;
import game.myStrategy.lib.draw.drawer.DrawerAWT;
import game.myStrategy.lib.draw.drawer.DrawerCamera;
import game.myStrategy.lib.draw.drawer.settings.SettingsDrawer;
import game.myStrategy.lib.draw.drawer.settings.SettingsG;
import game.myStrategy.lib.events.EventBus;
import game.myStrategy.lib.math.Angle;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.lib.timer.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static game.myStrategy.game.context.Context.context;

@Component
@Scope("singleton")
public final class GameDrawService {
    @Autowired
    private EventBus eventBus;
    @Autowired
    private UpdateService updateService;
    private boolean pause;

    //region Static
    public void start(JPanel panel) {
        init(panel);
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public Camera getCamera() {
        return camera;
    }
    //endregion

    //region Fields
    private ThreadManager threadManager;

    private Camera camera;

    private DrawerAWT finalDrawerImpl;
    private DrawerCamera painterMap;
    private DrawerCamera painterObj;

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
        finalDrawerImpl = new DrawerAWT(size, true);
        painterMap = new DrawerCamera(size, true);
        painterObj = new DrawerCamera(size, true);

        finalDrawerImpl.setAll(settingsDrawer);
        painterMap.setAll(settingsDrawer);
        painterObj.setAll(settingsDrawer);

        Graphics2D[] graphics = new Graphics2D[] {painterMap.getG(), painterObj.getG()};
        camera = new Camera(new Vec2D(), size, graphics);
    }

    private void d(JPanel panel) {
        drawerMap = new DrawerMap(painterMap);
        drawerJPanel = new DrawerJPanel(panel, finalDrawerImpl);
    }

    private void init(JPanel panel) {
        size = new Vec2D(panel.getSize());
        setSettings();
        d(panel);
        context().setDrawService(new DrawService());

        {
            Timer timer = new Timer(500);
            threadManager = ThreadManager.get();
            threadManager.drawMap.setExe(() -> drawerMap.draw());
            threadManager.drawObj.setExe(() -> context().getDrawService().iterate(painterObj));
            threadManager.drawFinal.setExe(() -> finalDraw(timer));
            try { Thread.sleep(1); } catch (InterruptedException ignore) {}
            threadManager.start();
        }
    }

    private void finalDraw(Timer timer) {
        finalDrawerImpl.fillRect(new Vec2D(), size, Color.GRAY, new Angle(0));
        finalDrawerImpl.drawImage(new Vec2D(), painterMap.getImage());
        finalDrawerImpl.drawImage(new Vec2D(), painterObj.getImage());
        this.screenshot();

        if (timer.startF()) {
            devInfoData = new String[] {
                    "UPS thread Map = " + threadManager.drawMap.getEPS(),
                    "UPS thread Obj = " + threadManager.drawObj.getEPS(),
                    "UPS thread Update = " + updateService.getEPS(),
                    "",
                    "camera.scale = " + camera.getCurrentScale(),
                    "camera.firstPos = " + camera.getFirstPos(),
                    "camera.endPos = " + camera.getEndPos(),
                    "camera.size = " + camera.getSize(),
            };
        }

        finalDrawerImpl.drawString(new Vec2D(), devInfoData, 16, Color.YELLOW);

        if (!pause) drawerJPanel.draw();

        painterMap.dispose();
        painterObj.dispose();
        camera.update();
    }

    /*=======================================================*/
    private static String devInfoData[] = new String[0];

    private boolean takeScreenshot;
    public void takeScreenshot() {
        takeScreenshot = true;
    }
    private void screenshot() {
        if (takeScreenshot) {
            String directoryName = "screenshots/";
            File directory = new File(directoryName);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH-mm-ss"));
            String fileName = directoryName + "screenshot_" + timeStamp + ".png";

            try {
                File file = new File(fileName);
                ImageIO.write(finalDrawerImpl.getImage(), "PNG", file);
            } catch (IOException e) {
                System.err.println("Failed to save screenshot: " + e.getMessage());
            }

            takeScreenshot = false;
        }
    }

}
