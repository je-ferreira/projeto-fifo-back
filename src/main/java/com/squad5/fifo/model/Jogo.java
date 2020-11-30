package com.squad5.fifo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

import javax.persistence.*;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Jogo {
	
	@Id @GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	private String nome;
	
	private Boolean ativo;

	private String urlCapa;

	@ManyToOne
	private Dispositivo dispositivoPreferencial;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<TipoDispositivo> tipoDispositivoList;

}
