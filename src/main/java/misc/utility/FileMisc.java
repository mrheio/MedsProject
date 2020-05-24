package misc.utility;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileMisc {

    private static final String projectFolderLocation = System.getProperty("user.dir");
    private static final Path usersPath = Paths.get(projectFolderLocation, "users.json");

    public static Path getUsersPath() {
        System.out.println(usersPath);
        return usersPath;
    }

}
