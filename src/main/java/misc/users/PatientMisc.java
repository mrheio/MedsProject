package misc.users;

import model.other.PatientProblem;
import model.roles.Patient;
import model.roles.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PatientMisc {

    public static void addLoggedPatientProblem(PatientProblem patientProblem) throws IOException {
        for (Patient x: PatientMisc.getPatientsFromUsers()) {
            if (x.getUsername().equals(UserMisc.getLoggedUser().getUsername())) {
                x.getProblems().add(patientProblem);
                UserMisc.setLoggedUser(x);
            }
        }
        UserMisc.writeUsers("users.json");
    }

    public static void deleteLoggedPatientProblem(PatientProblem patientProblem) throws IOException {
        for (Patient x: PatientMisc.getPatientsFromUsers()) {
            if (x.getUsername().equals(UserMisc.getLoggedUser().getUsername())) {
                Iterator<PatientProblem> iterator = x.getProblems().iterator();
                while(iterator.hasNext()) {
                    PatientProblem problem = iterator.next();
                    if (problem.getDescriptionOfProblem().equals(patientProblem.getDescriptionOfProblem())) {
                        iterator.remove();
                    }
                }
                UserMisc.setLoggedUser(x);
            }
        }
        UserMisc.writeUsers("users.json");
    }

    public static List<Patient> getPatientsFromUsers() {
        List<Patient> patients = new ArrayList<>();
        for (Person x: UserMisc.getUsers()) {
            if (x.getRole().equals("Patient")) {
                patients.add((Patient) x);
            }
        }
        return patients;
    }

    public static List<Patient> getPatientsFromFile(String fileName) throws IOException {
        List<Patient> patients = new ArrayList<>();
        UserMisc.readUsers(fileName);
        for (Person x: UserMisc.getUsers()) {
            if (x.getRole().equals("Patient")) {
                patients.add((Patient) x);
            }
        }
        return patients;
    }


}

