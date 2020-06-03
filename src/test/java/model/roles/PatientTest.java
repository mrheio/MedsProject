package model.roles;

import misc.utility.security.BCrypt;
import model.other.PatientProblem;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PatientTest {

    private static Patient p1 = new Patient("sn1", "fn1", LocalDate.of(1950, 4, 12), "email1", "un1", "pw1");
    private static Patient p2 = new Patient("sn2", "fn2", LocalDate.of(1990, 6, 3), "email2", "un2", "pw2");
    private static Patient p3 = new Patient("sn3", "fn3", LocalDate.of(1950, 7, 23), "email3", "un3", "pw3");
    private static List<String> allergies1 = Arrays.asList("a1", "a2", "a3");
    private static List<String> cc1 = Arrays.asList("cc1", "cc2");
    private static PatientProblem problem1 = new PatientProblem("type1", "description1", "yes", "yes", allergies1, cc1, null);
    private static PatientProblem problem2 = new PatientProblem("type2", "description1", "no", "no", null, null, "treatment2");
    private static PatientProblem problem3 = new PatientProblem("type3", "description1", "no", "no", null, null, null);

    @BeforeClass
    public static void addProblems() {
        p1.getProblems().add(problem1);
        p1.getProblems().add(problem2);
        p1.getProblems().add(problem3);
    }

    @Test
    public void returnSpecificProblemShouldGetTheProblemWithTheWantedType() {
        assertEquals(problem1, p1.returnSpecificProblem("type1"));
        assertEquals(problem2, p1.returnSpecificProblem("type2"));
        assertTrue("different problems", problem3.equals(p1.returnSpecificProblem("type3")));
        assertFalse("same problem", problem1.equals(p1.returnSpecificProblem("type3")));
        assertFalse("same problem", problem1.equals(p2.returnSpecificProblem("type1")));
    }

    @Test
    public void returnNoTreatmentProblemsShouldGetTheProblemsWithTreatmentSetOnNull() {
        List<PatientProblem> noTreatment = Arrays.asList(problem1, problem3);
        assertEquals("different no treatment problems lists", noTreatment, p1.returnNoTreatmentProblems());
        List<PatientProblem> problems = Arrays.asList(problem1, problem2, problem3);
        assertFalse("same lists", problems.equals(p1.returnNoTreatmentProblems()));
    }
}