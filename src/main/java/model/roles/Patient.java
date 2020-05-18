package model.roles;

import misc.users.UserMisc;
import model.other.PatientProblem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {

    private List<PatientProblem> problems = new ArrayList<>();

    public Patient() {
        this.problems = new ArrayList<>();
    }

    public Patient(String surname, String forename, LocalDate birthday, String email, String username,  String password, String role) {
        super(surname, forename, birthday, email, username, password, role);

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

    public PatientProblem returnSpecificProblem() {
        for (PatientProblem x: problems) {
            if (x.getTypeOfProblem().equals(((Doctor) UserMisc.getLoggedUser()).getSpecialty())) {
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
