package com.example.devforum.dto.topico;

import com.example.devforum.domain.Curso;
import com.example.devforum.domain.Topico;

import java.time.LocalDateTime;

public record DadosListagemTopico(Long id, String titulo, String mensagem, String curso, LocalDateTime dataCriacao, Boolean status, String autor) {

    public DadosListagemTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getCurso().getNome(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor().getNome());
    }
}
