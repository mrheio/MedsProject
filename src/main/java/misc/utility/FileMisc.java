package misc.utility;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileMisc {

    private static final String PROJECT_FOLDER = System.getProperty("user.dir");

    public static Path getApplicationPath() {
        return Paths.get(PROJECT_FOLDER);
    }

    public static Path getPathToFile(String... path) {
        return getApplicationPath();
    }

    public static void ifFileNotFound(String... path) {
        if (!Files.exists(getApplicationPath())) {
            getApplicationPath().toFile().mkdirs();
        }
    }

}
