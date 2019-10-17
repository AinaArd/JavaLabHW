package ru.itis.longpolling.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.longpolling.dto.MessageDto;
import ru.itis.longpolling.models.Message;
import ru.itis.longpolling.repositories.MessageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;


    @Override
    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<MessageDto> getAllMessagesDto() {
        return getAllMessages().stream().map(temp -> MessageDto.builder()
                .text(temp.getText())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
