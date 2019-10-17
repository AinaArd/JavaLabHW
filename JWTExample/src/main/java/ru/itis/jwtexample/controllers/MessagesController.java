package ru.itis.jwtexample.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.jwtexample.dto.MessageDto;
import ru.itis.jwtexample.services.MessageService;

import java.util.List;

@RestController
public class MessagesController {

    private final MessageDto message = new MessageDto();

    @Autowired
    private MessageService messageService;

    @ApiOperation("Get message")
    @GetMapping("/messages")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<MessageDto>> getAllMessage(@RequestHeader("Authorization") String auth) throws InterruptedException {

        if (message.getText().isEmpty()) {
            message.wait();
        }
        message.clear();
        return ResponseEntity.ok().body(messageService.getAllMessagesDto());

    }


    @ApiOperation("Add new message")
    @PostMapping("/messages")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> addMessage(@RequestHeader("Authorization") String auth, @RequestBody MessageDto messageForm) {
        synchronized (message) {
            message.setAuthor(messageForm.getAuthor());
            message.setText(messageForm.getText());
            messageService.addMessage(message);
            message.notifyAll();
        }

        return ResponseEntity.ok().build();

    }
}