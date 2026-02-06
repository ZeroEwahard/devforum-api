package com.example.devforum.dto.topico;

import com.example.devforum.domain.Curso;
import com.example.devforum.domain.Topico;
import com.example.devforum.domain.Usuario;
import com.example.devforum.dto.curso.Categoria;
import com.example.devforum.dto.resposta.DadosResposta;

import java.time.LocalDateTime;
import java.util.List;

public record DadosTopicoDetalhamento(Long id, String titulo, String mensagem, LocalDateTime dataCriacao, Boolean status
                                     ,String nomeCurso, Categoria categoriaCurso) {
    public DadosTopicoDetalhamento(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getCurso().getNome(), topico.getCurso().getCategoria());
    }
}
