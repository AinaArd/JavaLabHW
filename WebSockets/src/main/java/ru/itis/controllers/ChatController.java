package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.dto.MessageDto;
import ru.itis.services.MessageService;


@Controller
public class ChatController {

    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public MessageDto sendMessage(@Payload MessageDto messageDto) {
        return messageService.addMessage(messageDto);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public MessageDto addUser(@Payload MessageDto messageDto,
                           SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", messageDto.getSender());
        return messageDto;
    }

    @GetMapping("/chat")
    public String getChatPage() {
        return "chat";
    }

//    @GetMapping("/chat/{desk-id}")
//    public String getChatPage(Authentication authentication, ModelMap model, @PathVariable(name = "desk-id") Long deskId) {
//        Desk desk = deskService.findOneDesk(deskId).orElseThrow(IllegalArgumentException::new);
//        User currentUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
//        List<Message> messages = messageService.getDeskMessages(desk);
//
//        model.addAttribute("messages", messages);
//        model.addAttribute("currentUser", currentUser);
//        model.addAttribute("desk", desk);
//        return "chat";
//    }
}

