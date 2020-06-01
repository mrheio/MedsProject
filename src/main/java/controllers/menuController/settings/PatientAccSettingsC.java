package controllers.menuController.settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import misc.users.UserMisc;
import misc.utility.BCrypt;
import misc.utility.NodeMisc;
import misc.utility.ViewMisc;
import model.roles.Patient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientAccSettingsC implements Initializable {

    @FXML private TextField emailTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField roleTextField;
    @FXML private Hyperlink forgotPasswordHL;
    @FXML private PasswordField oldPasswordField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label badOldPassword;
    @FXML private Label badNewPassword;
    @FXML private ComboBox patientOptionsComboBox;

    private Patient loggedPatient = (Patient) UserMisc.getLoggedUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurePatientSettingsCB();
        configureAccountTab();
    }

    @FXML void patientOptionsComboBoxAction(ActionEvent actionEvent) {
        if (patientOptionsComboBox.getSelectionModel().getSelectedItem().equals("Log out")) {
            UserMisc.logOutUser();
        }
        if (patientOptionsComboBox.getSelectionModel().getSelectedItem().equals("Menu")) {
            ViewMisc.showStage("/view/menuView/patientMenuView.fxml");
        }
    }

    @FXML private void editEmailButtonAction(ActionEvent event) throws IOException {
        NodeMisc.enableNode(emailTextField);
        loggedPatient.setEmail(emailTextField.getText());
        UserMisc.updateUsers(loggedPatient);
    }

    @FXML void editUsernameButtonAction(ActionEvent event) {
        NodeMisc.enableNode(usernameTextField);
    }

    @FXML void updateAccDetailsButtonAction(ActionEvent actionEvent) throws IOException {
        loggedPatient.setEmail(emailTextField.getText());
        loggedPatient.setUsername(usernameTextField.getText());
        NodeMisc.disableNode(emailTextField, usernameTextField);
        UserMisc.updateUsers(loggedPatient);
    }

    @FXML void changePasswordButtonAction(ActionEvent event) throws IOException {
        String oldPW = oldPasswordField.getText();
        String newPW = newPasswordField.getText();
        String confirmNewPW = confirmPasswordField.getText();
        if (!BCrypt.checkpw(oldPW, loggedPatient.getPassword())) {
            NodeMisc.showNode(badOldPassword);
        }
        if (!newPW.equals(confirmNewPW)) {
            NodeMisc.showNode(badNewPassword);
        }
        if (BCrypt.checkpw(oldPW, loggedPatient.getPassword())) {
            NodeMisc.hideNode(badOldPassword);
        }
        if (newPW.equals(confirmNewPW)) {
            NodeMisc.hideNode(badNewPassword);
        }
        if (BCrypt.checkpw(oldPW, loggedPatient.getPassword()) && newPW.equals(confirmNewPW)) {
            loggedPatient.setPassword(BCrypt.hashpw(newPW, BCrypt.gensalt(12)));
            UserMisc.updateUsers(loggedPatient);
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

    private void configurePatientSettingsCB() {
        ObservableList<String> patientOptions = FXCollections.observableArrayList("Log out", "Menu");
        patientOptionsComboBox.setPromptText(loggedPatient.getSurname() + " " + loggedPatient.getForename());
        patientOptionsComboBox.setItems(patientOptions);
    }

    private void configureAccountTab() {
        emailTextField.setText(loggedPatient.getEmail());
        usernameTextField.setText(loggedPatient.getUsername());
        roleTextField.setText(loggedPatient.getRole());
    }

}
