package com.example.devforum.dto.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DadosRespostaCadastro(@NotBlank String mensagem,
                                    @NotNull Long idTopico) {
}
