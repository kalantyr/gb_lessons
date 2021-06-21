package ru.kalantyr.data.entitites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
@NamedQueries(
        @NamedQuery(name = "Product.namedFindByTitle", query = "select p from Product p where p.title = ?1")
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;
}
