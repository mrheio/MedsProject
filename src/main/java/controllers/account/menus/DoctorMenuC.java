package controllers.account.menus;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
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
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.ResourceBundle;

public class DoctorMenuC extends MenuC implements Initializable {

    @FXML private Button giveTreatmentButton;
    @FXML private Label nameLabel;
    @FXML private Label ageLabel;
    @FXML private TextArea descriptionTA;
    @FXML private TextArea allergiesTA;
    @FXML private TextArea ccTA;
    @FXML private TextArea giveTreatmentTA;
    @FXML private ListView<PatientProblem> problemsListView;
    @FXML private TableView<PatientProblem> historyTableView;
        @FXML private TableColumn<PatientProblem, String> problemColumn;

    private Doctor loggedUser = (Doctor) super.loggedUser;
    private ObservableList<PatientProblem> problems = FXCollections.observableList(loggedUser.getProblemsForDoctor(PatientMisc.getPatientsFromUsers()));
    private ObservableList<PatientProblem> noTreatmentUnsolvedProblems = FXCollections.observableList(loggedUser.getNoTreatmentUnsolvedProblemsForDoctor(PatientMisc.getPatientsFromUsers()));
    private ObservableList<PatientProblem> solvedProblems = FXCollections.observableList(loggedUser.getSolvedProblems());

    public DoctorMenuC() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            configureMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NodeMisc.hideDisableNode(Arrays.asList(problemsAP), Arrays.asList(giveTreatmentButton));

    }

    protected void configureProblemsAP() {
        problemsListView.setItems(noTreatmentUnsolvedProblems);
        if (problemsListView.getSelectionModel().getSelectedItem() == null) {
            NodeMisc.hideNode(problemsAP);
        }
        showSelectedPatient();
    }

    protected void configureHistoryAP() {
        historyTableView.setItems(solvedProblems);
        problemColumn.setCellValueFactory(x -> x.getValue().descriptionOfProblemProperty());
    }

    @Override
    protected void configureMenu() throws IOException {
        super.configureMenu();
        configureProblemsAP();
        configureHistoryAP();
    }

    private void showPatientDetails() throws IOException {
        PatientProblem problem = problemsListView.getSelectionModel().getSelectedItem();
        if (problem != null) {
            NodeMisc.showNode(problemsAP);
            Patient patient = PatientMisc.getProblemSource(problem);
            nameLabel.setText(patient.getFullName());
            ageLabel.setText("AGE: "+ UserMisc.getUserYears(patient));
            descriptionTA.setText(problem.getDescriptionOfProblem());
            if (problem.getHasAllergies().equals("yes")) {
                allergiesTA.setText(problem.getHasAllergies() + ": " + problem.getAllergies().toString());
            } else {
                allergiesTA.setText(problem.getHasAllergies());
            }
            if (problem.getHasAllergies().equals("yes")) {
                ccTA.setText(problem.getHasChronicConditions() + ": " + problem.getChronicConditions().toString());
            } else {
                ccTA.setText(problem.getHasChronicConditions());
            }
        }
        if (problem == null) {
            NodeMisc.clearLabels(nameLabel, ageLabel);
            NodeMisc.clearTextFieldsAndAreas(descriptionTA, allergiesTA, ccTA);
            NodeMisc.hideNode(problemsAP);
        }
    }

    private void showSelectedPatient() {
        problemsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PatientProblem>() {
            @Override
            public void changed(ObservableValue<? extends PatientProblem> observableValue, PatientProblem patientProblem, PatientProblem t1) {
                try {
                    giveTreatmentTA.clear();
                    showPatientDetails();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    System.out.println("nothing selected");
                }
            }
        });
    }

    private void setPatientTreatment(String treatment) throws IOException {
        PatientProblem problem = problemsListView.getSelectionModel().getSelectedItem();
        Patient patient = PatientMisc.getProblemSource(problem);
        problem.setTreatment(treatment);
        problem.setDoctorName(loggedUser.getFullName());
        noTreatmentUnsolvedProblems.remove(problem);
        solvedProblems.add(problem);
        UserMisc.updateUsers(loggedUser, patient);
        giveTreatmentTA.clear();
    }

    @Override
    void problemsHLAction(ActionEvent actionEvent) {
        super.problemsHLAction(actionEvent);
        if (problemsListView.getSelectionModel().getSelectedItem() == null) {
            NodeMisc.hideNode(problemsAP);
        }
    }

    @FXML void appointmentNeededButtonAction(ActionEvent event) throws IOException {
        setPatientTreatment("Appointment needed!");
    }

    @FXML void giveTreatmentButtonAction(ActionEvent event) throws IOException {
        setPatientTreatment(giveTreatmentTA.getText());
    }

    @FXML void keyReleaseProperty() {
        BooleanBinding booleanBinding = giveTreatmentTA.textProperty().isEmpty();
        giveTreatmentButton.disableProperty().bind(booleanBinding);
    }

}

