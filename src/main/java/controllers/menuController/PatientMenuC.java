package controllers.menuController;

import controllers.otherController.RequestHelpC;
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
import java.util.ResourceBundle;

public class PatientMenuC implements Initializable {


    @FXML private AnchorPane doctorDetailsAnchorPane;
    @FXML private Button deleteProblemButton;
    @FXML private TableView<PatientProblem> problemsTableView;
        @FXML private TableColumn<PatientProblem, String> typeOfProblemTableColumn;
        @FXML private TableColumn<PatientProblem, String> problemTableColumn;
        @FXML private TableColumn<PatientProblem, String> treatmentTableColumn;
    @FXML private ComboBox patientOptionsComboBox;
    @FXML private TextField doctorFilterTextField;
    @FXML private TableView<Doctor> doctorsTableView;
        @FXML private TableColumn<Doctor, String> surnameColumn;
        @FXML private TableColumn<Doctor, String> forenameColumn;
        @FXML private TableColumn<Doctor, String> specialtyColumn;
    @FXML private Label doctorName;

    private ObservableList<Doctor> doctors = FXCollections.observableList(DoctorMisc.getDoctorsFromFile());
    private ObservableList<String> patientOptions = FXCollections.observableArrayList("Log out");
    private ObservableList<PatientProblem> patientProblems = FXCollections.observableList(((Patient) UserMisc.getLoggedUser()).getProblems());

    public PatientMenuC() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTables();

        patientOptionsComboBox.setPromptText(UserMisc.getLoggedUser().getSurname() + " " + UserMisc.getLoggedUser().getForename());
        patientOptionsComboBox.setItems(patientOptions);

        NodeMisc.hideNode(doctorName);
    }

    @FXML void requestHelpButtonAction(ActionEvent actionEvent) {
        RequestHelpC.setPatientProblem(new PatientProblem());
        ViewMisc.showStage("/view/otherView/requestHelpView.fxml");
    }

    @FXML void patientOptionsComboBoxAction(ActionEvent actionEvent) {
        if (patientOptionsComboBox.getSelectionModel().getSelectedItem().equals("Log out")) {
            UserMisc.logOutUser();
        }
    }

    @FXML void deleteProblemButtonAction(ActionEvent actionEvent) throws IOException {
        PatientProblem patientProblem = problemsTableView.getSelectionModel().getSelectedItem();
        patientProblems.remove(patientProblem);
        PatientMisc.deleteLoggedPatientProblem(patientProblem);

    }

    private void filterTableView(ObservableList list, TextField filterTextField, TableView table) {
        FilteredList<Doctor> filteredList = new FilteredList<>(list, p -> true);
        doctorFilterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(model -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String text = newValue.toLowerCase();
                if (model.getSpecialty().toLowerCase().indexOf(text) != -1) {
                    return true;
                }
                return false;
            });
            SortedList<Doctor> sortedList = new SortedList<Doctor>(filteredList);
            sortedList.comparatorProperty().bind(doctorsTableView.comparatorProperty());
            table.setItems(sortedList);
        });
    }

    private void initializeTables() {
        doctorsTableView.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (!newVal) {
                doctorsTableView.getSelectionModel().clearSelection();
            }
        });
        doctorsTableView.setItems(doctors);
        surnameColumn.setCellValueFactory(doctorStringCellDataFeatures -> doctorStringCellDataFeatures.getValue().surnameProperty());
        forenameColumn.setCellValueFactory(doctorStringCellDataFeatures -> doctorStringCellDataFeatures.getValue().forenameProperty());
        specialtyColumn.setCellValueFactory(doctorStringCellDataFeatures -> doctorStringCellDataFeatures.getValue().specialtyProperty());
        doctorsTableView.sort();
        doctorsTableView.getSortOrder().add(specialtyColumn);

        filterTableView(doctors, doctorFilterTextField, doctorsTableView);

        doctorSelected();

        problemsTableView.setItems(patientProblems);
        typeOfProblemTableColumn.setCellValueFactory(patientProblemStringCellDataFeatures -> patientProblemStringCellDataFeatures.getValue().typeOfProblemProperty());
        problemTableColumn.setCellValueFactory(patientProblemStringCellDataFeatures -> patientProblemStringCellDataFeatures.getValue().descriptionOfProblemProperty());
        treatmentTableColumn.setCellValueFactory(patientProblemStringCellDataFeatures -> patientProblemStringCellDataFeatures.getValue().treatmentProperty());

    }

    private void doctorSelected() {
        doctorsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Doctor>() {
            @Override
            public void changed(ObservableValue<? extends Doctor> observableValue, Doctor doctor, Doctor t1) {
                showDoctorDetails();
            }
        });
    }

    private void showDoctorDetails() {
        Doctor selectedDoctor = doctorsTableView.getSelectionModel().getSelectedItem();
        if (selectedDoctor != null) {
            doctorName.setText(selectedDoctor.getSurname() + " " + selectedDoctor.getForename());
            NodeMisc.showNode(doctorName);
        }
        if (selectedDoctor == null) {
            doctorName.setText(null);
            NodeMisc.hideNode(doctorName);
        }

    }


}
