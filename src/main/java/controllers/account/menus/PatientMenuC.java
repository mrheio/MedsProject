package controllers.account.menus;

import controllers.other.RequestHelpC;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import misc.user.DoctorMisc;
import misc.user.PatientMisc;
import misc.user.UserMisc;
import misc.utility.NodeMisc;
import misc.utility.ViewMisc;
import model.other.PatientProblem;
import model.roles.Doctor;
import model.roles.Patient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

public class PatientMenuC extends MenuC implements Initializable {


    @FXML private AnchorPane treatmentAP;
    @FXML private ComboBox optionsCB;
    @FXML private TextField doctorFilterTextField;
    @FXML private TextArea treatmentTA;
    @FXML private TableView<Doctor> doctorsTableView;
        @FXML private TableColumn<Doctor, String> surnameColumn;
        @FXML private TableColumn<Doctor, String> forenameColumn;
        @FXML private TableColumn<Doctor, String> specialtyColumn;
    @FXML private TableView<PatientProblem> problemsTableView;
        @FXML private TableColumn<PatientProblem, String> typeOfProblemTableColumn;
        @FXML private TableColumn<PatientProblem, String> problemTableColumn;

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
        ViewMisc.showStage("/view/other/requestHelpView.fxml");
    }

    @FXML void patientOptionsComboBoxAction(ActionEvent actionEvent) {
        Object selectedOption = optionsCB.getSelectionModel().getSelectedItem();
        if (selectedOption.equals("Log out")) {
            UserMisc.logOutUser();
        }
        if (selectedOption.equals("Edit profile")) {
            ViewMisc.showStage("/view/account/settings/patientAccSettingsView.fxml");
        }
    }

    @FXML void deleteProblemButtonAction(ActionEvent actionEvent) throws IOException {
        PatientProblem patientProblem = problemsTableView.getSelectionModel().getSelectedItem();
        patientProblems.remove(patientProblem);
        PatientMisc.deleteLoggedPatientProblem(patientProblem);

    }

    private void configurePatientMenuCB() {
        optionsCB.setPromptText(loggedPatient.getForename());
        optionsCB.setItems(super.options);
    }

    private void configureDoctorsTable() throws IOException {
        ObservableList<Doctor> doctors = FXCollections.observableList(DoctorMisc.getDoctorsFromFile());
        doctorsTableView.setItems(doctors);
        surnameColumn.setCellValueFactory(x -> x.getValue().surnameProperty());
        forenameColumn.setCellValueFactory(x -> x.getValue().forenameProperty());
        specialtyColumn.setCellValueFactory(x -> x.getValue().specialtyProperty());

       Function<Doctor, String> stringFunction = doctor -> doctor.getSpecialty();
       NodeMisc.sortTableViewAfterColumn(doctorsTableView, specialtyColumn);
       NodeMisc.filterTableViewWithTextField(doctorsTableView, doctors, doctorFilterTextField, stringFunction);
       NodeMisc.deselectTableView(doctorsTableView);
    }

    private void configureProblemsTable() {
        problemsTableView.setItems(patientProblems);
        typeOfProblemTableColumn.setCellValueFactory(x -> x.getValue().typeOfProblemProperty());
        problemTableColumn.setCellValueFactory(x -> x.getValue().descriptionOfProblemProperty());
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
        if (selectedProblem != null) {
            treatmentTA.setText(selectedProblem.getTreatment());
            NodeMisc.showNode(treatmentAP);
        }
        if (selectedProblem == null) {
            treatmentTA.setText(null);
            NodeMisc.hideNode(treatmentAP);
        }
    }

}
