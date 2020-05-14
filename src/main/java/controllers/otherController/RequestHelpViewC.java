package controllers.otherController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import misc.utility.NodeMisc;
import misc.utility.TextMisc;
import misc.users.PatientMisc;
import misc.utility.ViewMisc;
import model.other.PatientProblem;
import model.other.ProblemTypes;
import static java.util.Arrays.asList;

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
        NodeMisc.hideNode(problemTypeComboBox, separatedByCommasLabel1, separatedByCommasLabel2, alreadyProblemLabel);
    }

    @FXML void physicalButtonAction(ActionEvent actionEvent) {
        NodeMisc.showNode(problemTypeComboBox);
    }

    @FXML void problemTypeComboBoxAction(ActionEvent actionEvent) {
        patientProblem.setTypeOfProblem(problemTypeComboBox.getSelectionModel().getSelectedItem().toString());
    }

    @FXML void mentalButtonAction(ActionEvent actionEvent) {
        NodeMisc.showNode(problemTypeComboBox);
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
        ViewMisc.showStage("/view/menuView/patientView.fxml");
    }

    @FXML void requestHelpButtonAction(ActionEvent event) throws IOException {
        setDetails();
        PatientMisc.addLoggedPatientProblem(patientProblem);
        ViewMisc.showStage("/view/menuView/patientView.fxml");
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


}
