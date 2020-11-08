package com.squad5.fifo.dto;

import com.squad5.fifo.model.Dispositivo;

import lombok.Data;

@Data
public class DispositivoDTO {

	private long id;
	private String nome;
	
	public DispositivoDTO(Dispositivo d) {
		id = d.getId();
		nome = d.getNome();
	}
}
