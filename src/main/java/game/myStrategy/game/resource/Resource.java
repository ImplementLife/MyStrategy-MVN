package game.myStrategy.game.resource;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Resource<T> extends HashMap<String, T> {
    private static Resource<Image[]> resImageArray;
    private static Resource<Image> resImage;

    public static Resource<Image[]> getResImageArray() {
        if (resImageArray == null) resImageArray = new Resource<>();
        return resImageArray;
    }
    public static Resource<Image> getResImage() {
        if (resImage == null) resImage = new Resource<>();
        return resImage;
    }

    static FileLoader loader = new FileLoader();

    public static void load() {
        String path = "resource/images/animations/";
        File dir = new File(path);

        for (File f : loader.getFileList(dir, ".gif")) {
            try {
                getResImageArray().put(path + f.getName(), ImageLoader.loadGif(f));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        resImage = new Resource<>();
    }

    Resource() {}
}
