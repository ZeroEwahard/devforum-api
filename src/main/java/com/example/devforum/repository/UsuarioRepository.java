package com.example.devforum.repository;

import com.example.devforum.domain.Usuario;
import jakarta.validation.constraints.Email;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(@NonNull String email);

    boolean existsByEmail(@Email String email);
}
