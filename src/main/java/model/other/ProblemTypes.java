package model.other;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProblemTypes {
    private static ObservableList<String> physicalProblems = FXCollections.observableArrayList("allergy",
            "cardiology",
            "dermatology",
            "endocrinology",
            "family medicine",
            "surgery",
            "neurology",
            "pediatrics",
            "urology");

    public ProblemTypes() {
    }

    public static ObservableList<String> getPhysicalProblems() {
        return physicalProblems;
    }

    public static void setPhysicalProblems(ObservableList<String> physicalProblems) {
        ProblemTypes.physicalProblems = physicalProblems;
    }
}
