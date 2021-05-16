package ru.kalantyr.java.lesson2.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.kalantyr.java.lesson2.*;

public class MainAppTests {
    @Test
    public void parseAndSum_test() throws MyArrayDataException {
        String[][] values = {
                { "1.1", "2.2", "3.3", "4.4" },
                { "5.5", "6.6", "7.7", "8.8" },
                { "-1", "-2", "-3", "-4" },
                { "-5", "-6", "-7", "-8" }
        };
        var result = MainApp.parseAndSum(values);
        Assert.assertEquals(3.6f, result, 0.001f);
    }

    @Test
    public void parseAndSum_size_test() {
        String[][] values1 = {
                { "1.1", "2.2", "3.3", "4.4" },
                { "5.5", "6.6", "7.7", "8.8" },
                { "-1", "-2", "-3", "-4" }
        };
        Assert.assertThrows(MyArraySizeException.class, () -> MainApp.parseAndSum(values1));

        String[][] values2 = {
                { "1.1", "2.2", "3.3", "4.4" },
                { "5.5", "6.6", "7.7" },
                { "-1", "-2", "-3" },
                { "-5", "-6", "-7", "-8" }
        };
        Assert.assertThrows(MyArraySizeException.class, () -> MainApp.parseAndSum(values2));
    }

    @Test
    public void parseAndSum_data_test() {
        String[][] values = {
                { "1.1", "2.2", "3.3", "4.4" },
                { "5.5", "6.6", "7.7", "8.8" },
                { "-1", "-2", "абракадабра", "-4" },
                { "-5", "-6", "-7", "-8" }
        };
        var th = Assert.assertThrows(MyArrayDataException.class, () -> MainApp.parseAndSum(values));
        Assert.assertTrue(th.getMessage().contains("абракадабра"));
    }
}
