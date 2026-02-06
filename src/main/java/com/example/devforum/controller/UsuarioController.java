package com.example.devforum.controller;

import com.example.devforum.domain.Perfil;
import com.example.devforum.domain.Usuario;
import com.example.devforum.dto.usuario.DadosCadastroUsuario;
import com.example.devforum.repository.UsuarioRepository;
import io.jsonwebtoken.Jwt;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }

    @PostMapping
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuario dados){
        if (usuarioRepository.existsByEmail(dados.email()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        Usuario usuario = new Usuario();
        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        usuario.setSenha(encoder.encode(dados.senha()));
        usuario.setPerfil(new Perfil("USER"));

        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
