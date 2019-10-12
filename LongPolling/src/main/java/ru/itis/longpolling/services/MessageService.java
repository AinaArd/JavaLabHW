package ru.itis.longpolling.services;

import ru.itis.longpolling.dto.MessageDto;
import ru.itis.longpolling.models.Message;

import java.util.List;

public interface MessageService {
    void addMessage(Message message);

    List<MessageDto> getAllMessagesDto();
    List<Message> getAllMessages();
}
