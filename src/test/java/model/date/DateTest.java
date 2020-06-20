package model.date;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DateTest {

    Date date = new Date();

    @Test
    public void generateDays() {
        List<Integer> days = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals("not the same", days, date.generateDays(5));
    }

    @Test
    public void generateYears() {
        List<Integer> years  = new ArrayList<>();
        for (int i=2000; i <=LocalDate.now().getYear(); i++) {
            years.add(i);
        }
        assertEquals("not the same", years, date.generateYears(2000));
    }
}