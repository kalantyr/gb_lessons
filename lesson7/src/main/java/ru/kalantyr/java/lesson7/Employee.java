package ru.kalantyr.java.lesson7;

import ru.kalantyr.orm.AppColumn;
import ru.kalantyr.orm.AppTable;

/**
 * DTO для работы с сотрудниками
 */
@AppTable(tableName = "Staff")
public class Employee {

    @AppColumn(columnName = "Name")
    public String name;

    // без аннотации тоже будет работать
    public int age;

    @AppColumn(columnName = "Salary")
    public int salary;

    public Employee(String name, int age, int salary) {

        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}