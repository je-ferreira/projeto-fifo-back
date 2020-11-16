package com.squad5.fifo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data @EqualsAndHashCode(callSuper = true)
public class JogoInsertDTO extends JogoDTO {

	@JsonIgnore
	private Long id;

	@NotBlank
	private String nome;
	
	@NotNull
	private Boolean ativo;

}
