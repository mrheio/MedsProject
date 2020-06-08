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
import model.roles.Patient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientAccSettingsC extends AccSettingsC implements Initializable {

    @FXML private ComboBox patientOptionsComboBox;

    private Patient loggedUser = (Patient) super.loggedUser;

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

    @FXML void forgotPasswordHLAction(ActionEvent event) {

    }

    private void configurePatientSettingsCB() {
        ObservableList<String> patientOptions = FXCollections.observableArrayList("Log out", "Menu");
        patientOptionsComboBox.setPromptText(loggedUser.getForename());
        patientOptionsComboBox.setItems(patientOptions);
    }

    @Override
    void updateAccDetailsButtonAction(ActionEvent actionEvent) throws IOException {
        super.updateAccDetailsButtonAction(actionEvent);
        NodeMisc.disableNode(emailTextField, usernameTextField);
        UserMisc.updateUsers(loggedUser);
    }
}
