package misc.utility;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TextMiscTest {

    @Test
    public void checkIfCommasAreRemovedAndConvertedIntoList(){
        TextMisc textMisc = new TextMisc();
        String text = "1, 2, 3, 4, 5";
        List<String> list = Arrays.asList("1", "2", "3", "4", "5");
        System.out.println(textMisc.removeCommasFromTextAndToList(text));
        System.out.println(list);
        assertEquals("lists are different", list, textMisc.removeCommasFromTextAndToList(text));
    }
}