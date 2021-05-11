package ru.kalantyr.java.lesson1;

import ru.kalantyr.java.lesson1.moving.Transport;

public class Person {
    private String _name;

    public Person(String name){
        _name = name;
    }

    public void start(Transport transport){
        System.out.println(String.format("%s начал(а) передвигаться на транспорте \"%s\"", _name, transport.getName()));
    }

    public void stop(Transport transport){
        System.out.println(String.format("%s остановил(а) транспорт \"%s\"", _name, transport.getName()));
    }
}
