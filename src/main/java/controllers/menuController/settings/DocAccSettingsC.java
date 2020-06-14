package controllers.menuController.settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import misc.users.UserMisc;
import misc.utility.NodeMisc;
import misc.utility.ViewMisc;
import model.roles.Doctor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DocAccSettingsC extends AccSettingsC implements Initializable {

    @FXML private TextField addressTextField;
    @FXML private ComboBox doctorOptionsComboBox;

    private Doctor loggedUser = (Doctor) super.loggedUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureDoctorSettingsCB();
        configureAccountTab();
    }

    @FXML void doctorOptionsComboBoxAction(ActionEvent actionEvent) {
        Object selectedOption = doctorOptionsComboBox.getSelectionModel().getSelectedItem();
        if (selectedOption.equals("Log out")) {
            UserMisc.logOutUser();
        }
        if (selectedOption.equals("Menu")) {
            ViewMisc.showStage("/view/menuView/doctorMenuView.fxml");
        }
    }

    @FXML void editAddressButtonAction(ActionEvent event) {
        NodeMisc.enableNode(addressTextField);
    }

    @FXML void forgotPasswordHLAction(ActionEvent event) {

    }

    private void configureDoctorSettingsCB() {
        doctorOptionsComboBox.setPromptText(loggedUser.getForename());
        doctorOptionsComboBox.setItems(super.options);
    }

    @Override
    void updateAccDetailsButtonAction(ActionEvent actionEvent) throws IOException {
        super.updateAccDetailsButtonAction(actionEvent);
        loggedUser.setAddress(addressTextField.getText());
        NodeMisc.disableNode(emailTextField, usernameTextField, addressTextField);
        UserMisc.updateUsers(loggedUser);
    }

    @Override
    void configureAccountTab() {
        super.configureAccountTab();
        addressTextField.setText(loggedUser.getAddress());
    }

}
