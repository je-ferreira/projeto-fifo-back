package com.squad5.fifo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

import javax.persistence.*;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class TipoDispositivo {
	
	@Id @GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	private String nome;

	@ManyToMany(mappedBy = "tipoDispositivoList", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@ToString.Exclude
	private List<Jogo> jogoList;

}
