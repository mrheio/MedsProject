package misc.utility;

import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextMisc {

    public static List<String> removeCommasFromTextAndToList(String text) {
        List<String> removedCommas = Arrays.asList(text.split("\\s*,\\s*"));
        return removedCommas;
    }

}
