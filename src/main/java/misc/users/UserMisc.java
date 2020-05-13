package misc.users;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import misc.utility.ViewMisc;
import model.roles.Person;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserMisc {

    private static List<Person> users = new ArrayList<Person>();
    private static Person loggedUser;

    public static void addUser(Person person) {
        users.add(person);
    }

    public static void writeUsers(String fileName) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.registerModule(new JavaTimeModule());
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(Paths.get(fileName).toFile(), users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readUsers(String fileName) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            users = mapper.readValue(Paths.get(fileName).toFile(), new TypeReference<List<Person>>(){});
            System.out.println("Reading users");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Person> getUsers() {
        return users;
    }

    public static void setUsers(List<Person> users) {
        UserMisc.users = users;
    }

    public static void logOutUser() {
        setLoggedUser(null);
        ViewMisc.showStage("/view/menuView/loginView.fxml");
    }

    public static Person getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(Person loggedUser) {
        UserMisc.loggedUser = loggedUser;
    }
}
