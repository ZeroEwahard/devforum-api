package com.example.devforum.controller;

import com.example.devforum.domain.Curso;
import com.example.devforum.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoRepository repository;

    public CursoController(CursoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity cadastrarCurso(@RequestBody @Valid Curso curso) {
        return  ResponseEntity.ok(repository.save(curso));
    }
}
