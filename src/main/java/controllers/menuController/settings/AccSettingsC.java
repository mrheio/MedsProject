package controllers.menuController.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import misc.users.UserMisc;
import misc.utility.NodeMisc;
import misc.utility.ViewMisc;
import misc.utility.security.BCrypt;
import model.roles.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

abstract public class AccSettingsC implements Initializable {

    @FXML TextField emailTextField;
    @FXML TextField usernameTextField;
    @FXML TextField roleTextField;
    @FXML PasswordField oldPasswordField;
    @FXML PasswordField newPasswordField;
    @FXML PasswordField confirmPasswordField;
    @FXML Label badOldPassword;
    @FXML Label badNewPassword;
    @FXML Hyperlink forgotPasswordHL;

    Person loggedUser = UserMisc.getLoggedUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    void configureAccountTab() {
        emailTextField.setText(loggedUser.getEmail());
        usernameTextField.setText(loggedUser.getUsername());
        roleTextField.setText(loggedUser.getRole());
    }

    @FXML void editEmailButtonAction(ActionEvent event) throws IOException {
        NodeMisc.enableNode(emailTextField);
    }

    @FXML void editUsernameButtonAction(ActionEvent event) {
        NodeMisc.enableNode(usernameTextField);
    }

    @FXML void updateAccDetailsButtonAction(ActionEvent actionEvent) throws IOException {
        loggedUser.setEmail(emailTextField.getText());
        loggedUser.setUsername(usernameTextField.getText());
    }

    @FXML void changePasswordButtonAction(ActionEvent event) throws IOException {
        String oldPW = oldPasswordField.getText();
        String newPW = newPasswordField.getText();
        String confirmNewPW = confirmPasswordField.getText();
        if (!BCrypt.checkpw(oldPW, loggedUser.getPassword())) {
            NodeMisc.showNode(badOldPassword);
        }
        if (!newPW.equals(confirmNewPW)) {
            NodeMisc.showNode(badNewPassword);
        }
        if (BCrypt.checkpw(oldPW, loggedUser.getPassword())) {
            NodeMisc.hideNode(badOldPassword);
        }
        if (newPW.equals(confirmNewPW)) {
            NodeMisc.hideNode(badNewPassword);
        }
        if (BCrypt.checkpw(oldPW, loggedUser.getPassword()) && newPW.equals(confirmNewPW) && !newPW.isEmpty() && !confirmNewPW.isEmpty()) {
            loggedUser.setPassword(BCrypt.hashpw(newPW, BCrypt.gensalt(12)));
            UserMisc.updateUsers(loggedUser);
            oldPasswordField.clear();
            newPasswordField.clear();
            confirmPasswordField.clear();
            NodeMisc.hideNode(badOldPassword, badNewPassword);
            ViewMisc.showStage("/view/menuView/loginView.fxml");
            UserMisc.setLoggedUser(null);
        }
    }

}
