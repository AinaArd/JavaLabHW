package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.dto.MessageDto;
import ru.itis.models.Message;
import ru.itis.models.MessageType;
import ru.itis.models.User;
import ru.itis.services.MessageService;
import ru.itis.services.UserService;

import static java.lang.String.format;

@Controller
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @MessageMapping("/chat/{chatId}/sendMessage")
    @SendTo("/topic/{chatId}")
    public MessageDto sendMessage(@DestinationVariable String chatId, @Payload MessageDto messageDto) {
        String path = "/topic/" + chatId;
        messagingTemplate.convertAndSend(path, messageDto);
        return messageService.addMessage(messageDto, chatId);
    }

    @MessageMapping("/chat/{chatId}/addUser")
    @SendTo("/topic/{chatId}")
    public MessageDto addUser(@DestinationVariable String chatId, @Payload MessageDto messageDto,
                              SimpMessageHeaderAccessor headerAccessor) {
        String currentRoomId = (String) headerAccessor.getSessionAttributes().put("chatId", chatId);
        User userSender = userService.getUserByName(messageDto.getSender()).orElseThrow(IllegalArgumentException::new);
        if (currentRoomId != null) {
            Message leaveMessage = Message.builder()
                    .type(MessageType.LEAVE)
                    .sender(userSender)
                    .content(messageDto.getContent())
                    .build();
            messagingTemplate.convertAndSend(format("/topic/%s", currentRoomId), leaveMessage);
        }

        if (userService.login(messageDto)) {
            headerAccessor.getSessionAttributes().put("username", messageDto.getSender());
        } else {
            headerAccessor.getSessionAttributes().put("username", messageDto.getSender());
        }
        messageDto.setType(MessageType.JOIN.toString());
        messagingTemplate.convertAndSend(format("/topic/%s", chatId), messageDto);
        return messageDto;
    }

    @GetMapping(value = "/chat/{chatId}")
    public String getOneChatPage(@PathVariable("chatId") String chatId, ModelMap modelMap) {
        modelMap.addAttribute("messages", messageService.findAllByChat(chatId));
        return "chat";
    }

    @GetMapping("/start")
    public String getStartPage() {
        return "start";
    }
}

