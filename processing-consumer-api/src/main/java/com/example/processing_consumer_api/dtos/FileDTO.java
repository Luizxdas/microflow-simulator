package com.example.processing_consumer_api.dtos;

import jakarta.validation.constraints.NotBlank;

public record FileDTO(@NotBlank String name, @NotBlank FileType type, @NotBlank String content) {
}
