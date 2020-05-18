package misc.users;

import misc.utility.FileMisc;
import model.other.PatientProblem;
import model.roles.Patient;
import model.roles.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PatientMisc {

    public static void addLoggedPatientProblem(PatientProblem patientProblem) throws IOException {
        Patient patient = (Patient) UserMisc.getLoggedUser();
        patient.getProblems().add(patientProblem);
        UserMisc.updateUsers(patient);
    }

    public static void deleteLoggedPatientProblem(PatientProblem patientProblem) throws IOException {
        Patient patient = (Patient) UserMisc.getLoggedUser();
        patient.getProblems().remove(patientProblem);
        UserMisc.updateUsers(patient);
    }

    public static List<Patient> getPatientsFromFile() throws IOException {
        List<Patient> patients = new ArrayList<>();
        UserMisc.readUsers();
        for (Person x: UserMisc.getUsers()) {
            if (x.getRole().equals("Patient")) {
                patients.add((Patient) x);
            }
        }
        return patients;
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




}
