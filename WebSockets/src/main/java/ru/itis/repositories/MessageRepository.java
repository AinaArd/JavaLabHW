package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.models.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(nativeQuery = true, value = "select * from message where chat_id = ?" +
            "")
    List<Message> findAllByChatId(String chatId);
}

