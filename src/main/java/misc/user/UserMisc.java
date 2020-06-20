package misc.user;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import misc.utility.FileMisc;
import misc.utility.ViewMisc;
import model.roles.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class UserMisc {

    private static List<Person> users = new ArrayList<Person>();
    private static Person loggedUser;
    private static final Path usersPath = FileMisc.getUsersPath();

    public static void addUser(Person person) {
        users.add(person);
    }

    public static void setUsers(List<Person> users) {
        UserMisc.users = users;
    }

    public static List<Person> getUsers() {
        return users;
    }

    public static Person getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(Person loggedUser) {
        UserMisc.loggedUser = loggedUser;
    }

    public static void writeUsers() throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.registerModule(new JavaTimeModule());
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(usersPath.toFile(), users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readUsers() throws IOException {
        if (!Files.exists(usersPath)) {
            Files.createFile(usersPath);
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            users = mapper.readValue(usersPath.toFile(), new TypeReference<List<Person>>(){});
            System.out.println("reading users...");
        } catch (MismatchedInputException e) {
            System.out.println("users.json is empty");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("users read");
    }

    public static void updateUsers(Person... persons) throws IOException {
        for (Person x: persons) {
            if (users.contains(x)) {
                for (Person y: users) {
                    if (y.equals(x)) {
                        users.set(users.indexOf(y), x);
                    }
                }
            } else {
                users.add(x);
            }
        }
        UserMisc.writeUsers();
    }

    public static void logOutUser() {
        setLoggedUser(null);
        ViewMisc.showLogin();
    }

    public static int getUserYears(Person person) {
        return Period.between(person.getBirthday(), LocalDate.now()).getYears();
    }

}
