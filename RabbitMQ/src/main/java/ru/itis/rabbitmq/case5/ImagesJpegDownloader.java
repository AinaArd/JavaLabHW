package ru.itis.rabbitmq.case5;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class ImagesJpegDownloader {

    private final static String JPG_ROUTING_KEY = "files.images.jpg";
    private final static String FILES_EXCHANGE = "files_topic_exchange";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, FILES_EXCHANGE, JPG_ROUTING_KEY);
            channel.basicConsume(queueName, false, (consumerTag, message) -> {
                String imageUrl = new String(message.getBody());
                System.out.println("Start  load image " + imageUrl);
                URL url = new URL(imageUrl);
                String fileName = url.getFile();
                try {
                    BufferedImage image = ImageIO.read(url);
                    File output = new File("downloaded/images/" + UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf(".")));
                    ImageIO.write(image, "jpg", output);
                    System.out.println("Finish load image " + imageUrl);
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (IOException e) {
                    System.err.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }

            }, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}