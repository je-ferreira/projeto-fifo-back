package com.squad5.fifo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Dispositivo {
	
	@Id @GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	private String nome;

	@Column(nullable = false)
	private Boolean ativo;

	@ManyToMany
	private List<TipoDispositivo> tipoDispositivoList;
	
}
