package model.roles;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {

    protected final StringProperty specialty;
    protected List<String> solvedProblems;
    protected StringProperty address;

    public Doctor() {
        this.solvedProblems = new ArrayList<>();
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

    public List<String> getSolvedProblems() {
        return solvedProblems;
    }

    public void setSolvedProblems(List<String> solvedProblems) {
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

    @Override
    public String toString() {
        return "Doctor{" +
                "specialty=" + specialty +
                ", solvedProblems=" + solvedProblems +
                ", address=" + address +
                "} " + super.toString();
    }
}
