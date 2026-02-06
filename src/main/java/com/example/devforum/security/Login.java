package com.example.devforum.security;

import jakarta.validation.constraints.NotBlank;

public record Login(@NotBlank String email,
                    @NotBlank String senha) {
}
