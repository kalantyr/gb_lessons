package ru.kalantyr.orm;

import java.lang.annotation.*;

/**
 * Позволяет настроить сохранение поля в БД
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AppTable {
    /**
     * Название таблицы
     */
    String tableName();
}
