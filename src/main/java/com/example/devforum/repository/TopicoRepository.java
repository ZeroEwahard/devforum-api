package com.example.devforum.repository;

import com.example.devforum.domain.Topico;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Boolean existsByTitulo(@NotBlank String titulo);

    Page<Topico> findAllByStatusTrue(Pageable pageable);
}
