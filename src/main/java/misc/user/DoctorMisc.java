package misc.user;

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
}
