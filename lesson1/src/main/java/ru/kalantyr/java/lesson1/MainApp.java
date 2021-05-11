package ru.kalantyr.java.lesson1;

import ru.kalantyr.java.lesson1.moving.*;

public class MainApp {
    public static void main(String[] args) {
        var t1 = new Skateboard();
        var t2 = new Motorcycle();
        var t3 = new Car();

        var p1 = new Person("Олег");
        p1.start(t1);
        p1.stop(t1);

        var p2 = new Person("Оксана");
        p2.start(t2);
        p2.stop(t2);

        var p3 = new Person("Николай");
        p3.start(t3);
        p3.stop(t3);
    }
}
