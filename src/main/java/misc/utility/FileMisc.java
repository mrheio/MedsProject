package misc.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileMisc {

    public static String PROJECT_FOLDER = System.getProperty("user.dir");

    public static Path getApplicationPath() {
        return Paths.get(PROJECT_FOLDER);
    }

    public static Path getPathToFile(String path) {
        return getApplicationPath().resolve(path);
    }

    public static void createFileIfNotFound(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
    }

}
