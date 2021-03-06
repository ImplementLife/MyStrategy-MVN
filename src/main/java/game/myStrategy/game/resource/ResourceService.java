package game.myStrategy.game.resource;

import game.myStrategy.game.context.InContext;
import game.myStrategy.game.resource.model.Conf;
import game.myStrategy.game.resource.model.Gif;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class ResourceService extends InContext {
    //region Singleton
    private static ResourceService instance;
    public static ResourceService get() {
        if (instance == null) instance = new ResourceService();
        return instance;
    }
    private ResourceService() {}
    //endregion

    //region Fields

    private final FileLoader fileLoader = new FileLoader();
    private final ConfigWriteReader configWriteReader = new ConfigWriteReader();
    private final ImageLoader imageLoader = new ImageLoader();

    private final String resLoc = "resource";
    private boolean resourceLoaded;
    private final Resource<Image> images = new Resource<>();
    private final Resource<Gif> gifs = new Resource<>();
    private final Resource<Conf> configs = new Resource<>();

    //endregion

    //region Load

    public void load() {
        if (resourceLoaded) return;
        resourceLoaded = true;
        loadImages();
        Resource.load();
    }

    private void loadImages() {
        ArrayList<File> files = fileLoader.getFileList(new File(resLoc), ".png");
        for (File f : files) images.put(f.getPath(), ImageLoader.load(f));
    }

    private void loadGifs() {

    }

    private void loadAudio() {

    }

    private void loadConf() {

    }

    //endregion

    //region Get

    public Image getImage(String path) {
        return images.get(path);
    }

    public Gif getGif(String path) {
        return gifs.get(path);
    }

    public Conf getConf(String path) {
        return configs.get(path);
    }

    public Conf getConfVal(String path, String name) {
        return configs.get(path);
    }

    //endregion

}
