package ru.kalantyr.data.controllers;

import ru.kalantyr.data.dto.ProductDtoClassProjection;
import ru.kalantyr.data.dto.ProductDtoInterfaceProjection;
import ru.kalantyr.data.entitites.Product;
import ru.kalantyr.data.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping("/interface_projections")
    public List<ProductDtoInterfaceProjection> interDtoDemo() {
        return productRepository.findAllBy();
    }

    @GetMapping("/class_projections")
    public List<ProductDtoClassProjection> classDtoDemo() {
        return productRepository.findAllByPriceGreaterThan(0);
    }

//    @GetMapping("/native_sql")
//    public List<Product> nativeSqlDemo() {
//        return productRepository.findAllByNativeSql();
//    }

//    @PostMapping("/save")
//    public Product
}
