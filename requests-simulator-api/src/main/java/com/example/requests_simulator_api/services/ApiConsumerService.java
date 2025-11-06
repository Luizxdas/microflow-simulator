package com.example.requests_simulator_api.services;
import com.example.dtos.FileDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ApiConsumerService {

    private final WebClient webClient;

    public ApiConsumerService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void sendImageRequest() {
        webClient.post()
                .uri("/files/send")
                .bodyValue(new FileDTO("ImageOne", "image", "Image of a dog"))
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }

    public void sendTextRequest() {
        webClient.post()
                .uri("/files/send")
                .bodyValue(new FileDTO("TextOne", "text", "Hello World!"))
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }
}