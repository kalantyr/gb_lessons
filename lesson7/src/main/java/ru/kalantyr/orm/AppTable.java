package ru.kalantyr.orm;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AppTable {
    /**
     * Название таблицы
     */
    String tableName();
}
