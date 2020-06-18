package controllers.account.menus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

abstract public class MenuC {

    protected ObservableList<String> options = FXCollections.observableArrayList("Log out", "Edit profile");

}
