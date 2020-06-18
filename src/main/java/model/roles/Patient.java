package model.roles;

import model.other.PatientProblem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {

    private List<PatientProblem> problems = new ArrayList<>();

    public Patient() {
        this.problems = new ArrayList<>();
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

    public void deletePatientProblem(PatientProblem patientProblem) {
        problems.remove(patientProblem);
    }

    public PatientProblem returnSpecificProblem(String specialty) {
        for (PatientProblem x: problems) {
            if (x.getTypeOfProblem().equals(specialty)) {
                return x;
            }
        }
        return null;
    }

    public List<PatientProblem> returnNoTreatmentProblems() {
        List<PatientProblem> noTreatmentProblems = new ArrayList<>();
        for (PatientProblem x: problems) {
            if (x.getTreatment() == null) {
                noTreatmentProblems.add(x);
            }
        }
        return noTreatmentProblems;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "problems=" + problems +
                "} " + super.toString();
    }
}
