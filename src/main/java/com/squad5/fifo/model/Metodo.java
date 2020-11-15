package com.squad5.fifo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity @Data
public class Metodo {

	@Id
	@GeneratedValue
	private Long id;

	private String estado;

	@ManyToOne(optional = false)
	private TipoMetodo tipoMetodo;

}
