package com.example.requests_simulator_api.services;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ApiCallerScheduler {

    private final ApiConsumerService service;

    public ApiCallerScheduler(ApiConsumerService service) {
        this.service = service;
    }

    @Scheduled(fixedRate = 15000)
    public void sendBatch() {
        for (int i = 0; i < 5; i++) {
            service.sendImageRequest();
            service.sendTextRequest();
        }
    }
}