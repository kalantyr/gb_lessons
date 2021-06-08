package ru.kalantyr.orm;

public interface IRepository<T> {
    /**
     * Сохранить объект в БД
     */
    void add(T object);

    /**
     * Достать объект по ID
     */
    T get(long id);
}
