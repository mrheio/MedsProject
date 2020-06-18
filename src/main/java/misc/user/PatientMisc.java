package misc.user;

import model.other.PatientProblem;
import model.roles.Patient;
import model.roles.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientMisc {

    public static void addPatientProblem(Patient patient, PatientProblem patientProblem) throws IOException {
        patient.getProblems().add(patientProblem);
        UserMisc.updateUsers(patient);
    }

    public static void addLoggedPatientProblem(PatientProblem patientProblem) throws IOException {
        Patient loggedPatient = (Patient) UserMisc.getLoggedUser();
        addPatientProblem(loggedPatient, patientProblem);
    }

    public static void deletePatientProblem(Patient patient, PatientProblem patientProblem) throws IOException {
        patient.getProblems().remove(patientProblem);
        UserMisc.updateUsers(patient);
    }

    public static void deleteLoggedPatientProblem(PatientProblem patientProblem) throws IOException {
        Patient loggedPatient = (Patient) UserMisc.getLoggedUser();
        deletePatientProblem(loggedPatient, patientProblem);
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

    public static List<Patient> getPatientsFromFile() throws IOException {
        UserMisc.readUsers();
        return getPatientsFromUsers();
    }



}
