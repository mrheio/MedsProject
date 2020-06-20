package model.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        month month = (month) o;
        return Objects.equals(monthName, month.monthName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, monthName);
    }
}
