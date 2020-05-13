package controllers.menuController;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import misc.users.DoctorMisc;
import misc.users.UserMisc;
import model.other.PatientProblem;
import model.roles.Patient;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class DoctorViewC implements Initializable {

    @FXML Label writeDownTreatment;
    @FXML TextField addressTextField;
    @FXML private TextArea treatmentTextArea;
    @FXML private ComboBox doctorOptionsComboBox;
    @FXML private Button giveTreatmentButton;
    @FXML private Button appointmentNeededButton;
    @FXML private TableView<Patient> patientsTableView;
    @FXML private TableColumn<Patient, String> surnameColumn;
    @FXML private TableColumn<Patient, String> forenameColumn;
    @FXML private AnchorPane patientDetailsAnchorPane;
    @FXML private Label patientName;
    @FXML private TextArea problemTextArea;
    @FXML private TextArea allergiesTextArea;
    @FXML private TextArea chronicConditionsTextArea;
    @FXML private Label patientAgeLabel;

    private ObservableList<Patient> patients = FXCollections.observableList(DoctorMisc.getPatientsForLoggedDoctor());
    private ObservableList<String> doctorOptions = FXCollections.observableArrayList("Log out", "Change address");

    public DoctorViewC() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        surnameColumn.setCellValueFactory(data -> data.getValue().surnameProperty());
        forenameColumn.setCellValueFactory(data -> data.getValue().forenameProperty());
        patientsTableView.setItems(patients);

        patientSelected();

        doctorOptionsComboBox.setPromptText(UserMisc.getLoggedUser().getSurname() + " " + UserMisc.getLoggedUser().getForename());
        doctorOptionsComboBox.setItems(doctorOptions);

        hideNodes();
        giveTreatmentButton.setDisable(true);
    }

    @FXML void doctorOptionsComboBoxAction(ActionEvent actionEvent) {
        if (doctorOptionsComboBox.getSelectionModel().getSelectedItem().equals("Log out")) {
            UserMisc.logOutUser();
        }
        if (doctorOptionsComboBox.getSelectionModel().getSelectedItem().equals("Change address")) {
            addressTextField.setVisible(true);
            changeAddressOnEnter();
            doctorOptionsComboBox.getSelectionModel().clearSelection();
        }
    }

    @FXML void appointmentNeededAction(ActionEvent event) throws IOException {
        Patient patient = patientsTableView.getSelectionModel().getSelectedItem();
        PatientProblem patientProblem = patient.returnSpecificProblem();
        patientProblem.setTreatment("Appointment needed!");
        patients.remove(patientsTableView.getSelectionModel().getSelectedItem());
        UserMisc.writeUsers("users.json");
    }

    @FXML void giveTreatmentAction(ActionEvent event) throws IOException {
        Patient patient = patientsTableView.getSelectionModel().getSelectedItem();
        PatientProblem patientProblem = patient.returnSpecificProblem();
        patientProblem.setTreatment(treatmentTextArea.getText());
        patients.remove(patientsTableView.getSelectionModel().getSelectedItem());
        UserMisc.writeUsers("users.json");
    }

    @FXML void keyReleaseProperty(KeyEvent keyEvent) {
        BooleanBinding booleanBinding = treatmentTextArea.textProperty().isEmpty();
        giveTreatmentButton.disableProperty().bind(booleanBinding);
    }

    private void hideNodes() {
        giveTreatmentButton.setVisible(false);
        appointmentNeededButton.setVisible(false);
        treatmentTextArea.setVisible(false);
        writeDownTreatment.setVisible(false);
        addressTextField.setVisible(false);
        patientDetailsAnchorPane.setVisible(false);
    }

    private void clearNodes() {

    }

    private void setPatientDetails() {
        Patient patient = patientsTableView.getSelectionModel().getSelectedItem();
        PatientProblem patientProblem = patient.returnSpecificProblem();
        patientName.setText(patient.getSurname() + " " + patient.getForename());
        patientAgeLabel.setText("AGE: " + Period.between(patient.getBirthday(), LocalDate.now()).getYears());
        problemTextArea.setText(patientProblem.getDescriptionOfProblem());
        if (patient.returnSpecificProblem().getHasAllergies().equals("no")) {
            allergiesTextArea.setText("DOESN'T HAVE");
        }
        if (patientProblem.getHasAllergies().equals("unknown")) {
            allergiesTextArea.setText("DOESN'T KNOW");
        }
        if (patientProblem.getHasAllergies().equals("yes")) {
            allergiesTextArea.setText(patientProblem.getAllergies().toString());
        }
        if (patientProblem.getHasChronicConditions().equals("no")) {
            chronicConditionsTextArea.setText("DOESN'T HAVE");
        }
        if (patientProblem.getHasChronicConditions().equals("unknown")) {
            chronicConditionsTextArea.setText("DOESN'T KNOW");
        }
        if (patientProblem.getHasChronicConditions().equals("yes")) {
            chronicConditionsTextArea.setText(patientProblem.getChronicConditions().toString());
        }
    }

    private void showPatientDetails() {
        setPatientDetails();
        giveTreatmentButton.setVisible(true);
        appointmentNeededButton.setVisible(true);
        treatmentTextArea.setVisible(true);
        writeDownTreatment.setVisible(true);
        patientDetailsAnchorPane.setVisible(true);
    }

    private void patientSelected() {
        patientsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Patient>() {
            @Override
            public void changed(ObservableValue<? extends Patient> observableValue, Patient patient, Patient t1) {
                showPatientDetails();
            }
        });
    }

    private void changeAddressOnEnter() {
        addressTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                try {
                    DoctorMisc.updateLoggedDoctorAddress(addressTextField.getText());
                    addressTextField.clear();
                    addressTextField.setVisible(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}

