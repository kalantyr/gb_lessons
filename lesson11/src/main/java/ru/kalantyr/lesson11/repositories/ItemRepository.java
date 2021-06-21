package ru.kalantyr.lesson11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kalantyr.lesson11.entitites.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
