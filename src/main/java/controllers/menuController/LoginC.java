package controllers.menuController;


import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import misc.utility.BCrypt;
import misc.users.UserMisc;
import misc.utility.NodeMisc;
import misc.utility.ViewMisc;
import model.roles.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.util.Arrays.asList;

public class LoginC implements Initializable {

    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Label badLogin;
    @FXML private Button loginButton;
    @FXML private Button cancelButton;
    @FXML private Button createNewAccountButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NodeMisc.hideDisableNode(asList(badLogin), asList(loginButton));
    }

    @FXML void keyReleaseProperty() {
        BooleanBinding booleanBinding = Bindings.or(usernameTextField.textProperty().isEmpty(), passwordField.textProperty().isEmpty());
        loginButton.disableProperty().bind(booleanBinding);
    }

    @FXML void loginAction() throws IOException {
        checkUser();
    }

    @FXML void cancelAction() {
        Platform.exit();
    }

    @FXML void createNewAccountButtonAction() throws IOException {
        ViewMisc.showStage("/view/otherView/registerView.fxml");
    }
    
    private void checkUser() {
        String username = usernameTextField.getText();
        String pw = passwordField.getText();
        boolean userExists = false;
        Person person = null;
        for (Person x: UserMisc.getUsers()) {
            if (username.equals(x.getUsername()) && BCrypt.checkpw(pw, x.getPassword())) {
                userExists = true;
                person = x;
                break;
            }
        }
        if (userExists == true) {
            UserMisc.setLoggedUser(person);
            if (person.getRole().equals("Patient")) {
                System.out.println("Logged as patient");
                ViewMisc.showStage("/view/menuView/patientView.fxml");
            }
            if (person.getRole().equals("Doctor")) {
                System.out.println("Logged as doctor");
                ViewMisc.showStage("/view/menuView/doctorView.fxml");
            }
        }
        if (userExists == false) {
            NodeMisc.showNode(badLogin);
            passwordField.clear();
        }
    }




}
