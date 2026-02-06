package com.example.devforum.dto.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosTopicoCadastrado(@NotBlank
                                    String titulo,
                                    @NotBlank
                                    String mensagem,
                                    @NotNull
                                    Long idCurso
) {
}
