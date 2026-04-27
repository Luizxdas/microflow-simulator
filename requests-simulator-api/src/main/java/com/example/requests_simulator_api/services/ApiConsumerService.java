package com.example.requests_simulator_api.services;
import com.example.requests_simulator_api.dtos.FileDTO;
import com.example.requests_simulator_api.dtos.FileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ApiConsumerService {

    private static final Logger log = LoggerFactory.getLogger(ApiConsumerService.class);

    private final WebClient webClient;

    public ApiConsumerService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void sendImageRequest() {
        webClient.post()
                .uri("/files/send")
                .bodyValue(new FileDTO("ImageOne", FileType.IMAGE, "Image of a dog"))
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe(
                        success -> {},
                        error -> log.error("Error sending image request: {}", error.getMessage())
                );
    }

    public void sendTextRequest() {
        webClient.post()
                .uri("/files/send")
                .bodyValue(new FileDTO("TextOne", FileType.TEXT, "Hello World!"))
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe(
                        success -> {},
                        error -> log.error("Error sending text request: {}", error.getMessage())
                );
    }
}