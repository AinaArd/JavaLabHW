package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.MessageDto;
import ru.itis.models.Message;
import ru.itis.models.MessageType;
import ru.itis.models.User;
import ru.itis.repositories.MessageRepository;

import java.util.List;

import static ru.itis.dto.MessageDto.from;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    public MessageDto addMessage(MessageDto messageDto, String chatId) {
        User sender = userService.getUserByName(messageDto.getSender()).get();
        Message message = Message.builder()
                .content(messageDto.getContent())
                .sender(sender)
                .chatId(chatId)
                .build();
        MessageDto newMessage = from(messageRepository.save(message));
        newMessage.setType(MessageType.CHAT.toString());
        return newMessage;
    }

    public List<Message> findAllByChat(String chatId) {
        return messageRepository.findAllByChatId(chatId);
    }
}
