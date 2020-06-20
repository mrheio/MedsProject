package controllers.account.menus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import misc.user.UserMisc;
import misc.utility.NodeMisc;
import misc.utility.TextMisc;
import model.combobox.AccSettings;
import model.combobox.LogOut;
import model.combobox.Option;
import model.roles.Person;

import java.io.IOException;

abstract public class MenuC {

    @FXML protected AnchorPane problemsAP;
    @FXML protected AnchorPane historyAP;
    @FXML protected ComboBox<Option> optionsCB;
    @FXML protected Hyperlink problemsHL;
    @FXML protected Hyperlink historyHL;
    protected Person loggedUser = UserMisc.getLoggedUser();
    protected ObservableList<Option> options = FXCollections.observableArrayList(new LogOut(), new AccSettings());

    protected void configureCB() {
        TextMisc.textBind(optionsCB.promptTextProperty(), loggedUser.usernameProperty());
        optionsCB.setItems(options);
    }

    protected void configureMenu() throws IOException {
        configureCB();
    }

    @FXML void optionsCBAction(ActionEvent actionEvent) {
        optionsCB.getSelectionModel().getSelectedItem().action(loggedUser);
    }

    @FXML void problemsHLAction(ActionEvent actionEvent) {
        NodeMisc.hideNode(historyAP);
        NodeMisc.showNode(problemsAP);
        NodeMisc.oneHLVisited(problemsHL, historyHL);
    }

    @FXML void historyHLAction(ActionEvent actionEvent) {
        NodeMisc.hideNode(problemsAP);
        NodeMisc.showNode(historyAP);
        NodeMisc.oneHLVisited(historyHL, problemsHL);
    }
}
