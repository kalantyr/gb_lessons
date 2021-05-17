package ru.kalantyr.java.lesson3.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.kalantyr.java.lesson3.*;

public class BoxTests {

    @Test
    public void getWeight_test() {
        var box = new Box<Apple>();
        box.add(new Apple());
        box.add(new Apple());
        Assert.assertEquals(2f, box.getWeight(), 0.01f);

        var box2 = new Box<Orange>();
        box2.add(new Orange());
        box2.add(new Orange());
        Assert.assertEquals(3f, box2.getWeight(), 0.01f);
    }

    @Test
    public void compare_test() {
        var box1 = new Box<Apple>();
        var box2 = new Box<Orange>();
        Assert.assertTrue(box1.compare(box2));
        Assert.assertTrue(box2.compare(box1));

        box1.add(new Apple());
        box2.add(new Orange());
        Assert.assertFalse(box1.compare(box2));
        Assert.assertFalse(box2.compare(box1));

        box1.add(new Apple());
        box1.add(new Apple());
        box2.add(new Orange());
        Assert.assertTrue(box1.compare(box2));
        Assert.assertTrue(box2.compare(box1));
    }

    @Test
    public void transferTo_test() {
        var box1 = new Box<Apple>();
        var box2 = new Box<Apple>();
        box1.transferTo(box2);
        Assert.assertTrue(box1.isEmpty());
        Assert.assertTrue(box2.isEmpty());

        box1.add(new Apple());
        box1.transferTo(box2);
        Assert.assertTrue(box1.isEmpty());
        Assert.assertTrue(box2.getWeight() > 0);

        box1.add(new Apple());
        box1.add(new Apple());
        box1.transferTo(box2);
        Assert.assertTrue(box1.isEmpty());
        Assert.assertEquals(3f, box2.getWeight(), 0.01f);
    }
}
