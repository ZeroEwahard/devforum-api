package com.example.devforum.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Perfil {

    @Column(name = "perfil_nome")
    private String nome;
}
