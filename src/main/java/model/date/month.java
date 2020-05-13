package model.date;

import java.util.ArrayList;
import java.util.List;

public class month {
    private int id;
    private String monthName;

    public month(int id, String monthName) {
        this.id = id;
        this.monthName = monthName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public int getId() {
        return id;
    }

    public String getMonthName() {
        return monthName;
    }

    public static List<month> generateMonths() {
        List<month> months = new ArrayList<month>();
        months.add(new month(1, "January"));
        months.add(new month(2, "February"));
        months.add(new month(3, "March"));
        months.add(new month(4, "April"));
        months.add(new month(5, "May"));
        months.add(new month(6, "June"));
        months.add(new month(7, "July"));
        months.add(new month(8, "August"));
        months.add(new month(9, "September"));
        months.add(new month(10, "October"));
        months.add(new month(11, "November"));
        months.add(new month(12, "December"));
        return months;
    }
}
