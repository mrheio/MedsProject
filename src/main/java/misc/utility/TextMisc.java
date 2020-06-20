package misc.utility;

import javafx.beans.property.StringProperty;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextMisc {

    public static List<String> removeCommasFromTextAndToList(String text) {
        List<String> removedCommas = Arrays.asList(text.split("\\s*,\\s*"));
        return removedCommas;
    }

    public static void textBind(StringProperty stringProperty1, StringProperty stringProperty2) {
        stringProperty1.bind(stringProperty2);
    }

}
