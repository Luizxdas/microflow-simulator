package com.example.processing_consumer_api.services;

import com.example.processing_consumer_api.dtos.FileDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {

    @Value("${APP_TEXT_SLEEP_TIME_MS:100}")
    private Long textSleepTimeMS;

    @Value("${APP_IMAGE_SLEEP_TIME_MS:100}")
    private Long imageSleepTimeMS;

    @RabbitListener(queues = "queue.text")
    public void processText(FileDTO file) throws InterruptedException {
        Thread.sleep(textSleepTimeMS);
    }

    @RabbitListener(queues = "queue.image")
    public void processImage(FileDTO file) throws InterruptedException {
        Thread.sleep(imageSleepTimeMS);
    }
}
