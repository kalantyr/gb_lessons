package ru.kalantyr.java.lesson7;

import ru.kalantyr.orm.*;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) {
        var dataContext = new DataContext("org.sqlite.JDBC", "jdbc:sqlite:C:\\Temp\\lesson7.db");

        var employees = new DataSet<>(dataContext, Employee.class);
        try {
            employees.create();
            employees.insert(new Employee("Оксана", 22, 20_000));
            employees.insert(new Employee("Глеб", 33, 30_000));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
