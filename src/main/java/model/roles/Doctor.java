package model.roles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import misc.user.PatientMisc;
import model.other.PatientProblem;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {

    protected final StringProperty specialty;
    protected StringProperty address;
    protected List<PatientProblem> solvedProblems = new ArrayList<>();

    public Doctor() {
        this.specialty = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
    }

    public Doctor(String surname, String forename, LocalDate birthday, String email, String username, String password, String specialty, String address) {
        super(surname, forename, birthday, email, username, password, "Doctor");
        this.specialty = new SimpleStringProperty(specialty);
        this.address = new SimpleStringProperty(address);
    }

    public String getSpecialty() {
        return specialty.get();
    }

    public StringProperty specialtyProperty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty.set(specialty);
    }

    public List<PatientProblem> getSolvedProblems() {
        return solvedProblems;
    }

    public void setSolvedProblems(List<PatientProblem> solvedProblems) {
        this.solvedProblems = solvedProblems;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    @JsonIgnore
    public List<PatientProblem> getProblemsForDoctor() throws IOException {
        List<Patient> allPatients = PatientMisc.getPatientsFromUsers();
        List<PatientProblem> problemsForDoctor = new ArrayList<>();
        for (Patient x: allPatients) {
            problemsForDoctor.addAll(x.getProblemsForSpecialty(getSpecialty()));
        }
        return problemsForDoctor;
    }

    @JsonIgnore
    public List<PatientProblem> getNoTreatmentUnsolvedProblemsForDoctor() throws IOException {
        List<PatientProblem> noTreatmentUnsolvedProblems = new ArrayList<>();
        for (PatientProblem x: getProblemsForDoctor()) {
            if (x.getTreatment() == null && x.isSolved() == false) {
                noTreatmentUnsolvedProblems.add(x);
            }
        }
        return noTreatmentUnsolvedProblems;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "specialty=" + specialty +
                ", solvedProblems=" + solvedProblems +
                ", address=" + address +
                "} " + super.toString();
    }
}
