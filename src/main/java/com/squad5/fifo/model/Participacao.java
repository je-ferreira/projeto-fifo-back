package com.squad5.fifo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity @Data
@Builder @AllArgsConstructor
@NoArgsConstructor
public class Participacao {

	@EmbeddedId
	private ParticipacaoId id = new ParticipacaoId();

	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	private Vez vez;

	private Integer resultado;

}
