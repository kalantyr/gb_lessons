package ru.kalantyr.lesson11.repositories;

import ru.kalantyr.lesson11.dto.ProductDtoClassProjection;
import ru.kalantyr.lesson11.dto.ProductDtoInterfaceProjection;
import ru.kalantyr.lesson11.dto.ProductSimpleDto;
import ru.kalantyr.lesson11.entitites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<ProductDtoInterfaceProjection> findAllBy();

    List<ProductDtoClassProjection> findAllByPriceGreaterThan(int minPrice);

//    @Query("select p.id as id, p.title as title, p.price as anotherPriceField from Product p")
    @Query("select new ru.kalantyr.lesson11.dto.ProductSimpleDto(p) from Product p")
    List<ProductSimpleDto> findAllProductsWithManualRequest();

    Product namedFindByTitle(String title);
}
