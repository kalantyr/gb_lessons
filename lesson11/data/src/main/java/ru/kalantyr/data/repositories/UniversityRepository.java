package ru.kalantyr.data.repositories;

import ru.kalantyr.data.dto.ProductDtoClassProjection;
import ru.kalantyr.data.dto.ProductDtoInterfaceProjection;
import ru.kalantyr.data.dto.ProductSimpleDto;
import ru.kalantyr.data.entitites.Product;
import ru.kalantyr.data.entitites.University;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedEntityGraph;
import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
//    @EntityGraph(attributePaths = {"groups"})
//    @Query("SELECT u FROM University u WHERE u.id = :id")
//    University findUniversityById(Long id);

    @EntityGraph(value = "University.with-groups-and-students")
    University findByTitle(String title);
}
