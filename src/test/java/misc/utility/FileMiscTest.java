package misc.utility;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileMiscTest {

    @Test
    public void checkUsersPathLocation() {
        FileMisc fileMisc = new FileMisc();
        fileMisc.ifFileNotFound();
    }
}