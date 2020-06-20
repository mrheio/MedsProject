package controllers.account.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import misc.user.UserMisc;
import misc.utility.NodeMisc;
import misc.utility.ViewMisc;
import model.roles.Patient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientAccSettingsC extends AccSettingsC implements Initializable {

    private Patient loggedUser = (Patient) super.loggedUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureCB();
        configureAccountTab();
    }

    @Override
    void updateAccDetailsButtonAction(ActionEvent actionEvent) throws IOException {
        super.updateAccDetailsButtonAction(actionEvent);
        NodeMisc.disableNode(emailTextField, usernameTextField);
        UserMisc.updateUsers(loggedUser);
    }
}
