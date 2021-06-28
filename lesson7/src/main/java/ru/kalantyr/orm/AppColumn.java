package ru.kalantyr.orm;

import java.lang.annotation.*;

/**
 * Позволяет настроить сохранение DTO-класса в БД
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AppColumn {
    /**
     * Название столбца
     */
    String columnName();
}
