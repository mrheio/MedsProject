package model.other;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class PatientProblem {
    private StringProperty typeOfProblem;
    private StringProperty descriptionOfProblem;
    private StringProperty hasAllergies;
    private StringProperty hasChronicConditions;
    private List<String> allergies;
    private List<String> chronicConditions;
    private StringProperty treatment;

    public PatientProblem() {
        this.typeOfProblem = new SimpleStringProperty();
        this.descriptionOfProblem = new SimpleStringProperty();
        this.hasAllergies = new SimpleStringProperty();
        this.hasChronicConditions = new SimpleStringProperty();
        this.allergies = new ArrayList<>();
        this.chronicConditions = new ArrayList<>();
        this.treatment = new SimpleStringProperty();
    }

    public PatientProblem(String typeOfProblem, String descriptionOfProblem, String hasAllergies, String hasChronicConditions, List<String> allergies, List<String> chronicConditions, String treatment) {
        this.typeOfProblem = new SimpleStringProperty(typeOfProblem);
        this.descriptionOfProblem = new SimpleStringProperty(descriptionOfProblem);
        this.hasAllergies = new SimpleStringProperty(hasAllergies);
        this.hasChronicConditions = new SimpleStringProperty(hasChronicConditions);
        this.allergies = allergies;
        this.chronicConditions = chronicConditions;
        this.treatment = new SimpleStringProperty(treatment);
    }

    public String getTypeOfProblem() {
        return typeOfProblem.get();
    }

    public StringProperty typeOfProblemProperty() {
        return typeOfProblem;
    }

    public void setTypeOfProblem(String typeOfProblem) {
        this.typeOfProblem.set(typeOfProblem);
    }

    public String getDescriptionOfProblem() {
        return descriptionOfProblem.get();
    }

    public StringProperty descriptionOfProblemProperty() {
        return descriptionOfProblem;
    }

    public void setDescriptionOfProblem(String descriptionOfProblem) {
        this.descriptionOfProblem.set(descriptionOfProblem);
    }

    public String getHasAllergies() {
        return hasAllergies.get();
    }

    public StringProperty hasAllergiesProperty() {
        return hasAllergies;
    }

    public void setHasAllergies(String hasAllergies) {
        this.hasAllergies.set(hasAllergies);
    }

    public String getHasChronicConditions() {
        return hasChronicConditions.get();
    }

    public StringProperty hasChronicConditionsProperty() {
        return hasChronicConditions;
    }

    public void setHasChronicConditions(String hasChronicConditions) {
        this.hasChronicConditions.set(hasChronicConditions);
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public List<String> getChronicConditions() {
        return chronicConditions;
    }

    public void setChronicConditions(List<String> chronicConditions) {
        this.chronicConditions = chronicConditions;
    }

    public String getTreatment() {
        return treatment.get();
    }

    public StringProperty treatmentProperty() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment.set(treatment);
    }

    @Override
    public String toString() {
        return "PatientProblem{" +
                "typeOfProblem=" + typeOfProblem +
                ", descriptionOfProblem=" + descriptionOfProblem +
                ", hasAllergies=" + hasAllergies +
                ", hasChronicConditions=" + hasChronicConditions +
                ", allergies=" + allergies +
                ", chronicConditions=" + chronicConditions +
                ", treatment=" + treatment +
                '}';
    }
}
