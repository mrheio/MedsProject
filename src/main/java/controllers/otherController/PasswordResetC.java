package controllers.otherController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import misc.users.UserMisc;
import misc.utility.ViewMisc;
import misc.utility.security.BCrypt;
import misc.utility.security.SecurityMisc;
import model.roles.Person;

import java.net.URL;
import java.util.ResourceBundle;

public class PasswordResetC implements Initializable {

    @FXML private Label usernameLabel;
    @FXML private Label badCredentials;
    @FXML private Label nameLabel;
    @FXML private TextField usernameTextField;
    @FXML private TextField emailTextField;
    @FXML private Button resetPWButton;
    @FXML private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML void cancelAction(ActionEvent event) {
        ViewMisc.showStage("/view/menuView/loginView.fxml");
    }


    @FXML void resetPWButtonAction(ActionEvent event) {

    }

    private void checkCredentials() {
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        Person person = null;
        boolean userExists = false;
        for (Person x: UserMisc.getUsers()) {
            if (username.equals(x.getUsername()) && x.getEmail().equals(email)) {
                userExists = true;
                person = x;
                break;
            }
        }
    }


}
