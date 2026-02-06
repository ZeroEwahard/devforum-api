package com.example.devforum.dto.topico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacao(@NotNull
                               Long id,
                               String titulo,
                               String mensagem) {

}
