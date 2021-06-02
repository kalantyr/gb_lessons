package ru.kalantyr.orm;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

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
        var tableName = dataClass.getSimpleName(); // потенциально может быть два одинаково названных класса из разных пакетов, но для обучения и так сойдёт

        if (throwIfExists)
            if (tableExists())
                throw new RuntimeException("Таблица уже существует");

        var columns = Arrays
                .stream(dataClass.getMethods())
                .filter(m -> m.getName().startsWith("get"))
                .map(DataSet::methodToColumn)
                .collect(Collectors.joining(", "));

        var sql = String.format("CREATE TABLE %s (%s);", tableName, columns);
        dataContext.execute(sql);
    }

    private static String methodToColumn(Method method) {
        var type = "";
        switch (method.getReturnType().getSimpleName()) {
            case "String":
                type = "TEXT";
                break;
            case "int":
                type = "INTEGER";
                break;
        }
        var name = method.getName().substring(3);
        return name + " " + type;
    }

    /**
     * Есть ли уже таблица в БД
     */
    public boolean tableExists() {
        throw new UnsupportedOperationException();
    }

    /**
     * Добавляет запись в таблицу
     */
    public void insert(T item) {
        if (item == null)
            throw new IllegalArgumentException();
    }
}
