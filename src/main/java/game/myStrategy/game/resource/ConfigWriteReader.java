package game.myStrategy.game.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class ConfigWriteReader extends HashMap<String, String> {

    public void load(String path) throws IOException {
        load(new File(path));
    }

    public void load(File file) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));
        for (String s : properties.stringPropertyNames()) {
            put(s, properties.getProperty(s));
        }
    }

    public void save(String path) throws IOException {
        Properties properties = new Properties();
        properties.putAll(this);
        properties.store(new FileOutputStream(path), "comment");
    }

}
