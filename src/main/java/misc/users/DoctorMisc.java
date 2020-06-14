package misc.users;

import misc.utility.FileMisc;
import model.other.PatientProblem;
import model.roles.Doctor;
import model.roles.Patient;
import model.roles.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoctorMisc {

    public static List<Doctor> getDoctorsFromUsers() {
        List<Doctor> doctors = new ArrayList<>();
        for (Person x: UserMisc.getUsers()) {
            if (x.getRole().equals("Doctor")) {
                doctors.add((Doctor) x);
            }
        }
        return doctors;
    }

    public static List<Doctor> getDoctorsFromFile() throws IOException {
        UserMisc.readUsers();
        return getDoctorsFromUsers();
    }

    public static List<Patient> getPatientsForDoctor(Doctor doctor) throws IOException {

        List<Patient> allPatients = PatientMisc.getPatientsFromFile();
        List<Patient> patientsForDoctor = new ArrayList<>();
        for (Patient x: allPatients) {
            for (PatientProblem y: x.getProblems()) {
                if (y.getTypeOfProblem().equals(doctor.getSpecialty()) && y.getTreatment() == null) {
                    patientsForDoctor.add(x);
                }
            }
        }
        return patientsForDoctor;
    }

    public static List<Patient> getPatientsForLoggedDoctor() throws IOException {
        Doctor doctor = (Doctor) UserMisc.getLoggedUser();
        return getPatientsForDoctor(doctor);
    }

    public static void updateLoggedDoctorAddress(String address) throws IOException {
        Doctor doctor = (Doctor) UserMisc.getLoggedUser();
        doctor.setAddress(address);
        UserMisc.updateUsers(doctor);
    }

}
