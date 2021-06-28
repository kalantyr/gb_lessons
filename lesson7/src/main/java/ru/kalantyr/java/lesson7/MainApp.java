package ru.kalantyr.java.lesson7;

import ru.kalantyr.orm.*;

import java.sql.SQLException;
import java.util.Arrays;

public class MainApp {
    private static final String fileName = "D:\\Work\\lesson7.db";

    public static void main(String[] args) {
        var dataContext = new DataContext("org.sqlite.JDBC", "jdbc:sqlite:" + fileName);

        Employee[] staf = {
                new Employee("Иван", 44, 40_000),
                new Employee("Марь Ивановна", 55, 50_000)
        };

        var employees = new DataSet<>(dataContext, Employee.class);
        try {
            // создаём таблицу, если её ещё нет
            employees.create();

            // добавляем поштучно
            employees.insert(new Employee("Оксана", 22, 20_000));
            employees.insert(new Employee("Глеб", 33, 30_000));

            // добавляем пачкой
            employees.insert(Arrays.asList(staf)); // почему массив не реализует Iterable? приходится конвертировать в модифицируемый список
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
