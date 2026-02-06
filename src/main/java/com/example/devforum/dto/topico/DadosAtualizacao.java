package com.example.devforum.dto.topico;

import com.example.devforum.dto.resposta.DadosResposta;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacao(@NotNull
                               Long id,
                               String titulo,
                               String mensagem) {

}
