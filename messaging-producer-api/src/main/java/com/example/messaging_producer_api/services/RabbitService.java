package com.example.messaging_producer_api.services;

import com.example.messaging_producer_api.dtos.FileDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {
    private final RabbitTemplate rabbitTemplate;

    public RabbitService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToQueue(FileDTO file) {
        String routingKey = switch (file.type()) {
            case "text" -> "file.text";
            case "image" -> "file.image";
            default -> "file.unknown";
        };

        rabbitTemplate.convertAndSend(routingKey, file);
    }
}
