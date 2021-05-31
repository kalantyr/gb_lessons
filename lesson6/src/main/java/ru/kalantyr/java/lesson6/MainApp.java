package ru.kalantyr.java.lesson6;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainApp {
    public static void main(String[] args){
        String[] words = {
                "Москва", "Казань", "Ростов", "Москва", "Владивосток", "Москва", "Ростов", "Анапа"
        };

        Employee[] employees = {
                new Employee("Клавдия Ивановна", 55, 12_500),
                new Employee("Маша", 22, 25_000),
                new Employee("Олег", 33, 50_000),
                new Employee("Оксана", 44, 100_000),
                new Employee("Глеб", 55, 200_000),
                new Employee("Сергей", 55, 200_000)
        };

        task1(words);
        task2(employees);
        task3(employees);
        task4("Съешь ещё этих мягких французских булок");
        task5();
        task6(words);
        task7(words);
    }

    /**
     * 1. Создайте массив с набором слов, и с помощью Stream API найдите наиболее часто встречающееся
     */
    public static void task1(String[] words) {
        var groups = Arrays
                .stream(words)
                .collect(Collectors.groupingBy(s -> s));
        var sorted = groups
                .entrySet()
                .stream()
                .sorted((g1, g2) -> Integer.compare(g2.getValue().size(), g1.getValue().size()))
                .collect(Collectors.toList());
        for (var pair : sorted) {
            System.out.printf("%s (%d)", pair.getKey(), pair.getValue().size());
            System.out.println();
        }
    }

    /**
     * 2. Создайте массив объектов типа Сотрудник (с полями Имя, Возраст, Зарплата) и вычислите среднюю зарплату сотрудника
     */
    public static void task2(Employee[] employees) {
        if (employees.length == 0) {
            System.out.println("Нет данных о сотрудниках");
            return;
        }

        var sum = Arrays
                .stream(employees)
                .mapToInt(Employee::getSalary)
                // .average() // это работает криво (вместо int зачем-то возвращается OptionalDouble для IntStream)
                .sum();
        var averageSalary = sum / employees.length;

        System.out.println("Средняя зарплата = " + averageSalary);
    }

    /**
     * 3. Ищет самых старших сотрудников и отпечатает в консоль сообщение вида “N самых старших сотрудников зовут: имя1, имя2, имяN;”
     */
    public static void task3(Employee[] employees) {
        if (employees.length == 0) {
            System.out.println("Нет данных о сотрудниках");
            return;
        }

        var maxAge = Arrays
                .stream(employees)
                .mapToInt(Employee::getAge)
                .max()
                .getAsInt();
        var oldest = Arrays
                .stream(employees)
                .filter(e -> e.getAge() == maxAge)
                //.forEach(e -> System.out.println(""));
                .collect(Collectors.toList());
        var names = oldest
                .stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", "));

        System.out.println(oldest.size() + " самых старших сотрудников зовут: " + names);
    }

    /**
     * 4. Взять строку, состоящую из 100 слов разделенных пробелом, получить список слов длиннее 5 символов, и склеить их в одну строку с пробелом в качестве разделителя
     */
    public static void task4(String line) {
        var result =  Arrays
                .stream(line.split(" "))
                .filter(s -> s.length() > 5)
                .collect(Collectors.joining(" "));
        System.out.println(result);
    }

    /**
     * 5. Посчитать сумму четных чисел в пределах от 100 до 200 (включительно)
     */
    public static void task5() {
        var result = IntStream
                .range(100, 200 + 1)
                .filter(i -> i % 2 == 0)
                .sum();
        System.out.println("Сумма четных чисел в пределах от 100 до 200 (включительно) = " + result); // получилось 7650
    }

    /**
     * 6. Посчитать суммарную длину строк в одномерном массиве
     */
    public static void task6(String[] lines) {
        var result = Arrays
                .stream(lines)
                .mapToInt(String::length)
                .sum();
        System.out.println("Суммарная длина строк  = " + result);
    }

    /**
     * 7. Из массива слов получить первые три слова в алфавитном порядке
     */
    public static void task7(String[] words) {
        var firstWords = Arrays
                .stream(words)
                .sorted()
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Первые три слова в алфавитном порядке: " + String.join(" ", firstWords));
    }
}
