package ru.kalantyr.java.lesson3.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.kalantyr.java.lesson3.MainApp;

import java.util.ArrayList;

public class MainAppTests {

    @Test
    public void swap_test(){
        var strings = new String[] { "A", "B", "C" };
        MainApp.swap(strings, 0, 2);
        Assert.assertEquals("C", strings[0]);
        Assert.assertEquals("A", strings[2]);

        MainApp.swap(strings, 1, 1);
        Assert.assertEquals("B", strings[1]);

        var floats = new Float[] { 1.1f, 2.2f, 3.3f };
        MainApp.swap(floats, 0, 2);
        Assert.assertEquals(3.3f, floats[0], 0.01f);
        Assert.assertEquals(1.1f, floats[2], 0.01f);
    }

    @Test
    public void arrayToList_test() {
        String[] array = { "Aa", "Bb", "Cc" };
        var list = MainApp.arrayToList(array);
        Assert.assertEquals(ArrayList.class, list.getClass());
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void countWords_test() {
        String[] values = {
                "Яблоко", "Апельсин", "Груша", "Яблоко", "Яблоко", "Апельсин"
        };
        var map = MainApp.countWords(values);
        Assert.assertEquals(3, (long) map.get("Яблоко"));
        Assert.assertEquals(2, (long) map.get("Апельсин"));
    }
}
