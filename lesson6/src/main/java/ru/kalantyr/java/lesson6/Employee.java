package ru.kalantyr.java.lesson6;

public class Employee {
    private final String name;
    private int age;
    private int salary;

    public Employee(String name, int age, int salary) {

        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }
}
