package com.example.devforum.dto.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


public record DadosResposta(
                            Long id,
                            String mensagem,
                            LocalDateTime dataCriacao,
                            Boolean solucao) {
}
