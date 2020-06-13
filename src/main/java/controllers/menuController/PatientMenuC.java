package controllers.menuController;

import controllers.otherController.RequestHelpC;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import misc.users.DoctorMisc;
import misc.users.PatientMisc;
import misc.users.UserMisc;
import misc.utility.NodeMisc;
import misc.utility.ViewMisc;
import model.other.PatientProblem;
import model.roles.Doctor;
import model.roles.Patient;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

public class PatientMenuC implements Initializable {


    @FXML private AnchorPane treatmentAP;
    @FXML private TableView<PatientProblem> problemsTableView;
        @FXML private TableColumn<PatientProblem, String> typeOfProblemTableColumn;
        @FXML private TableColumn<PatientProblem, String> problemTableColumn;
    @FXML private ComboBox patientOptionsComboBox;
    @FXML private TextField doctorFilterTextField;
    @FXML private TextArea treatmentTA;
    @FXML private TableView<Doctor> doctorsTableView;
        @FXML private TableColumn<Doctor, String> surnameColumn;
        @FXML private TableColumn<Doctor, String> forenameColumn;
        @FXML private TableColumn<Doctor, String> specialtyColumn;

    private Patient loggedPatient = (Patient) UserMisc.getLoggedUser();
    private ObservableList<PatientProblem> patientProblems = FXCollections.observableList(((Patient) UserMisc.getLoggedUser()).getProblems());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            configureMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML void requestHelpButtonAction(ActionEvent actionEvent) {
        RequestHelpC.setPatientProblem(new PatientProblem());
        ViewMisc.showStage("/view/otherView/requestHelpView.fxml");
    }

    @FXML void patientOptionsComboBoxAction(ActionEvent actionEvent) {
        if (patientOptionsComboBox.getSelectionModel().getSelectedItem().equals("Log out")) {
            UserMisc.logOutUser();
        }
        if (patientOptionsComboBox.getSelectionModel().getSelectedItem().equals("Edit profile")) {
            ViewMisc.showStage("/view/menuView/settingsView/patientAccSettingsView.fxml");
        }
    }

    @FXML void deleteProblemButtonAction(ActionEvent actionEvent) throws IOException {
        PatientProblem patientProblem = problemsTableView.getSelectionModel().getSelectedItem();
        patientProblems.remove(patientProblem);
        PatientMisc.deleteLoggedPatientProblem(patientProblem);

    }

    private void configurePatientMenuCB() {
        ObservableList<String> patientOptions = FXCollections.observableArrayList("Log out", "Edit profile");
        patientOptionsComboBox.setPromptText(loggedPatient.getForename());
        patientOptionsComboBox.setItems(patientOptions);
    }

    private void configureDoctorsTable() throws IOException {
        ObservableList<Doctor> doctors = FXCollections.observableList(DoctorMisc.getDoctorsFromFile());
        NodeMisc.deselectTableView(doctorsTableView);
        doctorsTableView.setItems(doctors);
        surnameColumn.setCellValueFactory(doctorStringCellDataFeatures -> doctorStringCellDataFeatures.getValue().surnameProperty());
        forenameColumn.setCellValueFactory(doctorStringCellDataFeatures -> doctorStringCellDataFeatures.getValue().forenameProperty());
        specialtyColumn.setCellValueFactory(doctorStringCellDataFeatures -> doctorStringCellDataFeatures.getValue().specialtyProperty());

       Function<Doctor, String> stringFunction = doctor -> doctor.getSpecialty();
       NodeMisc.sortTableViewAfterColumn(doctorsTableView, specialtyColumn);
       NodeMisc.filterTableViewWithTextField(doctorsTableView, doctors, doctorFilterTextField, stringFunction);
    }

    private void configureProblemsTable() {
        problemsTableView.setItems(patientProblems);
        typeOfProblemTableColumn.setCellValueFactory(patientProblemStringCellDataFeatures -> patientProblemStringCellDataFeatures.getValue().typeOfProblemProperty());
        problemTableColumn.setCellValueFactory(patientProblemStringCellDataFeatures -> patientProblemStringCellDataFeatures.getValue().descriptionOfProblemProperty());
        showTreatmentForSelectedProblem();
    }

    private void configureTables() throws IOException {
        configureDoctorsTable();
        configureProblemsTable();
    }

    private void configureMenu() throws IOException {
        configurePatientMenuCB();
        configureTables();
        NodeMisc.hideNode(treatmentAP);
    }

    private void showTreatmentForSelectedProblem() {
        problemsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PatientProblem>() {
            @Override
            public void changed(ObservableValue<? extends PatientProblem> observableValue, PatientProblem patientProblem, PatientProblem t1) {
                showTreatment();
            }
        });
    }

    private void showTreatment() {
        PatientProblem selectedProblem = problemsTableView.getSelectionModel().getSelectedItem();
        if (selectedProblem == null) {
            treatmentTA.setText(null);
            NodeMisc.hideNode(treatmentAP);
        }
        if (selectedProblem != null) {
            treatmentTA.setText(selectedProblem.getTreatment());
            NodeMisc.showNode(treatmentAP);
        }
    }

}
