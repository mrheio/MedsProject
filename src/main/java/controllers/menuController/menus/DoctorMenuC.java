package controllers.menuController.menus;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import misc.users.DoctorMisc;
import misc.users.UserMisc;
import misc.utility.NodeMisc;
import misc.utility.ViewMisc;
import model.other.PatientProblem;
import model.roles.Doctor;
import model.roles.Patient;
import model.roles.Person;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class DoctorMenuC extends MenuC implements Initializable {

    @FXML private AnchorPane patientDetailsAP;
    @FXML private ComboBox optionsCB;
    @FXML private Button giveTreatmentButton;
    @FXML private Button appointmentNeededButton;
    @FXML private TextArea treatmentTextArea;
    @FXML private TextArea problemTA;
    @FXML private TextArea allergiesTA;
    @FXML private TextArea ccTA;
    @FXML private Label patientName;
    @FXML private Label patientAgeLabel;
    @FXML private Label writeDownTreatment;
    @FXML private TableView<Patient> patientsTableView;
        @FXML private TableColumn<Patient, String> surnameColumn;
        @FXML private TableColumn<Patient, String> forenameColumn;

    private Doctor loggedDoctor = (Doctor) UserMisc.getLoggedUser();
    private ObservableList<Patient> patients = FXCollections.observableList(DoctorMisc.getPatientsForLoggedDoctor());

    public DoctorMenuC() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureMenu();
    }

    @FXML void doctorOptionsComboBoxAction(ActionEvent actionEvent) {
        Object selectedOption = optionsCB.getSelectionModel().getSelectedItem();
        if (selectedOption.equals("Log out")) {
            UserMisc.logOutUser();
        }
        if (selectedOption.equals("Edit profile")) {
            ViewMisc.showStage("/view/menuView/settingsView/docAccSettingsView.fxml");
        }
    }

    @FXML void appointmentNeededAction(ActionEvent event) throws IOException {
        setPatientTreatment("Appointment needed!");
    }

    @FXML void giveTreatmentAction(ActionEvent event) throws IOException {
        setPatientTreatment(treatmentTextArea.getText());
    }

    @FXML void keyReleaseProperty(KeyEvent keyEvent) {
        BooleanBinding booleanBinding = treatmentTextArea.textProperty().isEmpty();
        giveTreatmentButton.disableProperty().bind(booleanBinding);
    }

    private void configureDoctorOptionsCB() {
        optionsCB.setPromptText(loggedDoctor.getForename());
        optionsCB.setItems(super.options);
    }

    private void configurePatientTable() {
        patientsTableView.setItems(patients);
        surnameColumn.setCellValueFactory(x -> x.getValue().surnameProperty());
        forenameColumn.setCellValueFactory(x -> x.getValue().forenameProperty());
        patientSelected();
    }

    private void configureMenu() {
        configureDoctorOptionsCB();
        configurePatientTable();
        NodeMisc.hideNode(giveTreatmentButton, appointmentNeededButton, treatmentTextArea, writeDownTreatment, patientDetailsAP);
        NodeMisc.disableNode(giveTreatmentButton);
    }

    private void setShowPatientDetails() {
        Patient patient = patientsTableView.getSelectionModel().getSelectedItem();
        if (patient != null) {
            PatientProblem patientProblem = patient.returnSpecificProblem(loggedDoctor.getSpecialty());
            patientName.setText(patient.getSurname() + " " + patient.getForename());
            patientAgeLabel.setText("AGE: " + Period.between(patient.getBirthday(), LocalDate.now()).getYears());
            problemTA.setText(patientProblem.getDescriptionOfProblem());
            String hasAllergies = patientProblem.getHasAllergies();
            String hasCC = patientProblem.getHasChronicConditions();
            if (patientProblem.getHasAllergies().equals("yes")) {
                allergiesTA.setText(patientProblem.getAllergies().toString());
            } else {
                allergiesTA.setText(hasAllergies);
            }
            if (patientProblem.getHasChronicConditions().equals("yes")) {
                ccTA.setText(patientProblem.getChronicConditions().toString());
            } else {
                ccTA.setText(hasCC);
            }
            NodeMisc.showNode(giveTreatmentButton, appointmentNeededButton, treatmentTextArea, writeDownTreatment, patientDetailsAP);

        }
        if (patient == null) {
            NodeMisc.clearLabels(patientName, patientAgeLabel);
            NodeMisc.clearTextFieldsAndAreas(problemTA, allergiesTA, ccTA);
            NodeMisc.hideNode(giveTreatmentButton, appointmentNeededButton, treatmentTextArea, writeDownTreatment, patientDetailsAP);
        }
    }

    private void patientSelected() {
        patientsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Patient>() {
            @Override
            public void changed(ObservableValue<? extends Patient> observableValue, Patient doctor, Patient t1) {
                setShowPatientDetails();
            }
        });
    }

    private void setPatientTreatment(String treatment) throws IOException {
        Patient patient = patientsTableView.getSelectionModel().getSelectedItem();
        PatientProblem patientProblem = patient.returnSpecificProblem(loggedDoctor.getSpecialty());
        patientProblem.setTreatment(treatment);
        patients.remove(patient);
        UserMisc.updateUsers(patient);
    }

}

