package controllers.menuController.menus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

abstract public class MenuC {

    protected ObservableList<String> options = FXCollections.observableArrayList("Log out", "Edit profile");

}
