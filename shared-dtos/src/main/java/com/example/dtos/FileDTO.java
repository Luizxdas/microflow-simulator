package com.example.dtos;

import jakarta.validation.constraints.NotBlank;

public record FileDTO(@NotBlank String name, @NotBlank String type, @NotBlank String content) {
}
