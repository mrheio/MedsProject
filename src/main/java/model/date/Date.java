package model.date;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class Date {
    private int day;
    private month month;
    private int year;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public model.date.month getMonth() {
        return month;
    }

    public void setMonth(model.date.month month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static ObservableList<Integer> generateDays(int lastDay) {
        ObservableList<Integer> days = FXCollections.observableArrayList();
        for (int i=1; i<=lastDay; i++) {
            days.add(i);
        }
        return days;
    }

    public static ObservableList<month> generateMonths() {
        ObservableList<month> months = FXCollections.observableArrayList();
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

    public static ObservableList<Integer> generateYears(int startingYear) {
        LocalDate currentDate = LocalDate.now();
        ObservableList<Integer> years = FXCollections.observableArrayList();
        for (int i=startingYear; i<currentDate.getYear(); i++) {
            years.add(i);
        }
        return years;
    }

    public static ObservableList<Integer> generateDaysForMonth(month month) {
        ObservableList<Integer> days = generateDays(31);
        if (month.equals("April") || month.equals("June") || month.equals("September") || month.equals("November")) {
                days = generateDays(30);
            }
        if (month.equals("February")) {
                days = generateDays(28);
            }
        return days;
    }
}