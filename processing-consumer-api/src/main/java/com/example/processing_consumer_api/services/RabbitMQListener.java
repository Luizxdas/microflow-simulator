package com.example.processing_consumer_api.services;

import com.example.dtos.FileDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {

    @RabbitListener(queues = "queue.text")
    public void processText(FileDTO file) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Finished: " + file.name());
    }

    @RabbitListener(queues = "queue.image")
    public void processImage(FileDTO file) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Finished: " + file.name());
    }
}
