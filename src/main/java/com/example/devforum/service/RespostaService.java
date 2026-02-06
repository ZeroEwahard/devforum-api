package com.example.devforum.service;

import com.example.devforum.domain.Resposta;
import com.example.devforum.domain.Topico;
import com.example.devforum.domain.Usuario;
import com.example.devforum.dto.resposta.DadosResposta;
import com.example.devforum.dto.resposta.DadosRespostaCadastro;
import com.example.devforum.repository.CursoRepository;
import com.example.devforum.repository.RespostaRepository;
import com.example.devforum.repository.TopicoRepository;
import com.example.devforum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RespostaService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RespostaRepository repostaRepository;

    public RespostaService(RespostaRepository repostaRepository, TopicoRepository topicoRepository,
                           UsuarioRepository usuarioRepository) {
        this.repostaRepository = repostaRepository;
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public DadosResposta cadastrar(DadosRespostaCadastro dados) {
        Topico topico = topicoRepository.findById(dados.idTopico()).orElseThrow(() -> new
                IllegalArgumentException("Tópico não encontrado"));
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario autor = usuarioRepository.findByEmail(email).orElseThrow(() -> new
                IllegalArgumentException("Autor não encontrado"));

        boolean duplicada = topico.getRespostas().stream()
                .anyMatch(r -> r.getMensagem().equalsIgnoreCase(dados.mensagem()));
        if (duplicada) {
            throw new IllegalStateException("Não é permitido resposta duplicada");
        }

        Resposta resposta = new Resposta();
        resposta.setMensagem(dados.mensagem());
        resposta.setTopico(topico);
        resposta.setAutor(autor);
        resposta.setDataCriacao(LocalDateTime.now());
        topico.getRespostas().forEach(r -> r.setSolucao(false));
        resposta.setSolucao(true);
        repostaRepository.save(resposta);

        return new DadosResposta(
                resposta.getId(),
                resposta.getMensagem(),
                resposta.getDataCriacao(),
                resposta.getSolucao());
    }
}
