package com.squad5.fifo.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity @Data
@Builder
public class Vez {

	@Id
	@GeneratedValue
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date entrada;

	@ManyToOne(optional = false)
	private Jogo jogo;

	@ManyToOne
	private Dispositivo dispositivo;

	@ManyToMany
	private List<Usuario> usuarioList;

}
