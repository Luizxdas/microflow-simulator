package com.example.requests_simulator_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FileDTO(@NotBlank String name, @NotNull FileType type, @NotBlank String content) {
}
