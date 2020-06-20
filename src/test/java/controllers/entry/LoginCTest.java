package controllers.entry;

import misc.user.UserMisc;
import org.junit.BeforeClass;

import java.io.IOException;

import static org.junit.Assert.*;

public class LoginCTest {

    public static final String TEST_USER = "user";
    public static final String TEST_PW = "password";
    private LoginC loginC;

    @BeforeClass
    public static void setup() throws IOException {

        UserMisc.readUsers();
    }



}