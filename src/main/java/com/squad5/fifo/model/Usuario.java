package com.squad5.fifo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private CargoUsuario cargoUsuario;

    @ManyToOne
    private Vez vez;

}
