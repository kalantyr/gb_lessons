package ru.kalantyr.lesson11.repositories;

import ru.kalantyr.lesson11.entitites.University;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
//    @EntityGraph(attributePaths = {"groups"})
//    @Query("SELECT u FROM University u WHERE u.id = :id")
//    University findUniversityById(Long id);

    @EntityGraph(value = "University.with-groups-and-students")
    University findByTitle(String title);
}
