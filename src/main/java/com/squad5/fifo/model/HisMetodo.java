package com.squad5.fifo.model;

import lombok.Data;

import javax.persistence.*;

@Entity @Data
public class HisMetodo {

	@Id
	private Long id;

	private String estado;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TipoMetodo tipoMetodo;

}
