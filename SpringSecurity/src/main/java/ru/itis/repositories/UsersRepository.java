package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findById(Long id);
    List<User> findByName(String name);
    <S extends User> S save(S s);

    List<User> findByLogin(String login);

    Optional<User> findOneByLogin(String login);
}
