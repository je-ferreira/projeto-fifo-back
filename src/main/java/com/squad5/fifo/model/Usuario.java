package com.squad5.fifo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

}
