package controllers.otherController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import misc.utility.TextMisc;
import misc.users.PatientMisc;
import misc.utility.ViewMisc;
import model.other.PatientProblem;
import model.other.ProblemTypes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RequestHelpViewC implements Initializable {


    @FXML private Label alreadyProblemLabel;
    @FXML private RadioButton physicalRadioButton;
    @FXML private RadioButton mentalRadioButton;
    @FXML private ToggleGroup typeOfProblem;
    @FXML private ComboBox problemTypeComboBox;
    @FXML private TextArea problemTextArea;
    @FXML private TextArea allergiesTextArea;
    @FXML private RadioButton yesAllergiesRadioButton;
    @FXML private ToggleGroup allergies;
    @FXML private RadioButton idkAllergiesRadioButton;
    @FXML private RadioButton noAllergiesRadioButton;
    @FXML private Button cancelButton;
    @FXML private Button requestHelpButton;
    @FXML private TextArea chronicConditionsTextArea;
    @FXML private RadioButton yesChronicConditionsRadioButton;
    @FXML private ToggleGroup chronicConditions;
    @FXML private RadioButton idkChronicConditionsRadioButton;
    @FXML private RadioButton noChronicConditionsRadioButton;
    @FXML private Label separatedByCommasLabel1;
    @FXML private Label separatedByCommasLabel2;

    private static PatientProblem patientProblem;

    public static PatientProblem getPatientProblem() {
        return patientProblem;
    }

    public static void setPatientProblem(PatientProblem patientProblem) {
        RequestHelpViewC.patientProblem = patientProblem;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        problemTypeComboBox.setItems(ProblemTypes.getPhysicalProblems());
        problemTypeComboBox.setVisible(false);
        separatedByCommasLabel1.setVisible(false);
        separatedByCommasLabel2.setVisible(false);
        alreadyProblemLabel.setVisible(false);
    }

    @FXML void physicalButtonAction(ActionEvent actionEvent) {
        problemTypeComboBox.setVisible(true);
    }

    @FXML void problemTypeComboBoxAction(ActionEvent actionEvent) {
        patientProblem.setTypeOfProblem(problemTypeComboBox.getSelectionModel().getSelectedItem().toString());
    }

    @FXML void mentalButtonAction(ActionEvent actionEvent) {
        problemTypeComboBox.setVisible(false);
        patientProblem.setTypeOfProblem(mentalRadioButton.getText());
    }

    @FXML void yesAllergiesButtonAction(ActionEvent actionEvent) {
        allergiesTextArea.setDisable(false);
        separatedByCommasLabel1.setVisible(true);
        patientProblem.setHasAllergies("yes");
    }

    @FXML void idkAllergiesButtonAction(ActionEvent actionEvent) {
        allergiesTextArea.setDisable(true);
        separatedByCommasLabel1.setVisible(false);
        patientProblem.setHasAllergies("unknown");
    }

    @FXML void noAllergiesButtonAction(ActionEvent actionEvent) {
        allergiesTextArea.setDisable(true);
        separatedByCommasLabel1.setVisible(false);
        patientProblem.setHasAllergies("no");
    }

    @FXML void yesChronicConditionsButtonAction(ActionEvent actionEvent) {
        chronicConditionsTextArea.setDisable(false);
        separatedByCommasLabel2.setVisible(true);
        patientProblem.setHasChronicConditions("yes");
    }

    @FXML void idkChronicConditionsButtonAction(ActionEvent actionEvent) {
        chronicConditionsTextArea.setDisable(true);
        separatedByCommasLabel2.setVisible(false);
        patientProblem.setHasChronicConditions("unknown");
    }

    @FXML void noChronicConditionsButtonAction(ActionEvent actionEvent) {
        chronicConditionsTextArea.setDisable(true);
        separatedByCommasLabel2.setVisible(false);
        patientProblem.setHasChronicConditions("no");
    }

    private void setDetails() {
        patientProblem.setDescriptionOfProblem(problemTextArea.getText());
        if (patientProblem.getHasAllergies().equals("yes")) {
            patientProblem.setAllergies(TextMisc.removeCommasFromTextAndToList(allergiesTextArea.getText()));
        } else {
            patientProblem.setAllergies(null);
        }
        if (patientProblem.getHasChronicConditions().equals("yes")) {
            patientProblem.setChronicConditions(TextMisc.removeCommasFromTextAndToList(chronicConditionsTextArea.getText()));
        } else {
            patientProblem.setChronicConditions(null);
        }
    }

    @FXML void cancelButtonAction(ActionEvent event) {
        ViewMisc.showStage("/view/menuView/patientView.fxml");
    }

    @FXML void requestHelpButtonAction(ActionEvent event) throws IOException {
        setDetails();
        PatientMisc.addLoggedPatientProblem(patientProblem);
        ViewMisc.showStage("/view/menuView/patientView.fxml");
    }
}
