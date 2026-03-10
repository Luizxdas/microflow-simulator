package com.example.requests_simulator_api.services;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ApiCallerScheduler {

    private final ApiConsumerService service;

    public ApiCallerScheduler(ApiConsumerService service) {
        this.service = service;
    }

    @Scheduled(fixedDelayString = "${APP_REQUEST_DELAY_MS:200}")
    public void sendBatch() {
        service.sendImageRequest();
        service.sendTextRequest();
    }
}