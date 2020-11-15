package com.squad5.fifo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Jogo {
	
	@Id @GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	private String nome;
	
	private boolean ativo;
	
	@ManyToMany
	private List<TipoDispositivo> tiposDispositivo;
}