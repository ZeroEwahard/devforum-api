package com.example.devforum.controller;

import com.example.devforum.dto.topico.DadosAtualizacao;
import com.example.devforum.dto.topico.DadosListagemTopico;
import com.example.devforum.dto.topico.DadosTopicoCadastrado;
import com.example.devforum.dto.topico.DadosTopicoDetalhamento;
import com.example.devforum.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoService repositorioService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosTopicoCadastrado dados, UriComponentsBuilder uriComponentsBuilder) {
        DadosTopicoDetalhamento topico = repositorioService.cadastrar(dados);
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.id()).toUri();
        return ResponseEntity.created(uri).body(topico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(Pageable pageable) {
        return ResponseEntity.ok(repositorioService.listar(pageable));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacao dados) {
        return ResponseEntity.ok(repositorioService.atualizar(dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        repositorioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(repositorioService.detalhar(id));
    }


}
