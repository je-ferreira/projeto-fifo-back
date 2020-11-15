package com.squad5.fifo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity @Data
public class HisMetodo {

	@Id
	private Long id;

	private String estado;

	@ManyToOne(optional = false)
	private TipoMetodo tipoMetodo;

}
