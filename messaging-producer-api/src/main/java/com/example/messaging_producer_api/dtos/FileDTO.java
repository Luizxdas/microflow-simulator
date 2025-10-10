package com.example.messaging_producer_api.dtos;

import jakarta.validation.constraints.NotBlank;

public record FileDTO(@NotBlank String type, @NotBlank String content) {
}
