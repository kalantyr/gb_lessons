package ru.kalantyr.java.lesson3;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private final List<T> list = new ArrayList<>();

    /**
     * Вычисляет вес коробки
     */
    public float getWeight() {
        return list
                .stream()
                .map(Fruit::getWeight)
                .reduce(0f, Float::sum);
    }

    /**
     * Сравнивает с другой коробкой
     * @return  true, если массы равны
     */
    public boolean compare(Box<? extends Fruit> otherBox)  throws IllegalArgumentException {
        if (otherBox == null)
            throw new IllegalArgumentException("fruit is null");
        return Float.compare(getWeight(), otherBox.getWeight()) == 0;
    }

    /**
     * Перекладывает все предметы в другую коробку
     */
    public void transferTo(Box<T> otherBox) throws IllegalArgumentException {
        if (otherBox == null)
            throw new IllegalArgumentException();

        otherBox.list.addAll(list);
        list.clear();
    }

    /**
     * Добавить фрукт в коробку
     */
    public void add(T fruit) throws IllegalArgumentException {
        if (fruit == null)
            throw new IllegalArgumentException("fruit is null");

        if (list.contains(fruit))
            throw new IllegalArgumentException("fruit has already been added");

        list.add(fruit);
    }

    /**
     * Коробка пуста?
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
