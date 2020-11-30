package com.squad5.fifo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data @EqualsAndHashCode(callSuper = true)
public class TipoDispositivoInsertDTO extends TipoDispositivoDTO {

	@JsonIgnore
	private Long id;

	@NotBlank
	private String nome;

}
