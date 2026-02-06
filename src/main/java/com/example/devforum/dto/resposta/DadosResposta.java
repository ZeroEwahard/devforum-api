package com.example.devforum.dto.resposta;

import java.time.LocalDateTime;

public record DadosResposta(
        Long id,
        String mensagem,
        LocalDateTime dataCriacao,
        Boolean solucao) {
}
