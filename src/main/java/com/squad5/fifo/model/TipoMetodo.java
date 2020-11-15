package com.squad5.fifo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Data
public class TipoMetodo {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	private String nome;

}
