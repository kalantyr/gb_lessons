package ru.kalantyr.orm;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        create(false); // почему бы просто не объявить значение параметра по умолчанию (прямо в сигнатуре)
    }

    /**
     * Создаёт таблицу в БД
     * @param throwIfExists генерировать исключение, если таблица уже существует
     */
    public void create(boolean throwIfExists) throws SQLException {
        var tableName = getTableName();

        if (throwIfExists)
            if (tableExists())
                throw new RuntimeException(String.format("Таблица %s уже существует", tableName));

        var columns = getFieldsForStore()
                .map(DataSet::fieldToColumn)
                .collect(Collectors.joining(", "));

        var sql = String.format("CREATE TABLE %s (%s);", tableName, columns);
        dataContext.execute(sql);
    }

    /**
     * Возвращает поля, которые имеет смысл сохранять в БД
     */
    private Stream<Field> getFieldsForStore() {
        // в рабочем варианте такое надо кэшировать, конечно
        return Arrays
                .stream(dataClass.getFields())
                .filter(f -> !Modifier.isFinal(f.getModifiers()))
                .filter(f -> Modifier.isPublic(f.getModifiers()));
    }

    /**
     * Возвращает название таблицы в БД
     */
    public String getTableName() {
        var tableNameAnno = dataClass.getAnnotation(AppTable.class);
        if (tableNameAnno != null)
            return tableNameAnno.tableName();
        else
            return dataClass.getSimpleName(); // если аннотации нет, берём просто название класса
    }

    private static String fieldToColumn(Field field) {
        var name = getColumnName(field);

        var type = "";
        if (field.getType() == String.class)
            type = "TEXT";
        if (field.getType() == int.class || field.getType() == Integer.class)
            type = "INTEGER";
        if (type.isEmpty())
            throw new RuntimeException("Не удалось определить тип поля для " + name);

        return name + " " + type;
    }

    /**
     * Определяет название столбца в БД
     */
    private static String getColumnName(Field field) {
        var columnNameAnno = field.getAnnotation(AppColumn.class);
        if (columnNameAnno != null)
            return columnNameAnno.columnName();
        else
            return field.getName();
    }

    /**
     * Есть ли уже таблица в БД
     */
    public boolean tableExists() throws SQLException {
        var sql = String.format("SELECT name FROM sqlite_master WHERE type='table' AND name='%s';", getTableName());

        boolean[] exists = new boolean[1]; // костыльный костылище - заводится массив, чтобы обмануть компилятор
        exists[0] = false;
        dataContext.executeQuery(sql, resultSet -> {
            try {
                while (resultSet.next()) {
                    exists[0] = true; // нельзя просто установить значение boolean-переменной, так как замыкание работает только на чтение
                    break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return exists[0];
    }

    /**
     * Добавляет запись в таблицу
     */
    public void insert(T item) throws SQLException {
        if (item == null)
            throw new IllegalArgumentException();

        var tableName = getTableName();

        var names = getFieldsForStore()
                .map(DataSet::getColumnName)
                .collect(Collectors.joining(", "));
        var values = getFieldsForStore()
                .map(f -> getStringValue(f, item))
                .collect(Collectors.joining(", "));

        var command = String.format("INSERT INTO %s (%s) VALUES (%s);", tableName, names, values);
        dataContext.execute(command);

    }

    /**
     * Добавляет записи в таблицу
     */
    public void insert(Iterable<T> items) throws SQLException {
        if (items == null)
            throw new IllegalArgumentException();

        var tableName = getTableName();

        var names = getFieldsForStore()
                .map(DataSet::getColumnName)
                .collect(Collectors.joining(", "));

        var commands = new ArrayList<String>();
        for (var i: items) {
            var values = getFieldsForStore()
                    .map(f -> getStringValue(f, i))
                    .collect(Collectors.joining(", "));

            commands.add(String.format("INSERT INTO %s (%s) VALUES (%s);", tableName, names, values));
        }
        dataContext.executeInTransation(commands);
    }

    private String getStringValue(Field f, T item) {
        var value = "";
        try {
            var v = f.get(item);
            if (v != null)
                value = v.toString();
            if (f.getType() == String.class)
                value = String.format("\"%s\"", value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e); // чтобы можно было использовать в лямбдах
        }
        return value;
    }
}
