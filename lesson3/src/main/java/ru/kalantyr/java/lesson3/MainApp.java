package ru.kalantyr.java.lesson3;

import java.util.*;

public class MainApp {

    /**
     * 1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа)
     */
    public static <T extends Object> void swap(T[] array, int i1, int i2)
            throws ArrayIndexOutOfBoundsException, IllegalArgumentException
    {
        if (array == null)
            throw new IllegalArgumentException("array");

        if (i1 == i2)
            return;

        var v1 = array[i1];
        array[i1] = array[i2];
        array[i2] = v1;
    }

    /**
     * 2. Написать метод, который преобразует массив в ArrayList
     */
    public static <T> ArrayList<T> arrayToList(T[] array) throws IllegalArgumentException {
        if (array == null)
            throw new IllegalArgumentException("array");

        var list = new ArrayList<T>(array.length);
        for (var i: array)
            list.add(i);
        return list;
    }

    public static void main(String[] args) {

        // 4. Создать массив с набором слов.
        // Найти и вывести список слов, из которых состоит массив.
        // Посчитать, сколько раз встречается каждое слово.
        String[] values = {
                "Яблоко", "Апельсин", "Груша", "Яблоко", "Яблоко", "Апельсин", "Банан", "Груша", "Яблоко", "Яблоко"
        };
        var map = countWords(values);
        for (var entry: map.entrySet())
            System.out.printf("%s (%d)%n", entry.getKey(), entry.getValue());

        // ---------- output ----------
        // Груша (2)
        // Яблоко (5)
        // Апельсин (2)
        // Банан (1)
    }

    /**
     * Подсчитывает слова
     */
    public static Map<String, Integer> countWords(String[] values) throws IllegalArgumentException {
        if (values == null)
            throw new IllegalArgumentException("array");

        var map = new HashMap<String, Integer>();
        for (var s : values)
            if (map.containsKey(s))
                map.put(s, map.get(s) + 1);
            else
                map.put(s, 1);
        return map;
    }
}
