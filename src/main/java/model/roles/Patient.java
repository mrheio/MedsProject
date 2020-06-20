package model.roles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.other.PatientProblem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {

    private List<PatientProblem> problems = new ArrayList<>();

    public Patient() {
    }

    public Patient(String surname, String forename, LocalDate birthday, String email, String username,  String password) {
        super(surname, forename, birthday, email, username, password, "Patient");
    }

    public List<PatientProblem> getProblems() {
        return problems;
    }

    public void setProblems(List<PatientProblem> problems) {
        this.problems = problems;
    }

    @JsonIgnore
    public List<PatientProblem> getProblemsForSpecialty(String specialty) {
        List<PatientProblem> specialtyProblems = new ArrayList<>();
        for (PatientProblem x: problems) {
            if (x.getTypeOfProblem().equals(specialty)) {
                specialtyProblems.add(x);
            }
        }
        return specialtyProblems;
    }

    @JsonIgnore
    public List<PatientProblem> getNoTreatmentProblems() {
        List<PatientProblem> noTreatmentProblems = new ArrayList<>();
        for (PatientProblem x: problems) {
            if (x.getTreatment() == null) {
                noTreatmentProblems.add(x);
            }
        }
        return noTreatmentProblems;
    }

    @JsonIgnore
    public List<PatientProblem> getTreatmentProblems() {
        List<PatientProblem> treatmentProblems = new ArrayList<>();
        for (PatientProblem x: problems) {
            if (x.getTreatment() != null) {
                treatmentProblems.add(x);
            }
        }
        return treatmentProblems;
    }

    @JsonIgnore
    public List<PatientProblem> getUnsolvedProblems() {
        List<PatientProblem> unsolvedProblems = new ArrayList<>();
        for (PatientProblem x: problems) {
            if (x.isSolved() == false) {
                unsolvedProblems.add(x);
            }
        }
        return unsolvedProblems;
    }

    @JsonIgnore
    public List<PatientProblem> getSolvedProblems() {
        List<PatientProblem> unsolvedProblems = new ArrayList<>();
        for (PatientProblem x: problems) {
            if (x.isSolved() == true) {
                unsolvedProblems.add(x);
            }
        }
        return unsolvedProblems;
    }

    public void unsolvedToSolved(PatientProblem patientProblem) {
        for (PatientProblem x: getUnsolvedProblems()) {
            if (x.equals(patientProblem)) {
                x.setSolved(true);
            }
        }
    }

    public void deletePatientProblem(PatientProblem patientProblem) {
        problems.remove(patientProblem);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "problems=" + problems +
                "} " + super.toString();
    }
}
