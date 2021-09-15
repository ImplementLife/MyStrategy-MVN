package game.myStrategy.game.resource;

import java.io.File;
import java.util.ArrayList;

public final class FileLoader {
    FileLoader() {}
    private String suffix;
    private ArrayList<File> files;

    /**
     * Поиск файлов в каталоге по расширению
     */
    ArrayList<File> getFileList(File path, String suffix) {
        FileLoader fileLoader = new FileLoader();
        fileLoader.suffix = suffix;
        fileLoader.files = new ArrayList<>();
        return fileLoader.getFileList(path);
    }

    private ArrayList<File> getFileList(File path) throws NullPointerException {
        reader(path);
        return files;
    }

    private void reader(File path) throws NullPointerException {
        if (path.isDirectory()) {
            for (File f : path.listFiles()) {
                if (f.isFile()) {
                    String str = f.getName();
                    if (str.substring(str.lastIndexOf('.')).equals(suffix)) {
                        files.add(f);
                    }
                } else {
                    reader(f);
                }
            }
        }
    }
}
