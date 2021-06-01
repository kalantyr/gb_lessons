package ru.kalantyr.orm;

import java.sql.SQLException;

public class DataSet<T> {
    private final DataContext dataContext;
    private final Class<T> dataClass;

    public DataSet(DataContext dataContext, Class<T> dataClass) {
        this.dataClass = dataClass;
        if (dataContext == null)
            throw new IllegalArgumentException();

        this.dataContext = dataContext;
    }

    /**
     * Создаёт таблицу в БД
     */
    public void create() throws SQLException {
        create(false); // неудобно, что нельзя просто объявить значение параметра по умолчанию (прямо в сигнатуре), приходится перегрузки делать
    }

    /**
     * Создаёт таблицу в БД
     * @param throwIfExists генерировать исключение, если таблица уже существует
     */
    public void create(boolean throwIfExists) throws SQLException {
        var sql = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Name TEXT NOT NULL);", dataClass.getSimpleName());
        dataContext.execute(sql);
    }

    /**
     * Добавляет запись в таблицу
     */
    public void insert(T item) {
        if (item == null)
            throw new IllegalArgumentException();
    }
}
