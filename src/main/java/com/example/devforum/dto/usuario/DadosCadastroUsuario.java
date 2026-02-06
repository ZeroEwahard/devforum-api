package com.example.devforum.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(@NotBlank
                                   String nome,
                                   @Email
                                   String email,
                                   @NotBlank
                                   String senha) {
}
