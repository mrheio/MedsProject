package controllers.account.menus;

import controllers.other.RequestHelpC;
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
import misc.utility.TextMisc;
import misc.utility.ViewMisc;
import model.other.PatientProblem;
import model.roles.Doctor;
import model.roles.Patient;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.Function;

public class PatientMenuC extends MenuC implements Initializable {

    @FXML private TextField doctorFilterTextField;
    @FXML private TableView<Doctor> doctorsTableView;
        @FXML private TableColumn<Doctor, String> nameColumn;
        @FXML private TableColumn<Doctor, String> specialtyColumn;
    @FXML private TableView<PatientProblem> problemsTableView;
        @FXML private TableColumn<PatientProblem, String> typeColumn;
        @FXML private TableColumn<PatientProblem, String> descriptionColumn;
        @FXML private TableColumn<PatientProblem, String> treatmentColumn;
        @FXML private TableColumn<PatientProblem, String> doctorColumn;
    @FXML private TableView<PatientProblem> historyTableView;
        @FXML private TableColumn<PatientProblem, String> hTypeColumn;
        @FXML private TableColumn<PatientProblem, String> hDescriptionColumn;
        @FXML private TableColumn<PatientProblem, String> hTreatmentColumn;
        @FXML private TableColumn<PatientProblem, String> hDoctorColumn;

    private Patient loggedUser = (Patient) super.loggedUser;
    private ObservableList<PatientProblem> patientProblems = FXCollections.observableList(loggedUser.getProblems());
    private ObservableList<PatientProblem> unsolvedProblems = FXCollections.observableList(loggedUser.getUnsolvedProblems());
    private ObservableList<PatientProblem> solvedProblems = FXCollections.observableList(loggedUser.getSolvedProblems());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            configureMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configureDoctorsTable() throws IOException {
        ObservableList<Doctor> doctors = FXCollections.observableList(DoctorMisc.getDoctorsFromFile());
        doctorsTableView.setItems(doctors);
        nameColumn.setCellValueFactory(x -> x.getValue().fullNameProperty());
        specialtyColumn.setCellValueFactory(x -> x.getValue().specialtyProperty());

        Function<Doctor, String> stringFunction = doctor -> doctor.getSpecialty();
        NodeMisc.sortTableViewAfterColumn(doctorsTableView, specialtyColumn);
        NodeMisc.filterTableViewWithTextField(doctorsTableView, doctors, doctorFilterTextField, stringFunction);
        NodeMisc.deselectTableView(doctorsTableView);
    }

    protected void configureProblemsAP() {
        problemsTableView.setItems(unsolvedProblems);
        typeColumn.setCellValueFactory(x -> x.getValue().typeOfProblemProperty());
        descriptionColumn.setCellValueFactory(x -> x.getValue().descriptionOfProblemProperty());
        treatmentColumn.setCellValueFactory(x -> x.getValue().treatmentProperty());
        doctorColumn.setCellValueFactory(x -> x.getValue().doctorNameProperty());
    }

    protected void configureHistoryAP() {
        historyTableView.setItems(solvedProblems);
        hTypeColumn.setCellValueFactory(x -> x.getValue().typeOfProblemProperty());
        hDescriptionColumn.setCellValueFactory(x -> x.getValue().descriptionOfProblemProperty());
        hTreatmentColumn.setCellValueFactory(x -> x.getValue().treatmentProperty());
        hDoctorColumn.setCellValueFactory(x -> x.getValue().doctorNameProperty());
    }

    @Override
    protected void configureMenu() throws IOException {
        super.configureMenu();
        configureDoctorsTable();
        configureProblemsAP();
        configureHistoryAP();
    }

    @FXML void requestHelpButtonAction(ActionEvent actionEvent) {
        RequestHelpC.setPatientProblem(new PatientProblem());
        ViewMisc.showRequestHelp();
    }

    @FXML void markAsSolvedButtonAction(ActionEvent actionEvent) throws IOException {
        PatientProblem patientProblem = problemsTableView.getSelectionModel().getSelectedItem();
        try {
            patientProblem.setSolved(true);
            unsolvedProblems.remove(patientProblem);
            solvedProblems.add(patientProblem);
            UserMisc.updateUsers(loggedUser);
        } catch (NullPointerException e) {
            System.out.println("nothing selected");
        }
    }

    @FXML void requestAnotherTreatmentButtonAction(ActionEvent actionEvent) throws IOException {
        PatientProblem patientProblem = problemsTableView.getSelectionModel().getSelectedItem();
        try {
            patientProblem.setTreatment(null);
            patientProblem.setDoctorName("-");
            UserMisc.updateUsers(loggedUser);
        } catch (NullPointerException e) {
            System.out.println("nothing selected");
        }
    }

}
