package misc.users;

import model.other.PatientProblem;
import model.roles.Doctor;
import model.roles.Patient;
import model.roles.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoctorMisc {

    public static List<Doctor> getDoctorsFromFile(String fileName) throws IOException {
        List<Doctor> doctors = new ArrayList<>();
        UserMisc.readUsers(fileName);
        for (Person x: UserMisc.getUsers()) {
            if (x.getRole().equals("Doctor")) {
                doctors.add((Doctor) x);
            }
        }
        return doctors;
    }

    public static List<Doctor> getDoctorsFromUsers() {
        List<Doctor> doctors = new ArrayList<>();
        for (Person x: UserMisc.getUsers()) {
            if (x.getRole().equals("Patient")) {
                doctors.add((Doctor) x);
            }
        }
        return doctors;
    }

    public static List<Patient> getPatientsForLoggedDoctor() throws IOException {
        Doctor doctor = (Doctor) UserMisc.getLoggedUser();
        List<Patient> patientsForDoctor = new ArrayList<>();
        for (Patient x: PatientMisc.getPatientsFromFile("users.json")) {
            for (PatientProblem y: x.getProblems()) {
                if (y.getTypeOfProblem().equals(doctor.getSpecialty()) && y.getTreatment() == null) {
                    patientsForDoctor.add(x);
                }
            }
        }
        return patientsForDoctor;
    }

    public static void updateLoggedDoctorAddress(String address) throws IOException {
        for (Doctor x: DoctorMisc.getDoctorsFromUsers()) {
            if (x.getUsername().equals(UserMisc.getLoggedUser().getUsername())) {
                x.setAddress(address);
                UserMisc.setLoggedUser(x);
            }
        }
        UserMisc.writeUsers("users.json");
    }

}
