package com.example.devforum.service;

import com.example.devforum.domain.Curso;
import com.example.devforum.domain.Topico;
import com.example.devforum.domain.Usuario;
import com.example.devforum.dto.topico.DadosAtualizacao;
import com.example.devforum.dto.topico.DadosListagemTopico;
import com.example.devforum.dto.topico.DadosTopicoCadastrado;
import com.example.devforum.dto.topico.DadosTopicoDetalhamento;
import com.example.devforum.repository.CursoRepository;
import com.example.devforum.repository.TopicoRepository;
import com.example.devforum.repository.UsuarioRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TopicoService {
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoService(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository,
                         CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    public DadosTopicoDetalhamento cadastrar(DadosTopicoCadastrado dados) {
        Curso curso = cursoRepository.findById(dados.idCurso()).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByEmail(email).get();

        if (topicoRepository.existsByTitulo(dados.titulo())) {
            throw new IllegalStateException("Já existe um tópico com esse título");
        }

        Topico topico = new Topico();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setAutor(usuario);
        topico.setCurso(curso);
        topico.setDataCriacao(LocalDateTime.now());
        topico.setStatus(true);
        topicoRepository.save(topico);

        return new DadosTopicoDetalhamento(topico.getId(), topico.getTitulo(),
                topico.getMensagem(), topico.getDataCriacao(),
                topico.getStatus(), curso.getNome(), curso.getCategoria());
    }

    public Page<DadosListagemTopico> listar(Pageable pageable) {
        return topicoRepository.findAllByStatusTrue(pageable)
                .map(DadosListagemTopico::new);
    }
    @Transactional
    public DadosTopicoDetalhamento detalhar(Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new DadosTopicoDetalhamento(topico);
    }
    @Transactional
    public DadosTopicoDetalhamento atualizar(DadosAtualizacao dados) {
        var usuario = usuarioLogado();
        Topico topico = topicoRepository.getReferenceById(dados.id());

        if (!topico.getAutor().getId().equals(usuario.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não tem permissão para alterar este tópico");
        }

        topico.atualizarInformacoes(dados);
        return new DadosTopicoDetalhamento(topico);
    }
    @Transactional
    public void deletar(Long id) {
        var usuario = usuarioLogado();
        Topico topico = topicoRepository.getReferenceById(id);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!topico.getAutor().getId().equals(usuario.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não pode excluir algo que não é seu");
        }
        if (!topico.getAutor().getEmail().equals(email)) {
            try {
                throw new AccessDeniedException("Você não pode apagar este tópico");
            } catch (AccessDeniedException e) {
                throw new RuntimeException(e);
            }
        }
        topico.excluir();
    }

    public Usuario usuarioLogado() {
        return (Usuario) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
