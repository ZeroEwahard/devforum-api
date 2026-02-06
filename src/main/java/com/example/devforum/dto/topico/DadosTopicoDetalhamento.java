package com.example.devforum.dto.topico;

import com.example.devforum.domain.Topico;
import com.example.devforum.dto.curso.Categoria;

import java.time.LocalDateTime;

public record DadosTopicoDetalhamento(Long id, String titulo, String mensagem, LocalDateTime dataCriacao, Boolean status
        , String nomeCurso, Categoria categoriaCurso) {
    public DadosTopicoDetalhamento(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getCurso().getNome(), topico.getCurso().getCategoria());
    }
}
