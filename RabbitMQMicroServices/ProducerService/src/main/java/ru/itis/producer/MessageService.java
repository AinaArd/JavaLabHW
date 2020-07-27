package ru.itis.rabbit_spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MessageService {
    private final RabbitTemplate rabbitTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public MessageService(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Scheduled(fixedDelay = 3000L)
    public void sendOrder() throws Exception {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        CustomMessage customMessage = new CustomMessage("hello", timestamp, "Aina");
        String orderJson = objectMapper.writeValueAsString(customMessage);
        Message message = MessageBuilder
                .withBody(orderJson.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();

        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_MESSAGES_QUEUE, message);
    }
}