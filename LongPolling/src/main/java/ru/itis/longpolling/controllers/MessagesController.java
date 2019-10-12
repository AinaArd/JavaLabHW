package ru.itis.longpolling.controllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.itis.longpolling.models.User;
import org.springframework.web.bind.annotation.*;
import ru.itis.longpolling.dto.MessageDto;
import ru.itis.longpolling.models.Message;
import ru.itis.longpolling.services.MessageService;

import java.util.List;

@RestController
public class MessagesController {

    @Autowired
    private MessageService messageService;

    @PostMapping(value = "/messages")
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageDto messageDto) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Message message = Message.builder()
                .text(messageDto.getText())
                .author(currentUser)
                .build();
        messageService.addMessage(message);
//        for (Message singleMessage : messageService.getAllMessages()) {
//            synchronized (messageService.getAllMessagesDto()) {
//                messageService.addMessage(singleMessage);
//                messageService.getAllMessagesDto().notifyAll();
//            }
//        }

        return ResponseEntity.ok(getMessages());
    }

    @SneakyThrows
    @GetMapping(value = "/messages")
    public ResponseEntity<List<MessageDto>> getMessages() {
//        synchronized (messageService.getAllMessagesDto()) {
//            if (messageService.getAllMessagesDto().isEmpty()) {
//                messageService.getAllMessagesDto().wait();
//            }
//           again take from db
//            List<MessageDto> response = messageService.getAllMessagesDto();
//            return ResponseEntity.ok().body(response);
        List<MessageDto> messages = messageService.getAllMessagesDto();
        return ResponseEntity.ok().body(messages);
    }
}