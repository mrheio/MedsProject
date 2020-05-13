package controllers.menuController;


import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import misc.utility.BCrypt;
import misc.users.UserMisc;
import misc.utility.ViewMisc;
import model.roles.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginC implements Initializable {

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Label badLogin;
    @FXML private Button loginButton;
    @FXML private Button cancelButton;
    @FXML private Button createNewAccountButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        badLogin.setVisible(false);
        loginButton.setDisable(true);
    }

    @FXML void keyReleaseProperty() {
        BooleanBinding booleanBinding = Bindings.or(username.textProperty().isEmpty(), password.textProperty().isEmpty());
        loginButton.disableProperty().bind(booleanBinding);
    }

    @FXML void loginAction() throws IOException {
        boolean userExists = false;
        for (Person x: UserMisc.getUsers()) {
            if (username.getText().equals(x.getUsername()) && BCrypt.checkpw(password.getText(), x.getPassword())) {
                userExists = true;
                UserMisc.setLoggedUser(x);
                if (x.getRole().equals("Patient")) {
                    System.out.println("Logged as patient");
                    ViewMisc.showStage("/view/menuView/patientView.fxml");
                }
                if (x.getRole().equals("Doctor")) {
                    System.out.println("Logged as doctor");
                    ViewMisc.showStage("/view/menuView/doctorView.fxml");
                }
            }
        }
        if (userExists == false) {
            badLogin.setVisible(true);
            password.clear();
        }
    }

    @FXML void cancelAction() {
        Platform.exit();
    }

    @FXML void createNewAccountButtonAction() throws IOException {
        ViewMisc.showStage("/view/otherView/registerView.fxml");
    }




}
