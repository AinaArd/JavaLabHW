package ru.itis.websockers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.websockers.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
