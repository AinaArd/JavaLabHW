package ru.itis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@SpringBootApplication
public class WebSocketsApp {


    public static void main(String[] args) {
        SpringApplication.run(WebSocketsApp.class, args);
    }
}
