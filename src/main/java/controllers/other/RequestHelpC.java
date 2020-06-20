package controllers.other;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import misc.utility.NodeMisc;
import misc.utility.TextMisc;
import misc.user.PatientMisc;
import misc.utility.ViewMisc;
import model.other.PatientProblem;
import model.other.ProblemTypes;
import static java.util.Arrays.asList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RequestHelpC implements Initializable {

    @FXML private ToggleGroup typeOfProblem;
        @FXML private RadioButton physicalRadioButton;
        @FXML private RadioButton mentalRadioButton;
    @FXML private ComboBox problemTypeComboBox;
    @FXML private TextArea problemTextArea;
    @FXML private TextArea allergiesTextArea;
    @FXML private TextArea chronicConditionsTextArea;
    @FXML private Label separatedByCommasLabel1;
    @FXML private Label separatedByCommasLabel2;

    private static PatientProblem patientProblem;

    public static PatientProblem getPatientProblem() {
        return patientProblem;
    }

    public static void setPatientProblem(PatientProblem patientProblem) {
        RequestHelpC.patientProblem = patientProblem;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        problemTypeComboBox.setItems(ProblemTypes.getPhysicalProblems());
        NodeMisc.hideNode(problemTypeComboBox, separatedByCommasLabel1, separatedByCommasLabel2);
    }

    @FXML void physicalButtonAction(ActionEvent actionEvent) {
        NodeMisc.showNode(problemTypeComboBox);
    }

    @FXML void problemTypeComboBoxAction(ActionEvent actionEvent) {
        patientProblem.setTypeOfProblem(problemTypeComboBox.getSelectionModel().getSelectedItem().toString());
    }

    @FXML void mentalButtonAction(ActionEvent actionEvent) {
        problemTypeComboBox.getSelectionModel().clearSelection();
        NodeMisc.hideNode(problemTypeComboBox);
        patientProblem.setTypeOfProblem(mentalRadioButton.getText());
    }

    @FXML void yesAllergiesButtonAction(ActionEvent actionEvent) {
        NodeMisc.showEnableNode(asList(separatedByCommasLabel1), asList(allergiesTextArea));
        patientProblem.setHasAllergies("yes");
    }

    @FXML void idkAllergiesButtonAction(ActionEvent actionEvent) {
        NodeMisc.hideDisableNode(asList(separatedByCommasLabel1), asList(allergiesTextArea));
        patientProblem.setHasAllergies("unknown");
    }

    @FXML void noAllergiesButtonAction(ActionEvent actionEvent) {
        NodeMisc.hideDisableNode(asList(separatedByCommasLabel1), asList(allergiesTextArea));
        patientProblem.setHasAllergies("no");
    }

    @FXML void yesChronicConditionsButtonAction(ActionEvent actionEvent) {
        NodeMisc.showEnableNode(asList(separatedByCommasLabel2), asList(chronicConditionsTextArea));
        patientProblem.setHasChronicConditions("yes");
    }

    @FXML void idkChronicConditionsButtonAction(ActionEvent actionEvent) {
        NodeMisc.hideDisableNode(asList(separatedByCommasLabel2), asList(chronicConditionsTextArea));
        patientProblem.setHasChronicConditions("unknown");
    }

    @FXML void noChronicConditionsButtonAction(ActionEvent actionEvent) {
        NodeMisc.hideDisableNode(asList(separatedByCommasLabel2), asList(chronicConditionsTextArea));
        patientProblem.setHasChronicConditions("no");
    }

    @FXML void cancelButtonAction(ActionEvent event) {
        ViewMisc.showPatientMenu();
    }

    @FXML void requestHelpButtonAction(ActionEvent event) throws IOException {
        setDetails();
        PatientMisc.addLoggedPatientProblem(patientProblem);
        ViewMisc.showPatientMenu();
    }

    private void setDetails() {
        if (patientProblem.getTypeOfProblem() == null) {
            return;
        }
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


}
