package ru.kalantyr.lesson11.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kalantyr.lesson11.entitites.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(value = "Order.with-items")
    Optional<Order> findById(Long id);

    @EntityGraph(value = "Order.with-items")
    List<Order> findAll();

    @EntityGraph(value = "Order.with-items")
    List<Order> findAllByUserId(long userId);

    @Query("SELECT i.order.userId FROM OrderItem i WHERE i.itemId = :itemId")
    List<Long> findUsersByItemIdForLesson(long itemId);
}
