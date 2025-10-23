package com.example.messaging_producer_api.controllers;

import com.example.dtos.FileDTO;
import com.example.messaging_producer_api.services.RabbitService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class FilesController {

    private final RabbitService rabbitService;

    public FilesController(RabbitService rabbitService) {
        this.rabbitService = rabbitService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendToProcessing(@Valid @RequestBody FileDTO file) {
        rabbitService.sendToQueue(file);
        return ResponseEntity.ok("File sent to processing!");
    }
}
