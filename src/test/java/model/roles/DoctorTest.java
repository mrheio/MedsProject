package model.roles;

import model.other.PatientProblem;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DoctorTest {

    private static Patient p1 = new Patient("sn1", "fn1", LocalDate.of(1950, 4, 12), "email1", "un1", "pw1");
    private static Doctor d1 = new Doctor("sn1", "fn1", LocalDate.of(1950, 4, 12), "email1", "un1", "pw1", "type1", null);
    private static List<String> allergies1 = Arrays.asList("a1", "a2", "a3");
    private static List<String> cc1 = Arrays.asList("cc1", "cc2");
    private static PatientProblem problem1 = new PatientProblem("type1", "description1", "yes", "yes", allergies1, cc1, null, null);
    private static PatientProblem problem2 = new PatientProblem("type1", "description2", "no", "no", null, null, null, null);
    private static PatientProblem problem3 = new PatientProblem("type3", "description3", "no", "no", null, null, null, null);
    private static List<Patient> patients = Arrays.asList(p1);

    @BeforeClass
    public static void addProblems() {
        p1.getProblems().add(problem1);
        p1.getProblems().add(problem2);
        p1.getProblems().add(problem3);
    }

    @Test
    public void getProblemsForDoctor() throws IOException {
        List<PatientProblem> problems = Arrays.asList(problem1, problem2);
        assertEquals("not the same problems", problems, d1.getProblemsForDoctor(patients));
    }

    @Test
    public void getNoTreatmentUnsolvedProblemsForDoctor() throws IOException {
        List<PatientProblem> problems = Arrays.asList(problem1, problem2);
        assertEquals("not the same", problems, d1.getNoTreatmentUnsolvedProblemsForDoctor(patients));
        problem1.setSolved(true);
        assertFalse("same lists", problems.equals(d1.getNoTreatmentUnsolvedProblemsForDoctor(patients)));
    }
}