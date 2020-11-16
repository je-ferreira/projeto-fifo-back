package com.squad5.fifo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity @Data
public class Partida {

	@Id
	@GeneratedValue
	private Long id;

	private String resultado;

	@ManyToOne(optional = false)
	private Jogo jogo;

	@ManyToOne(optional = false)
	private Node node;

}
