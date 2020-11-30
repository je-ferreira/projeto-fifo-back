package com.squad5.fifo.dto;

import lombok.Data;

import java.util.List;

@Data
public class JogoDTO {

	private Long id;

	private String nome;

	private Boolean ativo;

	private String urlCapa;

	private Long dispositivoPreferencial;

	private List<Long> tipoDispositivoIdList;

}