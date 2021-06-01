package ru.kalantyr.java.lesson7;

import ru.kalantyr.orm.*;

public class MainApp {
    public static void main(String[] args) {
        var e = new DataSet<Employee>();
        e.insert(new Employee("Оксана", 22, 20_000));
    }
}
