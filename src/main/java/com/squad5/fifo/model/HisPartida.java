package com.squad5.fifo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity @Data
public class HisPartida {

	@Id
	private long id;

	private String resultado;

	@ManyToOne(optional = false)
	private HisNode hisNode;

	@ManyToOne(optional = false)
	private Jogo jogo;

}
