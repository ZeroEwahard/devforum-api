package com.example.devforum.controller;

import com.example.devforum.dto.resposta.DadosResposta;
import com.example.devforum.dto.resposta.DadosRespostaCadastro;
import com.example.devforum.service.RespostaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("respostas")
public class RespostaController {

    private final RespostaService service;

    public RespostaController(RespostaService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosResposta> cadastrar(@RequestBody @Valid DadosRespostaCadastro dados) {
        DadosResposta resposta = service.cadastrar(dados);
        return ResponseEntity.ok(resposta);
    }
}
