package misc.user;

import misc.utility.FileMisc;
import model.roles.Doctor;
import model.roles.Patient;
import model.roles.Person;
import org.apache.commons.io.FileUtils;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserMiscTest {

    private static Path testUsersPath = FileMisc.getPathToFile("src/test/resources/data/users.json");
    private static Person p1 = new Patient("TEST PATIENT SURNAME", "TEST PATIENT FORENAME", LocalDate.of(1940, 4, 12), "TEST_PATIENT_EMAIL", "TEST_PATIENT", "TEST_PW_PATIENT");
    private static Person d1 = new Doctor("TEST DOCTOR SURNAME", "TEST DOCTOR FORENAME", LocalDate.of(1950, 4, 12), "TEST_DOCTOR_EMAIL", "TEST_DOCTOR", "TEST_PW_DOCTOR", "TEST TYPE", "TEST ADRESS");

    @BeforeClass
    public static void setup() throws IOException {
        UserMisc.usersPath = testUsersPath;
        FileMisc.createFileIfNotFound(testUsersPath);
        System.out.println(UserMisc.usersPath);
    }

    @Before
    public void setupUsers() {
        UserMisc.getUsers().clear();
        System.out.println("BEFORE: "+UserMisc.getUsers().size());
    }

    @After
    public void after() throws IOException {
        System.out.println("AFTER: "+UserMisc.getUsers().size()+"\n---------------------------------------\n");
        Files.delete(testUsersPath);
        FileMisc.createFileIfNotFound(testUsersPath);
    }

    @Test
    public void writeUsers() throws IOException {
        assertEquals("not 0", 0, UserMisc.getUsers().size());
        UserMisc.addUser(p1);
        System.out.println("AFTER ADDING 1: "+UserMisc.getUsers().size());
        assertEquals("not 1", 1, UserMisc.getUsers().size());
        UserMisc.writeUsers();
        UserMisc.addUser(d1);
        System.out.println("AFTER ADDING 2: "+UserMisc.getUsers().size());
        assertEquals("not 2", 2, UserMisc.getUsers().size());
        UserMisc.writeUsers();
    }

    @Test
    public void readUsers() throws IOException {
        UserMisc.addUser(p1);
        UserMisc.addUser(d1);
        System.out.println("AFTER ADDING 2: "+UserMisc.getUsers().size());
        System.out.println("WRITING USERS...");
        UserMisc.writeUsers();
        UserMisc.getUsers().remove(p1);
        System.out.println("AFTER DELETING 1: "+UserMisc.getUsers().size());
        UserMisc.getUsers().remove(d1);
        System.out.println("AFTER DELETING 2: "+UserMisc.getUsers().size());
        UserMisc.readUsers();
        System.out.println("AFTER READING: "+UserMisc.getUsers().size());
        assertEquals("not 2", 2, UserMisc.getUsers().size());
    }

    @Test
    public void updateUsers() throws IOException {
        UserMisc.addUser(p1);
        System.out.println("BEFORE UPDATE: "+UserMisc.getUsers().get(0).getUsername());
        UserMisc.writeUsers();
        p1.setUsername("UPDATED USERNAME");
        UserMisc.updateUsers(p1);
        UserMisc.getUsers().clear();
        UserMisc.readUsers();
        System.out.println("AFTER UPDATE: "+UserMisc.getUsers().get(0).getUsername());
        assertEquals("NOT THE SAME NAME", "UPDATED USERNAME", UserMisc.getUsers().get(0).getUsername());
    }

    @Test
    public void getUserYears() {
        assertEquals("NOT THE SAME YEARS", LocalDate.now().getYear()-1940, UserMisc.getUserYears(p1));
    }


}