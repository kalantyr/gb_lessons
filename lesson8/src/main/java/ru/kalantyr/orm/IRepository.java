package ru.kalantyr.orm;

import java.util.List;

// вроде говорилось, что интерфейсы надо называть по шалбону "...able", но пока назову по "I..." (так легче различать классы и интерфейсы в коде)
public interface IRepository<T> {
    /**
     * Сохранить объект в БД
     */
    void add(T object);

    /**
     * Достать объект по ID
     */
    T get(long id);

    /**
     * Ищет объекты по всем текстовым полям
     */
    Iterable<T> fullTextSearch(String text, SearchMode mode);
}

