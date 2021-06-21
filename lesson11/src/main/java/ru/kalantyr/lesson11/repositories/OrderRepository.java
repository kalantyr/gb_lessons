package ru.kalantyr.lesson11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kalantyr.lesson11.entitites.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
