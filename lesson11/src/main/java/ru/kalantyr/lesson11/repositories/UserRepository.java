package ru.kalantyr.lesson11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kalantyr.lesson11.entitites.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
