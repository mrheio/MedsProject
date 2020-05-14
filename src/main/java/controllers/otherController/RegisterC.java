package controllers.otherController;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import misc.utility.BCrypt;
import misc.users.UserMisc;
import misc.utility.NodeMisc;
import misc.utility.ViewMisc;
import model.date.Date;
import model.date.month;
import model.other.ProblemTypes;
import model.roles.Doctor;
import model.roles.Patient;
import model.roles.Person;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegisterC implements Initializable{

    @FXML TextField addressTextField;
    @FXML private ScrollPane registerScrollPane;
    @FXML private TextField surname;
    @FXML private TextField forename;
    @FXML private ComboBox<Integer> dayComboBox;
    @FXML private ComboBox<month> monthComboBox;
    @FXML private ComboBox<Integer> yearComboBox;
    @FXML private TextField email;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private ComboBox<String> roleComboBox;
    @FXML private ComboBox doctorSpecialtyComboBox;
    @FXML private Button createAccountButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        yearComboBox.setItems(Date.generateYears(1950));
        monthComboBox.setItems(Date.generateMonths());
        monthComboBox.setConverter(new StringConverter<month>() {
            @Override
            public String toString(month month) {
                return month.getMonthName();
            }

            @Override
            public month fromString(String s) {
                return monthComboBox.getValue();
            }
        });
        dayComboBox.setItems(Date.generateDays(31));

        roleComboBox.setItems(FXCollections.observableArrayList("Patient", "Doctor"));

        doctorSpecialtyComboBox.setItems(ProblemTypes.getPhysicalProblems());
        NodeMisc.hideNode(doctorSpecialtyComboBox, addressTextField);
    }

    @FXML void roleComboBoxAction(ActionEvent actionEvent) {
        if (roleComboBox.getSelectionModel().getSelectedItem().equals("Patient")) {
            NodeMisc.hideNode(doctorSpecialtyComboBox, addressTextField);
        }
        if (roleComboBox.getSelectionModel().getSelectedItem().equals("Doctor")) {
            NodeMisc.showNode(doctorSpecialtyComboBox, addressTextField);
        }
    }

    @FXML String doctorSpecialtyComboBoxAction() {
        String specialty = doctorSpecialtyComboBox.getSelectionModel().getSelectedItem().toString();
        return specialty;
    }

    @FXML void createAccountButtonAction(ActionEvent actionEvent) throws IOException {
        UserMisc.readUsers("users.json");
        Person person = null;
        if (passwordField.getText().equals(confirmPasswordField.getText())) {
            if (roleComboBox.getSelectionModel().getSelectedItem().equals("Patient")) {
                person = new Patient(surname.getText(), forename.getText(), LocalDate.of(yearComboBox.getSelectionModel().getSelectedItem(), monthComboBox.getSelectionModel().getSelectedItem().getId(), dayComboBox.getSelectionModel().getSelectedItem()), email.getText(), usernameTextField.getText(), BCrypt.hashpw(passwordField.getText(), BCrypt.gensalt(12)), roleComboBox.getSelectionModel().getSelectedItem());
            }
            if (roleComboBox.getSelectionModel().getSelectedItem().equals("Doctor")) {
                person = new Doctor(surname.getText(), forename.getText(), LocalDate.of(yearComboBox.getSelectionModel().getSelectedItem(), monthComboBox.getSelectionModel().getSelectedItem().getId(), dayComboBox.getSelectionModel().getSelectedItem()), email.getText(), usernameTextField.getText(), BCrypt.hashpw(passwordField.getText(), BCrypt.gensalt(12)), roleComboBox.getSelectionModel().getSelectedItem(), doctorSpecialtyComboBoxAction(), addressTextField.getText());
            }
            UserMisc.addUser(person);
            UserMisc.writeUsers("users.json");
            ViewMisc.showStage("/view/menuView/loginView.fxml");
        } else {
            passwordField.clear();
            confirmPasswordField.clear();
        }
    }

    @FXML void cancelButtonAction(ActionEvent actionEvent) {
        ViewMisc.showStage("/view/menuView/loginView.fxml");
    }

}
