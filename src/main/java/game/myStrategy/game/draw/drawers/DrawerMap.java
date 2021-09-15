package game.myStrategy.game.draw.drawers;

import game.myStrategy.game.map.MapGenerator;
import game.myStrategy.game.map.Tile;
import game.myStrategy.game.map.TypeTile;
import game.myStrategy.lib.draw.drawer.GameDrawer;
import game.myStrategy.game.resource.ImageLoader;
import game.myStrategy.lib.math.Vec2D;
import game.myStrategy.ui.game.gamePanel.listener.Listener;

import java.awt.*;
import java.util.HashMap;

public class DrawerMap {
    private static final String PATH_IMAGES = "resource/images/tiles/";

    private final GameDrawer drawer;

    public DrawerMap(GameDrawer drawer) {
        this.drawer = drawer;
        this.sizeTile = 128;
        this.sizeMap = MapGenerator.getSize();
        init(new Vec2D(sizeTile, sizeTile));
    }

    private final int sizeTile;
    private final Vec2D sizeMap;
    private static HashMap<String, Image> tiles;

    private static void init(Vec2D sizeTile) {
        if (tiles == null) {
            tiles = new HashMap<>();

            tiles.put("00", ImageLoader.resize(sizeTile, PATH_IMAGES + "grass/00.png"));
            tiles.put("01", ImageLoader.resize(sizeTile, PATH_IMAGES + "grass/01.png"));
            tiles.put("02", ImageLoader.resize(sizeTile, PATH_IMAGES + "grass/02.png"));
            tiles.put("03", ImageLoader.resize(sizeTile, PATH_IMAGES + "grass/03.png"));
            tiles.put("04", ImageLoader.resize(sizeTile, PATH_IMAGES + "grass/04.png"));
            tiles.put("05", ImageLoader.resize(sizeTile, PATH_IMAGES + "grass/05.png"));
            tiles.put("06", ImageLoader.resize(sizeTile, PATH_IMAGES + "grass/06.png"));
            tiles.put("07", ImageLoader.resize(sizeTile, PATH_IMAGES + "grass/07.png"));
            tiles.put("08", ImageLoader.resize(sizeTile, PATH_IMAGES + "grass/08.png"));
            tiles.put("09", ImageLoader.resize(sizeTile, PATH_IMAGES + "grass/09.png"));
            tiles.put("10", ImageLoader.resize(sizeTile, PATH_IMAGES + "grass/10.png"));
            tiles.put("11", ImageLoader.resize(sizeTile, PATH_IMAGES + "grass/11.png"));
            tiles.put("12", ImageLoader.resize(sizeTile, PATH_IMAGES + "grass/12.png"));

            tiles.put("water", ImageLoader.resize(sizeTile, PATH_IMAGES + "water/00.png"));
        }
    }

    public void draw() {
        drawer.drawImage(new Vec2D(), tiles.get("water2"));
        for (int X = 1; X < sizeMap.getX(); X++) {
            for (int Y = 1; Y < sizeMap.getY(); Y++) {
                Vec2D pos = new Vec2D(X, Y).scalar(sizeTile);
                if (GameDrawer.inCamera(pos, sizeTile)) {
                    Vec2D posInCamera = pos.sub(new Vec2D(sizeTile, sizeTile));

                    Tile tile = MapGenerator.getTile(new Vec2D(X, Y));
                    if (tile.getType() == TypeTile.WATER) drawer.drawImage(posInCamera, tiles.get("water"));
                    else drawer.drawImage(posInCamera, tiles.get("00"));

                    if (tile.getSubTile() != null) {
                        drawer.drawImage(posInCamera, tiles.get(tile.getSubTile()));
                    }

                    //drawSquad(pos, posInCamera);
                }
            }
        }
    }

    private void drawSquad(Vec2D pos, Vec2D posInCamera) {
        Vec2D globalMouse = Listener.getGlobalMousePos().clone();
        if (pos.getX() <= globalMouse.getX() && pos.getX() + sizeTile >= globalMouse.getX()) {
            if (pos.getY() <= globalMouse.getY() && pos.getY() + sizeTile >= globalMouse.getY()) {
                drawer.drawRect(posInCamera, new Vec2D(sizeTile, sizeTile), Color.BLACK, null, 3);
            }
        }
    }
}
