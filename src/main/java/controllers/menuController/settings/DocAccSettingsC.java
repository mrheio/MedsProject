package controllers.menuController.settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import misc.users.UserMisc;
import misc.utility.security.BCrypt;
import misc.utility.NodeMisc;
import misc.utility.ViewMisc;
import model.roles.Doctor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DocAccSettingsC implements Initializable {

    @FXML private TextField emailTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField roleTextField;
    @FXML private TextField addressTextField;
    @FXML private Hyperlink forgotPasswordHL;
    @FXML private PasswordField oldPasswordField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label badOldPassword;
    @FXML private Label badNewPassword;
    @FXML private ComboBox doctorOptionsComboBox;

    private Doctor loggedDoctor = (Doctor) UserMisc.getLoggedUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureDoctorSettingsCB();
        configureAccountTab();
    }

    @FXML void doctorOptionsComboBoxAction(ActionEvent actionEvent) {
        if (doctorOptionsComboBox.getSelectionModel().getSelectedItem().equals("Log out")) {
            UserMisc.logOutUser();
        }
        if (doctorOptionsComboBox.getSelectionModel().getSelectedItem().equals("Menu")) {
            ViewMisc.showStage("/view/menuView/doctorMenuView.fxml");
        }
    }

    @FXML private void editEmailButtonAction(ActionEvent event) throws IOException {
        NodeMisc.enableNode(emailTextField);
        loggedDoctor.setEmail(emailTextField.getText());
        UserMisc.updateUsers(loggedDoctor);
    }

    @FXML void editUsernameButtonAction(ActionEvent event) {
        NodeMisc.enableNode(usernameTextField);
    }

    @FXML void editAddressButtonAction(ActionEvent event) {
        NodeMisc.enableNode(addressTextField);
    }

    @FXML void updateAccDetailsButtonAction(ActionEvent actionEvent) throws IOException {
        loggedDoctor.setEmail(emailTextField.getText());
        loggedDoctor.setUsername(usernameTextField.getText());
        loggedDoctor.setAddress(addressTextField.getText());
        NodeMisc.disableNode(emailTextField, usernameTextField, addressTextField);
        UserMisc.updateUsers(loggedDoctor);
    }

    @FXML void changePasswordButtonAction(ActionEvent event) throws IOException {
        String oldPW = oldPasswordField.getText();
        String newPW = newPasswordField.getText();
        String confirmNewPW = confirmPasswordField.getText();
        if (!BCrypt.checkpw(oldPW, loggedDoctor.getPassword())) {
            NodeMisc.showNode(badOldPassword);
        }
        if (!newPW.equals(confirmNewPW)) {
            NodeMisc.showNode(badNewPassword);
        }
        if (BCrypt.checkpw(oldPW, loggedDoctor.getPassword())) {
            NodeMisc.hideNode(badOldPassword);
        }
        if (newPW.equals(confirmNewPW)) {
            NodeMisc.hideNode(badNewPassword);
        }
        if (BCrypt.checkpw(oldPW, loggedDoctor.getPassword()) && newPW.equals(confirmNewPW)) {
            loggedDoctor.setPassword(BCrypt.hashpw(newPW, BCrypt.gensalt(12)));
            UserMisc.updateUsers(loggedDoctor);
            oldPasswordField.clear();
            newPasswordField.clear();
            confirmPasswordField.clear();
            NodeMisc.hideNode(badOldPassword, badNewPassword);
            ViewMisc.showStage("/view/menuView/loginView.fxml");
            UserMisc.setLoggedUser(null);
        }
    }

    @FXML void forgotPasswordHLAction(ActionEvent event) {

    }

    private void configureDoctorSettingsCB() {
        ObservableList<String> doctorOptions = FXCollections.observableArrayList("Log out", "Menu");
        doctorOptionsComboBox.setPromptText(loggedDoctor.getSurname() + " " + loggedDoctor.getForename());
        doctorOptionsComboBox.setItems(doctorOptions);
    }

    private void configureAccountTab() {
        emailTextField.setText(loggedDoctor.getEmail());
        usernameTextField.setText(loggedDoctor.getUsername());
        roleTextField.setText(loggedDoctor.getRole()+ " - "+loggedDoctor.getSpecialty());
        addressTextField.setText(loggedDoctor.getAddress());
    }



}
