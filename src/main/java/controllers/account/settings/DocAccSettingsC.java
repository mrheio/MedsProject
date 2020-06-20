package controllers.account.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import misc.user.UserMisc;
import misc.utility.NodeMisc;
import misc.utility.ViewMisc;
import model.roles.Doctor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DocAccSettingsC extends AccSettingsC implements Initializable {

    @FXML private TextField addressTextField;

    private Doctor loggedUser = (Doctor) super.loggedUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureCB();
        configureAccountTab();
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

    @FXML void editAddressButtonAction(ActionEvent event) {
        NodeMisc.enableNode(addressTextField);
    }

}
