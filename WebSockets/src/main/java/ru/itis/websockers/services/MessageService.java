package ru.itis.websockers.services;

import ru.itis.websockers.dto.MessageDto;
import ru.itis.websockers.models.Message;

import java.util.List;

public interface MessageService {
    void addMessage(Message message);

    List<MessageDto> getAllMessagesDto();
    List<Message> getAllMessages();
}
