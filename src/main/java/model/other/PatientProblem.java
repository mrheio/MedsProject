package model.other;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.roles.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PatientProblem {
    private StringProperty typeOfProblem;
    private StringProperty descriptionOfProblem;
    private StringProperty hasAllergies;
    private StringProperty hasChronicConditions;
    private List<String> allergies;
    private List<String> chronicConditions;
    private StringProperty treatment;
    private StringProperty doctorName;
    private boolean isSolved;

    public PatientProblem() {
        this.typeOfProblem = new SimpleStringProperty();
        this.descriptionOfProblem = new SimpleStringProperty();
        this.hasAllergies = new SimpleStringProperty();
        this.hasChronicConditions = new SimpleStringProperty();
        this.allergies = new ArrayList<>();
        this.chronicConditions = new ArrayList<>();
        this.treatment = new SimpleStringProperty();
        this.doctorName = new SimpleStringProperty("-");
        isSolved = false;
    }

    public PatientProblem(String typeOfProblem, String descriptionOfProblem, String hasAllergies, String hasChronicConditions, List<String> allergies, List<String> chronicConditions, String treatment, String doctorName) {
        this.typeOfProblem = new SimpleStringProperty(typeOfProblem);
        this.descriptionOfProblem = new SimpleStringProperty(descriptionOfProblem);
        this.hasAllergies = new SimpleStringProperty(hasAllergies);
        this.hasChronicConditions = new SimpleStringProperty(hasChronicConditions);
        this.allergies = allergies;
        this.chronicConditions = chronicConditions;
        this.treatment = new SimpleStringProperty(treatment);
        this.doctorName = new SimpleStringProperty(doctorName);
        isSolved = false;
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

    public String getDoctorName() {
        return doctorName.get();
    }

    public StringProperty doctorNameProperty() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName.set(doctorName);
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientProblem problem = (PatientProblem) o;
        return isSolved == problem.isSolved &&
                Objects.equals(typeOfProblem.getValue(), problem.typeOfProblem.getValue()) &&
                Objects.equals(descriptionOfProblem.getValue(), problem.descriptionOfProblem.getValue()) &&
                Objects.equals(hasAllergies.getValue(), problem.hasAllergies.getValue()) &&
                Objects.equals(hasChronicConditions.getValue(), problem.hasChronicConditions.getValue()) &&
                Objects.equals(allergies, problem.allergies) &&
                Objects.equals(chronicConditions, problem.chronicConditions) &&
                Objects.equals(treatment.getValue(), problem.treatment.getValue()) &&
                Objects.equals(doctorName.getValue(), problem.doctorName.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeOfProblem, descriptionOfProblem, hasAllergies, hasChronicConditions, allergies, chronicConditions, treatment, doctorName, isSolved);
    }

    @Override
    public String toString() {
        return getDescriptionOfProblem();
    }
}
