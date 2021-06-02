package ru.kalantyr.orm;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AppColumn {
    /**
     * Название столбца
     */
    String columnName();
}
