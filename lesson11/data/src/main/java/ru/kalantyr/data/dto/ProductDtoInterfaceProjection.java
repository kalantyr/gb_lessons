package ru.kalantyr.data.dto;


import org.springframework.beans.factory.annotation.Value;

public interface ProductDtoInterfaceProjection {
    String getTitle();
    int getPrice();

    @Value("#{target.title + ' ' + target.title}")
    String getDoubleTitle();
}
